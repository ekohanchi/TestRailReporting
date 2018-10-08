package com.project.testrail;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableAsync
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Docket runAllStatusReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Status Report")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/getAllStatusReport.*"))
				.build();
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TestRail Reporter with Swagger")
                .description("TestRail Reporter with Swagger")
                .termsOfServiceUrl("https://github.com/ekohanchi/testrailReporting")
                .contact("Eli Kohanchi")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/ekohanchi/testrailReporting")
                .version("2.0")
                .build();
    }
	
//    public static void main(String[] args) {
//        @SuppressWarnings("unused")
//		ApplicationContext ctx = SpringApplication.run(Application.class, args);
//    }
}
