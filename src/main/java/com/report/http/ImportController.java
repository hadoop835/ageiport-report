package com.report.http;

import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.processor.core.AgeiPort;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteParam;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteResult;
import com.report.server.Query;
import com.report.server.StandaloneImportProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/v1")
@Slf4j
public class ImportController {
    @Autowired
    private AgeiPort ageiPort;

    @GetMapping(value = "/upload")
    public TaskExecuteResult upload(){
        String taskCode = StandaloneImportProcessor.class.getSimpleName();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("20230106114957-14c1b05b.xlsx");
        String fileKey = UUID.randomUUID().toString()+".xlsx";
        ageiPort.getFileStore().save(fileKey, inputStream, new HashMap<>());
        //3.构造查询参数TaskExecuteParam
        TaskExecuteParam request = new TaskExecuteParam();
        Query query = new Query();
        request.setTaskSpecificationCode(taskCode);
        request.setBizUserId("userId");
        request.setBizQuery(JsonUtil.toJsonString(query));
        request.setInputFileKey(fileKey);


        //4.调用本地方法executeTask，开始执行任务，并获取任务实例ID
        return  ageiPort.getTaskService().executeTask(request);
    }
}
