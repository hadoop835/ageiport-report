package com.report.plugin;

import org.springframework.stereotype.Service;

@Service
public class ExtDefaultShopPlugin extends  DefaultShopPlugin {
    @Override
    public void save() {
       System.out.println("ExtDefaultShopPlugin");
    }
}
