package com.report.plugin.spi;

import com.report.config.PluginConfig;
import com.report.config.SpiConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.plugin.core.Plugin;

import java.util.ServiceLoader;

public interface  ExtPlugin extends Plugin<PluginConfig> {


    @Override
    default boolean supports(@NotNull PluginConfig pluginConfig) {
        return this.getClass().getSimpleName().equals(pluginConfig.getCode());
    }
}
