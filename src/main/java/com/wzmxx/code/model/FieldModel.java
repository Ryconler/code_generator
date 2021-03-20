package com.wzmxx.code.model;

import lombok.Data;

@Data
public class FieldModel {
    private String name;
    private String type;
    private String description;
    private Boolean allowNull;

    public FieldModel(String name, String type, String description,  Boolean allowNull) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.allowNull = allowNull;
    }
}
