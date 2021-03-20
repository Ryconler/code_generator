package com.wzmxx.code.model;

import lombok.Data;

import java.util.List;

@Data
public class EntityModel {
    private String name;
    private List<FieldModel> fields;
}
