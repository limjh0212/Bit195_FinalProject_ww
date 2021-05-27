package com.bit.ww.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String version;
    private String title;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo")
                .description("API EXAMPLE")
                .build();
    }

    @Bean
    public Docket api() {
        version = "V1";
        title = "WW API " + version;
        return new Docket(DocumentationType.SWAGGER_2)

                .select()
                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
//                .paths(PathSelectors.ant("/member/**")) // 그중 /api/** 인 URL들만 필터링
                .build();
    }

}
