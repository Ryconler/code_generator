package com.wzmxx.code.model;

import lombok.Data;

@Data
public class GenerateResultModel {
    private String filePath;
    private String fileContent;

    public GenerateResultModel(String filePath, String fileContent) {
        this.filePath = filePath;
        this.fileContent = fileContent;
    }
}
