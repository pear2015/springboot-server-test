package com.gs.crms.backend.configs;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *  Created by  on 2017/3/2.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.gs.crms"},repositoryFactoryBeanClass = com.gsafety.springboot.common.pagerepository.QueryMetaDataRepositoryFactoryBean.class)
@EnableAutoConfiguration
@EntityScan({"com.gs.crms"})
public class JpaConfiguration {
}


