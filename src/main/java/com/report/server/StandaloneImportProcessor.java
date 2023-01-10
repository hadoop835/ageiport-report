package com.report.server;

import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.processor.core.annotation.ImportSpecification;
import com.alibaba.ageiport.processor.core.constants.ExecuteType;
import com.alibaba.ageiport.processor.core.model.api.BizDataGroup;
import com.alibaba.ageiport.processor.core.model.api.BizUser;
import com.alibaba.ageiport.processor.core.spi.file.DataGroup;
import com.alibaba.ageiport.processor.core.task.importer.ImportProcessor;
import com.alibaba.ageiport.processor.core.task.importer.model.BizImportResult;
import com.alibaba.ageiport.processor.core.task.importer.model.BizImportResultImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@ImportSpecification(code = "StandaloneImportProcessor", name = "StandaloneImportProcessor",executeType = ExecuteType.STANDALONE)
public class StandaloneImportProcessor implements ImportProcessor<Query, Data, View> {
    Logger logger = LoggerFactory.getLogger(StandaloneImportProcessor.class);

    @Override
    public BizImportResult<View, Data> convertAndCheck(BizUser user, Query query, List<View> views) {
        BizImportResultImpl<View, Data> result = new BizImportResultImpl<View, Data>();

        List<Data> data = new ArrayList<Data>();
        for (View view : views) {
            Data datum = new Data();
            datum.setId(view.getId());
            datum.setName(view.getName());
            data.add(datum);
        }

        result.setData(data);
        result.setView(query.getCheckErrorData());
        return result;
    }

    @Override
    public BizImportResult<View, Data> write(BizUser user, Query query, List<Data> data) {
        BizImportResultImpl<View, Data> result = new BizImportResultImpl<>();
        logger.info(JsonUtil.toJsonString(data));
        result.setData(data);
        result.setView(query.getWriteErrorData());
        return result;
    }

    @Override
    public DataGroup getDataGroup(BizUser user, Query query, BizDataGroup<View> bizDataGroup) {
        System.out.println(1);
        return null;
    }
}
