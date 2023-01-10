package com.report.config;

import com.alibaba.ageiport.ext.file.store.FileStoreOptions;

import java.io.File;

public class LocalFileStoreOptions implements FileStoreOptions {
    private String basePath;

    public LocalFileStoreOptions(String path){
        this.basePath =  path+ File.separator + "agei" + File.separator;
    }


    @Override
    public String type() {
        return "LocalFileStore";
    }
}
