package com.report.http;

import com.alibaba.ageiport.ext.arch.ExtensionLoader;
import com.report.PluginContext;
import com.report.config.PluginConfig;
import com.report.config.SpiConfig;
import com.report.plugin.spi.ShopPlugin;
import com.report.plugin.spi.UserPlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ServiceLoader;

@RestController
@RequestMapping(value = "/v1")
@Slf4j
public class PluginController {

    @Autowired
    private PluginRegistry<ShopPlugin,PluginConfig> pluginCont;

    @Autowired
    private PluginRegistry<UserPlugin,PluginConfig> userPluginPluginContext;


    @GetMapping(value = "/plugin")
    public   ShopPlugin  plugin(){
         ShopPlugin shopPlugin =  PluginContext.getBean(pluginCont,ShopPlugin.class);
        shopPlugin.save();
         UserPlugin userPlugin =  PluginContext.getBean(userPluginPluginContext,UserPlugin.class);
        return shopPlugin;
    }
}
