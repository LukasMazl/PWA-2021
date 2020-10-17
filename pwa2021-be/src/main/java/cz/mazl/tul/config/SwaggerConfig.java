package cz.mazl.tul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2WebMvc
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SPRING_WEB).apiInfo(new ApiInfo(
                "DBA 2020",
                "semestral work",
                "1.0.0",
                null,
                null,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        ))
                .select()
                .apis(getApis())
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<RequestHandler> getApis() {
        return RequestHandlerSelectors.any();
    }
}
