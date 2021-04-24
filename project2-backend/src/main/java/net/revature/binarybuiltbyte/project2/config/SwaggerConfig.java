package net.revature.binarybuiltbyte.project2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All Repositories")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket api2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("CRUD Operations by ID")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/*/*"))
                .build();
    }

    @Bean
    public Docket api3(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("byte Users")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/byte-user/**"))
                .build();
    }
    @Bean
    public Docket api4(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("EndPoints")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("(?!/findByUsername).+"))
                .build();
    }

}
