package com.example.studentmgmtsystem.configuration;

import com.example.studentmgmtsystem.repository.RepositoryWrapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class StudentMgmtSystemConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors
                .basePackage("com.example.studentmgmtsystem")).paths(PathSelectors.any()).build();
    }

    @Bean
    public RepositoryWrapperFactory repositoryWrapperFactory(final WebApplicationContext applicationContext) {
        final var repositories = new Repositories(applicationContext);
        return type -> repositories.getRepositoryFor(type).map(repo -> (JpaRepository<?, ?>) repo).orElseThrow();
    }

}
