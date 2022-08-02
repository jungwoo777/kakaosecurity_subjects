package com.example.kakaosecurity.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * 스웨거 설정
 * @author jungwoo
 *
 */
@Configuration
public class SwaggerConfig {
	
	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/**")
                .build();
    }
	
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("KAKAO SECURITY TEST API")
                        .description("카카오페이증권 과제 API 명세서입니다.")
                        .version("v1.0.0"));
    }

}
