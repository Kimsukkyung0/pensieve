package com.example.pensieve.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "authorization",
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {
//        @Bean
//        public OpenAPI api(){
//            Info info = new Info().title("swagg test").version("v3 swagger").description("test");
//            return new OpenAPI().components(new Components()).info(info);
//        }

    //스웨거 fox(2)설정파일.
//    @Bean
//    public Docket api2(){
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(true)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/**"))
//                .build();
//    }

    }
//
////    private ApiInfo apiInfo(){
////        Contact ct = new Contact("m","www.naver.com","smk93021@gmail.com");
////
////        return new ApiInfo(
////                "TEST API",
////                "Some custom description of API.",
////                "0.0.1",
////                "Terms of service",
////                ct, "License of API", "API license URL", Collections.emptyList());
////    }
//
////    @Bean
////    public Docket commonApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .groupName("example")
////                .apiInfo(this.apiInfo())
////                .select()
////                .apis(RequestHandlerSelectors
////                        .basePackage("com.example.demo.controller"))
////                .paths(PathSelectors.ant("/**"))
////                .build();
////    }
