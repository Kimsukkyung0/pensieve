package com.example.pensieve.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact ct = new Contact("m","www.naver.com","smk93021@gmail.com");

        return new ApiInfo(
                "TEST API",
                "Some custom description of API.",
                "0.0.1",
                "Terms of service",
                new Contact("MemoStack", "https://memostack.tistory.com", "public.devhong@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

//    @Bean
//    public Docket commonApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("example")
//                .apiInfo(this.apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors
//                        .basePackage("com.example.demo.controller"))
//                .paths(PathSelectors.ant("/**"))
//                .build();
//    }
}

