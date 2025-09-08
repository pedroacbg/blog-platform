package com.pedroacbg.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Platform API")
                        .version("v1")
                        .description("REST API for a Blog Platform")
                        .termsOfService("https://github.com/pedroacbg")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/pedroacbg")
                        )
        );
    }

}
