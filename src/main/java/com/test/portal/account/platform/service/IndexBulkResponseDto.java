package com.test.portal.account.platform.service;

public class IndexBulkResponseDto {
  private boolean isFailed;
  private int positionInRequestList;
  private String id;
  private String failedMessage;

  public boolean isFailed() {
    return isFailed;
  }

  public void setFailed(boolean isFailed) {
    this.isFailed = isFailed;
  }

  public int getPositionInRequestList() {
    return positionInRequestList;
  }

  public void setPositionInRequestList(int positionInRequestList) {
    this.positionInRequestList = positionInRequestList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFailedMessage() {
    return failedMessage;
  }

  public void setFailedMessage(String failedMessage) {
    this.failedMessage = failedMessage;
  }
}
