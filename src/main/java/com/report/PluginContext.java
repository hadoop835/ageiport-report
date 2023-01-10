package com.report;

import com.report.config.PluginConfig;
import com.report.config.SpiConfig;
import com.report.plugin.spi.ShopPlugin;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.plugin.core.Plugin;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class PluginContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static SpiConfig spiConfig;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PluginContext.applicationContext = applicationContext;
        PluginContext.spiConfig = PluginContext.applicationContext.getBean(SpiConfig.class);


    }


    public  static   <T extends Plugin<PluginConfig>>  T   getBean(PluginRegistry<T ,PluginConfig> pluginRegistry,Class<T> clazz){
        return  pluginRegistry.getRequiredPluginFor((PluginConfig) PluginContext.spiConfig.getLoadCode(clazz));
    }
}
