package com.wzmxx.code.ctrl;

import com.wzmxx.code.model.EntityModel;
import com.wzmxx.code.model.GenerateResultModel;
import com.wzmxx.code.service.GenerateService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/generate")
public class GenerateController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);

    @Autowired
    private GenerateService generateService;

    @PostMapping("/vue")
    public List<GenerateResultModel> generateVue(@RequestBody EntityModel model) {
        List<GenerateResultModel> results = this.generateService.generateVue(model);
        return results;
    }
    @PostMapping("/spring")
    public List<GenerateResultModel> generateSpring(@RequestBody EntityModel model) {
        List<GenerateResultModel> results = this.generateService.generateSpring(model);
        return results;
    }

    @GetMapping("/download/**")
    public void donwload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        int startIndex = servletPath.indexOf("/download/");
        if (startIndex > -1) {
            String filePath = servletPath.substring(startIndex + 10);
            File file = new File(filePath);
            String absPath = file.getAbsolutePath();
            logger.info("下载文件 {}", absPath);
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                IOUtils.copy(fis, response.getOutputStream());
                response.flushBuffer();
                fis.close();
            } else {
                logger.info("文件 {} 不存在", absPath);
                response.getWriter().print("error文件" + absPath + "不存在");
                response.getWriter().close();
            }
        }
    }
}
