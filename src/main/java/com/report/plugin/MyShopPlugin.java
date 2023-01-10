package com.report.plugin;

import com.report.config.PluginConfig;
import com.report.plugin.spi.ShopPlugin;
import org.springframework.stereotype.Service;

@Service
public class MyShopPlugin implements ShopPlugin{
    @Override
    public void save() {

    }


}
