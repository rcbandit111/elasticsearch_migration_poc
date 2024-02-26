package com.test.portal.account.platform.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xcontent.DeprecationHandler;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WebhookPlatformClientNewMigrated {

  private TransportClient client;

  private String index;

  public void setClient(String name, String node) throws Exception {
    try {
      Settings settings = Settings.builder().put("cluster.name", name).build();
      client = new PreBuiltTransportClient(settings);
      client.addTransportAddress(
              new TransportAddress(InetAddress.getByName(node), 9300));
      if (client.connectedNodes().isEmpty()) {
        throw new Exception("No nodes");
      } else {
       client.connectedNodes();
      }
    } catch (Exception e) {
      throw new Exception("Error");
    }
  }

  public DeleteResponseDto delete(DeleteRequestDto dto) throws Exception {
    DeleteByQueryRequestBuilder delete = new DeleteByQueryRequestBuilder(client,DeleteByQueryAction.INSTANCE);
    delete.source().setIndices(index);
    delete.filter(dto.getQuery());
    delete.refresh(dto.isRefresh());
    List<String> types = dto.getTypes();
    if (types != null && !types.isEmpty()) {
      delete.source().setTypes(types.toArray(new String[types.size()]));
    }
    try {
      BulkByScrollResponse response = delete.get();
      DeleteResponseDto responseDto = new DeleteResponseDto();
      responseDto.setDeleted(response.getDeleted());
      responseDto.setBulkFailures(response.getBulkFailures());
      responseDto.setSearchFailures(response.getSearchFailures());
      return responseDto;

    } catch (Exception ex) {
      throw new Exception("Error");
    }
  }

  private BulkResponseDto perform(BulkRequestBuilder bulkRequest,
                                  BulkRequestDto dto) {
    int noOfRetries = dto.getNoOfRetries();
    int firstRetryWaitTimeInMillis = dto.getFirstRetryWaitTimeInMillis();

    Retry retryBulkRequest = new Retry(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(firstRetryWaitTimeInMillis), noOfRetries),client.threadPool());
    BulkResponse response = retryBulkRequest.withBackoff(
            (bulkReq, bulkResponseActionListener) -> client.bulk(bulkReq, bulkResponseActionListener),
            bulkRequest.request()).resultNow();
    BulkResponseDto responseDto = new BulkResponseDto();
    responseDto.setTimeTakenInMilli(response.getTook().getMillis());
    List<IndexBulkResponseDto> responseInfo = new ArrayList<>();
    for (BulkItemResponse itemResponse : response.getItems()) {
      IndexBulkResponseDto itemResponseInfo = new IndexBulkResponseDto();
      itemResponseInfo.setId(itemResponse.getId());
      itemResponseInfo.setPositionInRequestList(itemResponse.getItemId());
      itemResponseInfo.setFailed(itemResponse.isFailed());
      itemResponseInfo.setFailedMessage(itemResponse.getFailureMessage());
      responseInfo.add(itemResponseInfo);
    }
    responseDto.setResponseInfo(responseInfo);
    System.out.println(response);
    return responseDto;
  }

  public void createIndex(Map<String, Object> indexMapping) throws Exception {
    try {
      CreateIndexRequest request = new CreateIndexRequest(index);
      if (indexMapping != null) {
        request.source(indexMapping, DeprecationHandler.IGNORE_DEPRECATIONS);
      }
      CreateIndexResponse createIndexResponse = client.admin().indices().create(request).actionGet();
      System.out.println(createIndexResponse);
    } catch (Exception exception) {
      throw new Exception(exception);
    }
  }




}
