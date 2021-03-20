package com.wzmxx.code;

import com.wzmxx.code.model.EntityModel;
import com.wzmxx.code.model.FieldModel;
import com.wzmxx.code.model.GenerateResultModel;
import com.wzmxx.code.service.GenerateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateServiceTest {

    @Autowired
    public GenerateService autoCreateVueUtils;


    @Test
    public void generate() throws Exception {
        EntityModel entityModel = new EntityModel();
        entityModel.setName("CodeTest");
        List<FieldModel> fieldModels = new ArrayList<>();
        fieldModels.add(new FieldModel("name", "String", "名称",false));
        fieldModels.add(new FieldModel("createTime", "Date", "创建时间",false));
        fieldModels.add(new FieldModel("filePath", "String", "路径",true));
        fieldModels.add(new FieldModel("isPublic", "Boolean", "是否公开",false));
        entityModel.setFields(fieldModels);
        List<GenerateResultModel> results = autoCreateVueUtils.generateSpring(entityModel);
    }
}
