package com.spring.project.root;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.spring.project.root.configs.CoreRepositoryConfiguration;
import com.spring.project.root.configs.DataAccessConfiguration;

/**
 * @author Enis
 */
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class, DataSourceAutoConfiguration.class })
@Import({ DataAccessConfiguration.class, CoreRepositoryConfiguration.class })
@EntityScan("com.spring.project.root.dataaccess")
@ComponentScan(basePackages = "com.spring.project.root.")
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	protected void addAdditionalDialects(final Set<IDialect> dialects) {
		dialects.add(new SpringSecurityDialect());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
