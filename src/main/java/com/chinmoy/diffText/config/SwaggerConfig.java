package com.chinmoy.diffText.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

	/**
	 * Class for the auto generated Swagger API Documentation.
	 */
	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {

	    /**
	     * Create the Spring Bean that is responsible to generate de Swagger API documentation basing in the
	     * Spring Controller and their declared methods.
	     * <p>
	     * Controllers mapped with the annotation {@link Api} will be included in the documentation except when the
	     * annotation {@link ApiIgnore} is also declared.
	     *
	     * @return the
	     */
	    @Bean
	    public Docket generateDocumentation() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("Chinmoy")
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
	                .apis(sel -> sel != null && !sel.findAnnotation(ApiIgnore.class).isPresent())
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Diff Document")
	                .build();
	    }


}
