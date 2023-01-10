package com.report.plugin.spi;

import com.alibaba.ageiport.ext.arch.SPI;
import com.report.config.PluginConfig;
import org.springframework.plugin.core.Plugin;
@SPI
public interface ShopPlugin extends ExtPlugin {

      void  save();
}
