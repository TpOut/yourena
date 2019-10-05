package com.example.selfdic;

import org.litepal.crud.DataSupport;

/**
 * Created by tpout on 17-8-27.
 */

public class ItemBean extends DataSupport {

    private String querySrc;
    private String queryResult;

    public ItemBean(String querySrc, String queryResult) {
        this.querySrc = querySrc;
        this.queryResult = queryResult;
    }

    public String getQuerySrc() {
        return querySrc;
    }

    public void setQuerySrc(String querySrc) {
        this.querySrc = querySrc;
    }

    public String getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(String queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "querySrc : " + querySrc
                + " ;"
                + "queryResult : " + queryResult
                + "}";
    }
}
