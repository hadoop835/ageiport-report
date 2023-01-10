package com.report.server;

import java.util.List;

public class Query {
    private Integer totalCount = 10000;
    private List<View> checkErrorData;
    private List<View> writeErrorData;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<View> getCheckErrorData() {
        return checkErrorData;
    }

    public void setCheckErrorData(List<View> checkErrorData) {
        this.checkErrorData = checkErrorData;
    }

    public List<View> getWriteErrorData() {
        return writeErrorData;
    }

    public void setWriteErrorData(List<View> writeErrorData) {
        this.writeErrorData = writeErrorData;
    }
}
