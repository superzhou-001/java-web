package indi.study.system.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${Swagger-Enable}")
    private boolean swaggerEnable;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 校验是否开启swagger接口页面
        if (swaggerEnable) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
            // swagger新页面
            registry.addResourceHandler("doc.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
        }

    }

    /**
     * 设置多个：
     *
     * @Bean
     *     public Docket appApi() {
     *
     *         List<Parameter> pars = new ArrayList<>();
     *         ParameterBuilder token = new ParameterBuilder();
     *         token.name("token").description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false)
     *                 .build();
     *         pars.add(token.build());
     *
     *         return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/app/.*")).build()
     *                 .globalOperationParameters(pars).apiInfo(pdaApiInfo()).useDefaultResponseMessages(false)
     *                 .enable(enableSwagger)
     *                 .groupName("appApi");
     *
     *     }
     *
     *     @Bean
     *     public Docket adminApi() {
     *
     *         List<Parameter> pars = new ArrayList<>();
     *         ParameterBuilder token = new ParameterBuilder();
     *         token.name("token").description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false)
     *                 .build();
     *         pars.add(token.build());
     *         return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/admin/.*")).build()
     *                 .globalOperationParameters(pars).apiInfo(pdaApiInfo()).useDefaultResponseMessages(false)
     *                 .enable(enableSwagger)
     *                 .groupName("adminApi");
     *
     *     }
     *
     *
     * @return
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("indi.study.system.controller")).paths(PathSelectors.any())
                .build().groupName("web-system接口");
        // 占时取消token
        //.globalOperationParameters(setHeaderToken())
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("web-system 接口文档").description("swagger接口").termsOfServiceUrl("")
                .version("1.0").build();
    }

    /**
     * @Description: 设置swagger文档中全局参数
     * @param
     * @Date: 2020/9/11 10:15
     * @return: java.util.List<springfox.documentation.service.Parameter>
     */

    private List<Parameter> setHeaderToken() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder userId = new ParameterBuilder();
        userId.name("token").description("用户TOKEN").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        pars.add(userId.build());
        return pars;
    }
}

