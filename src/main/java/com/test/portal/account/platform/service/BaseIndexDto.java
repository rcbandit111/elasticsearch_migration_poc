package com.test.portal.account.platform.service;

import java.util.Map;

public class BaseIndexDto {
  private String type;
  private String id;
  private Map<String, Object> document;
  private Long updateSpecificVersion;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, Object> getDocument() {
    return document;
  }

  public void setDocument(Map<String, Object> document) {
    this.document = document;
  }

  public Long getUpdateSpecificVersion() {
    return updateSpecificVersion;
  }

  public void setUpdateSpecificVersion(Long specificVersion) {
    this.updateSpecificVersion = specificVersion;
  }
  
}
