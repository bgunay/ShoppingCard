package com.burhan.webstore.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.burhan.webstore.domain")
@ComponentScan(basePackages = { "com.burhan.webstore" })
public class SpringMongoConfig extends AbstractMongoConfiguration{

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Override
	protected String getDatabaseName() {
		return "shoppingcard";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}

}