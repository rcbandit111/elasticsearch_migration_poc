package com.test.portal.account.platform.service;

import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;

import java.util.List;

public class BulkRequestDto {
  private List<BaseIndexDto> indices;
  private int firstRetryWaitTimeInMillis;
  private int noOfRetries;
  private RefreshPolicy refreshPolicy = RefreshPolicy.NONE;

  public int getFirstRetryWaitTimeInMillis() {
    return firstRetryWaitTimeInMillis;
  }

  public void setFirstRetryWaitTimeInMillis(int firstRetryWaitTimeInMillis) {
    this.firstRetryWaitTimeInMillis = firstRetryWaitTimeInMillis;
  }

  public int getNoOfRetries() {
    return noOfRetries;
  }

  public void setNoOfRetries(int noOfRetries) {
    this.noOfRetries = noOfRetries;
  }

  public List<BaseIndexDto> getIndices() {
    return indices;
  }

  public void setIndices(List<BaseIndexDto> indices) {
    this.indices = indices;
  }

  public RefreshPolicy getRefreshPolicy() {
    return refreshPolicy;
  }

  public void setRefreshPolicy(RefreshPolicy refreshPolicy) {
    this.refreshPolicy = refreshPolicy;
  }
}
