package com.report.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ConfigurationProperties(prefix = "spi")
public class SpiConfig {
    private Map<String,String> clazz = new ConcurrentHashMap<String,String>();

    public Map<String, String> getClazz() {
        return clazz;
    }

    public void setClazz(Map<String, String> clazz) {

        this.clazz = clazz;
    }

    public  <T>  PluginConfig   getLoadCode(Class<T> clazz){
        PluginConfig pluginConfig =new PluginConfig();
        if(CollectionUtils.isEmpty(getClazz())){
            pluginConfig.setCode("Default"+clazz.getSimpleName());
            return pluginConfig;
        }else{
            if(getClazz().containsKey(clazz.getName())){
                pluginConfig.setCode(getClazz().get(clazz.getName()));
            }else{
                pluginConfig.setCode("Default"+clazz.getSimpleName());
            }
            return pluginConfig;
        }

    }
}
