package com.gs.crms.backend.configs;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mongoDB数据库配置
 * Created by zhangqiang on 2017/8/15.
 */
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    /**
     * The Url.
     */
    @Value("${mongodb.host}")
    String host;

    /**
     * The port
     */
    @Value("${mongodb.port}")
    Integer port;

    /**
     * The database
     */
    @Value("${mongodb.database}")
    String database;

    /**
     * The Username.
     */
    @Value("${mongodb.username}")
    String username;
    /**
     * The Password.
     */
    @Value("${mongodb.password}")
    String password;


    /**
     * Grid fs template grid fs template.
     *
     * @return the grid fs template
     * @throws Exception the exception
     */
    @Bean(name = "gridFsTemplate")
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory() throws Exception {
        return super.mongoDbFactory();
    }

    /**
     * @return
     */
    @Override
    protected String getDatabaseName() {
        return database;
    }

    /**
     * @return
     * @throws Exception
     * @ConditionalOnMissingBean(MongoClient.class) 说明：告诉系统,下面的bean要替换系统默认的bean(mongodb)
     */
    @Override
    @Bean
    @ConditionalOnMissingBean(MongoClient.class)
    public Mongo mongo() throws Exception {
        List<ServerAddress> addresses = new ArrayList<>();
        List<String> hostList = Arrays.asList(StringUtils.split(host, ","));
        hostList.forEach(addr -> addresses.add(new ServerAddress(addr, port)));
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        return new MongoClient(addresses, Arrays.asList(credential));
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }
}
