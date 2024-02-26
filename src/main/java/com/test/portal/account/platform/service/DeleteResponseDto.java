package com.test.portal.account.platform.service;

import org.elasticsearch.action.bulk.BulkItemResponse.Failure;
import org.elasticsearch.index.reindex.ScrollableHitSource.SearchFailure;

import java.util.List;

public class DeleteResponseDto {
  private Long deleted;
  private List<Failure> bulkFailures;
  private List<SearchFailure> searchFailures;

  public List<Failure> getBulkFailures() {
    return bulkFailures;
  }

  public void setBulkFailures(List<Failure> bulkFailures) {
    this.bulkFailures = bulkFailures;
  }

  public List<SearchFailure> getSearchFailures() {
    return searchFailures;
  }

  public void setSearchFailures(List<SearchFailure> searchFailures) {
    this.searchFailures = searchFailures;
  }

  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }
}
