package com.report.server;

import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.processor.core.annotation.ExportSpecification;
import com.alibaba.ageiport.processor.core.constants.ExecuteType;
import com.alibaba.ageiport.processor.core.exception.BizException;
import com.alibaba.ageiport.processor.core.model.api.BizDataGroup;
import com.alibaba.ageiport.processor.core.model.api.BizExportPage;
import com.alibaba.ageiport.processor.core.model.api.BizUser;
import com.alibaba.ageiport.processor.core.model.api.impl.BizDataGroupImpl;
import com.alibaba.ageiport.processor.core.task.exporter.ExportProcessor;
import com.alibaba.ageiport.processor.core.task.exporter.api.BizExportTaskRuntimeConfig;
import com.alibaba.ageiport.processor.core.task.exporter.api.BizExportTaskRuntimeConfigImpl;

import java.util.ArrayList;
import java.util.List;

@ExportSpecification(code = "StandaloneExportProcessor", name = "StandaloneExportProcessor",executeType=ExecuteType.STANDALONE)
public class StandaloneExportProcessor implements ExportProcessor<Query, Data, View> {
    @Override
    public Integer totalCount(BizUser bizUser, Query query) throws BizException {
        return query.getTotalCount();
    }

    @Override
    public List<Data> queryData(BizUser user, Query query, BizExportPage bizExportPage) throws BizException {
        List<Data> dataList = new ArrayList<Data>();

        Integer totalCount = query.getTotalCount();
        for (int i = 1; i <= totalCount; i++) {
            final Data data = new Data();
            data.setId(i);
            data.setName("name" + i);
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<View> convert(BizUser user, Query query, List<Data> data) throws BizException {
        List<View> dataList = new ArrayList<View>();
        for (Data datum : data) {
            View view = BeanUtils.cloneProp(datum, View.class);
            dataList.add(view);
        }
        return dataList;
    }


}
