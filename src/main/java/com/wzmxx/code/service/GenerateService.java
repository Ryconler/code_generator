package com.wzmxx.code.service;

import com.wzmxx.code.model.EntityModel;
import com.wzmxx.code.model.GenerateResultModel;
import com.wzmxx.code.utils.NameUtils;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GenerateService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateService.class);

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${code.generate.path:./var/code/generate}")
    private File generateRoot;

    private final static String[] VUE_FILENAMES = {"-index.vue"};
    private final static String[] VUE_TEMPNAMES = {"vue/index.ftl"};

    private final static String[] SPRING_FILENAMES = {".java", "Repository.java", "Controller.java"};
    private final static String[] SPRING_TEMPNAMES = {"spring/entity.ftl", "spring/repository.ftl", "spring/controller.ftl"};



    public List<GenerateResultModel> generateVue(EntityModel entityModel)  {
        List<GenerateResultModel> results = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String nowTime = sdf.format(new Date());
        File destDir = new File(this.generateRoot, nowTime + "/" + entityModel.getName());
        String kebabName = NameUtils.getKebabName(entityModel.getName());

        for (int i = 0; i < VUE_FILENAMES.length; i++) {
            try {
                File destFile = new File(destDir, kebabName + VUE_FILENAMES[i]);
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(VUE_TEMPNAMES[i]);
                String content = writeFile(destFile, template, entityModel);
                results.add(new GenerateResultModel(destFile.getPath(), content));
            } catch (Exception e) {
                logger.error(e.toString(), e);
            }
        }
        return results;
    }

    public List<GenerateResultModel> generateSpring(EntityModel entityModel)  {
        List<GenerateResultModel> results = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String nowTime = sdf.format(new Date());
        File destDir = new File(this.generateRoot, nowTime + "/" + entityModel.getName());
        String entityName = entityModel.getName();

        for (int i = 0; i < SPRING_FILENAMES.length; i++) {
            try {
                File destFile = new File(destDir, entityName + SPRING_FILENAMES[i]);
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(SPRING_TEMPNAMES[i]);
                String content = writeFile(destFile, template, entityModel);
                results.add(new GenerateResultModel(destFile.getPath(), content));
            } catch (Exception e) {
                logger.error(e.toString(), e);
            }
        }
        return results;
    }

    public String writeFile(File destFile, Template template, EntityModel entityModel) throws Exception {
        File parentFile = destFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("entity", entityModel);
        paramMap.put("nameUtils", new NameUtils());

        template.setOutputEncoding("UTF-8");
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, paramMap);
        FileWriter fileWritter = new FileWriter(destFile.getAbsolutePath());;
        fileWritter.write(result);
        IOUtils.closeQuietly(fileWritter);
        return result;
    }

}
