package com.xzq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@Configuration
@PropertySource("classpath:redis.properties")
@ImportResource({ "classpath:spring-redis.xml" })
@Order(98)
public class Config {
	
}
