package com.test.portal.account.platform.service;

import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

public class DeleteRequestDto {

  private QueryBuilder query;
  private Boolean refresh;
  private List<String> types;

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public QueryBuilder getQuery() {
    return query;
  }

  public void setQuery(QueryBuilder query) {
    this.query = query;
  }

  public boolean isRefresh() {
    return refresh;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

}
