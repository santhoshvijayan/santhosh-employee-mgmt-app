package com.santhosh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@PropertySource("application.properties")
public class SpringMongoConfig{

	@Autowired
    private Environment env;
  public @Bean
  MongoDbFactory mongoDbFactory() throws Exception {
	    return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
  }

  public @Bean
  MongoTemplate mongoTemplate() throws Exception {

	//remove _class
	MappingMongoConverter converter =
		new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
	converter.setTypeMapper(new DefaultMongoTypeMapper(null));

	MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);

	return mongoTemplate;

  }

}