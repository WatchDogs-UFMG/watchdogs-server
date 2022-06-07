package br.ufmg.watchdogs.server.api.config.doc;

import br.ufmg.watchdogs.server.WatchdogsServerApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfig {

    @Bean
    public Docket watchdogsApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(WatchdogsServerApplication.class.getPackageName()))
                .paths(PathSelectors.any())
                .build();

    }
}
