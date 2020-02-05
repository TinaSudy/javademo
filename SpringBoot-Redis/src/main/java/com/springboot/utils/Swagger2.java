package com.springboot.utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * <pre>
 *     @author : orange
 *     e-mail : 495314527@qq.com
 *     time   : 2018/8/27 14:49
 *     desc   : swagger配置
 *     version: 1.0
 * </pre>
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
 
    @Bean
    public Docket restApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot整合swagger2")
                .description("springboot整合swagger2")
                .termsOfServiceUrl("https://blog.csdn.net/weixin_37591536")
                .version("1.0")
                .build();
    }
 
}