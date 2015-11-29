package com.bounswe2015group5.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.bounswe2015group5.domain"})
@EnableJpaRepositories(basePackages = {"com.bounswe2015group5.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
