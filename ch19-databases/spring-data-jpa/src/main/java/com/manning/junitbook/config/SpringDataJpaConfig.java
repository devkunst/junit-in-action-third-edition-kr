package com.manning.junitbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.manning.junitbook.repository")
@ComponentScan(basePackages = "com.manning.junitbook")
public class SpringDataJpaConfig {

}
