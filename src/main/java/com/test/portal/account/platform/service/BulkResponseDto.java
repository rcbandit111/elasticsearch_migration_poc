package com.test.portal.account.platform.service;

import java.util.List;

public class BulkResponseDto {
  private long timeTakenInMilli;
  private List<IndexBulkResponseDto> responseInfo;

  public long getTimeTakenInMilli() {
    return timeTakenInMilli;
  }

  public void setTimeTakenInMilli(long timeTakenInMilli) {
    this.timeTakenInMilli = timeTakenInMilli;
  }

  public List<IndexBulkResponseDto> getResponseInfo() {
    return responseInfo;
  }

  public void setResponseInfo(List<IndexBulkResponseDto> responseInfo) {
    this.responseInfo = responseInfo;
  }
}
