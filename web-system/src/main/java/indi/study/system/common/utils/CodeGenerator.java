package indi.study.system.common.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    //直接运行帮我们生成代码
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/web-system/src/main";
        String javaPath = path + "/java";
        String xmlPath = path + "/resources/mybatis/system";
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/webstudy_db?useUnicode=true&characterEncoding=utf8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("zhouming") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 禁止打开输出目录	默认值:true
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(javaPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.study.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    customFile.put("cc.vue", "/templates/cca.ftl");
                    consumer.customFile(customFile);
                })
                // 输出自定义文件到指定目录
                .packageConfig(builder -> {
                    builder.pathInfo(Collections.singletonMap(OutputFile.other, "D:")); // 设置mapperXml生成路径
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

