package com.xy.generator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.xy.generator.Generator;
import com.xy.generator.constant.ModelEnum;
import com.xy.generator.entity.BasicInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author zhaojianan
 *         代码生成工具类
 */
public class CodeUtil {

    public static void generator() throws SQLException {
        BasicInfo info = BasicInfo.create();
        Generator.GENERATOR_MODEL.stream().forEach(model -> {
            CodeUtil.createFile(info, model,
                    StringUtils.replace(Generator.PATH + model.path + model.baseUrl(info.getConfig()), ".", "/")
                            + "/"
                            + info.getEntityName() + model.prefix);

            System.out.println("创建" + info.getEntityName() + model.prefix + "成功");
        });
    }

    public static void createFile(BasicInfo dataModel, ModelEnum model, String filePath) {
        FileWriter out = null;
        try {
            @SuppressWarnings("deprecation")
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(CodeUtil.class, "/freemarker/ftl");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate(model.template);

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            } else {

                if (Generator.IS_OVERRIDE) {
                    file.delete();
                    file.createNewFile();
                }
            }

            // 设置输出流
            out = new FileWriter(file);
            // 模板输出静态文件
            template.process(dataModel, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
