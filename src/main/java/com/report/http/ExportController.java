package com.report.http;

import com.alibaba.ageiport.common.feature.FeatureUtils;
import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.processor.core.AgeiPort;
import com.alibaba.ageiport.processor.core.constants.MainTaskFeatureKeys;
import com.alibaba.ageiport.processor.core.model.core.ColumnHeader;
import com.alibaba.ageiport.processor.core.model.core.impl.ColumnHeaderImpl;
import com.alibaba.ageiport.processor.core.model.core.impl.ColumnHeadersImpl;
import com.alibaba.ageiport.processor.core.model.core.impl.MainTask;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteParam;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteResult;
import com.alibaba.ageiport.processor.core.spi.service.TaskProgressParam;
import com.alibaba.ageiport.processor.core.spi.service.TaskProgressResult;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.IoUtils;
import com.report.server.Query;
import com.report.server.StandaloneExportProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
@Slf4j
public class ExportController {

    @Autowired
    private AgeiPort ageiPort;

    @GetMapping(value = "/export")
    public  TaskExecuteResult  export(){
        //2.构造查询参数TaskExecuteParam
        Query query = new Query();
        query.setTotalCount(10000);
        TaskExecuteParam request = new TaskExecuteParam();
        request.setTaskSpecificationCode(StandaloneExportProcessor.class.getSimpleName());
        request.setBizUserId("userId");
        request.setBizUserTenant("1");
        request.setBizQuery(JsonUtil.toJsonString(query));
        request.setInputFileKey(UUID.randomUUID().toString());
        TaskExecuteResult response = ageiPort.getTaskService().executeTask(request);
        return response;
    }

    @GetMapping(value = "/getTaskProgress/{mainTaskId}")
    public  void GetTaskProgress(@PathVariable String mainTaskId, HttpServletResponse response) throws IOException {
        TaskProgressParam progressRequest = new TaskProgressParam();
        progressRequest.setMainTaskId(mainTaskId);
        TaskProgressResult taskProgress = ageiPort.getTaskService().getTaskProgress(progressRequest);

        MainTask mainTask = ageiPort.getTaskServerClient().getMainTask(mainTaskId);
        String fileKey = FeatureUtils.getFeature(mainTask.getFeature(), MainTaskFeatureKeys.OUTPUT_FILE_KEY);
        boolean exists = ageiPort.getFileStore().exists(fileKey, new HashMap<>());
        if(exists){
            String runtimeParam = mainTask.getRuntimeParam();
            String headersString = FeatureUtils.getFeature(runtimeParam, MainTaskFeatureKeys.RT_COLUMN_HEADERS_KEY);
            List<ColumnHeaderImpl> columnHeaderList = JsonUtil.toArrayObject(headersString, ColumnHeaderImpl.class);
            List<ColumnHeader> columnHeaderList1 = new ArrayList<>(columnHeaderList);
            ColumnHeadersImpl headers = new ColumnHeadersImpl(columnHeaderList1);
            String fileType = FeatureUtils.getFeature(runtimeParam, MainTaskFeatureKeys.RT_FILE_TYPE_KEY);
            InputStream inputStream = ageiPort.getFileStore().get(fileKey, new HashMap<>());
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            response.setHeader("Content-disposition", "attachment;filename=" + mainTaskId +"."+fileType);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(IoUtils.toByteArray(inputStream));
        }
    }


}
