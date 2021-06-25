package com.spring.project.root.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * core repository {@link Configuration}
 *
 * @author Enis
 */
@Configuration
@ComponentScan(basePackages = { "com.spring.project.root.repository" })
public class CoreRepositoryConfiguration implements SpringConfiguration {
}
