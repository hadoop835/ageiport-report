package com.report.server;

import com.alibaba.ageiport.processor.core.annotation.ViewField;

public class View {
    @ViewField(headerName = "编码")
    private Integer id;
    @ViewField(headerName = "姓名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
