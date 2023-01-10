package com.report.config;

import com.alibaba.ageiport.ext.file.store.FileStoreOptions;
import com.alibaba.ageiport.processor.core.AgeiPort;
import com.alibaba.ageiport.processor.core.AgeiPortImpl;
import com.alibaba.ageiport.processor.core.AgeiPortOptions;
import com.alibaba.ageiport.processor.core.client.http.HttpTaskServerClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgeiPortConfig {

    @Bean
    public AgeiPort  ageiPort(){
        AgeiPortOptions options = new AgeiPortOptions();
        HttpTaskServerClientOptions httpTaskServerClientOptions = new HttpTaskServerClientOptions();
        httpTaskServerClientOptions.setEndpoint("127.0.0.1");
        httpTaskServerClientOptions.setTimeoutMs(60000);
        options.setApp("ageiport-report");
        options.setNamespace("com.alibaba");
        options.setTenant("test");
        options.setTaskServerClientOptions(httpTaskServerClientOptions);
        AgeiPortOptions.Cluster cluster = new AgeiPortOptions.Cluster();
        AgeiPort ageiPort = AgeiPort.ageiPort(options);
        return ageiPort;
    }

}
