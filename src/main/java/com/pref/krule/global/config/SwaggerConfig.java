package com.pref.krule.global.config;

import com.pref.krule.global.config.props.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final AppProperties appProperties;

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(appProperties.getUrl())
                .produces(buildProduces())
                .apiInfo(buildApiInfo())
                .ignoredParameterTypes(BindingResult.class, Pageable.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pref.krule.domain"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        String API_DESCRIPTION = "Krule 프로젝트 명세서";
        String API_VERSION = "0.0.1";
        String API_TITLE = "Krule API Documentation";

        return new ApiInfoBuilder()
                .title(API_TITLE)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }

    private Set<String> buildProduces() {
        return new HashSet<>() {{
            add(MediaType.APPLICATION_JSON_VALUE);
        }};
    }
}
