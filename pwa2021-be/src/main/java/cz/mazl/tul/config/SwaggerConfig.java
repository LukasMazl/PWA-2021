package cz.mazl.tul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "DBA 2020",
                        "semestral work",
                        "1.0.0",
                        null,
                        null,
                        "Apache 2.0",
                        "https://www.apache.org/licenses/LICENSE-2.0",
                        Collections.emptyList()))
                .ignoredParameterTypes(OidcUser.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hello.world"))
                .paths(PathSelectors.any())
                .build();
    }
}
