package com.report;

import com.report.plugin.spi.ShopPlugin;
import com.report.plugin.spi.UserPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnablePluginRegistries(value = {ShopPlugin.class, UserPlugin.class})
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class,args);
    }
}
