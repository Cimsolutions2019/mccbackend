package nl.cimsolutions.mccbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("voyagers")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/voyagers.*"))
                .build();
    }

    @Bean
    public Docket api2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("datasource")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/datasource.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Demo API")
                .description("Demo Q&A API application")
                .termsOfServiceUrl("www.mocktermsofservice.com")
                .contact("kaisar39@gmail.com").license("Custom license")
                .licenseUrl("www.customurl.com").version("1.0").build();
    }

}