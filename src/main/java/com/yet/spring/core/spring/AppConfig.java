package com.yet.spring.core.spring;

import com.yet.spring.core.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//@Configuration
//@EnableAspectJAutoProxy
//@PropertySource({
//        "classpath:client.properties",
//        "classpath:logger.properties"
//})
public class AppConfig {
//    @Autowired
    private Environment environment;

//    @Autowired
//    @DefaultLogger
//    private EventLogger logger;
//    @Autowired
//    @Resource(name = "loggerMap")
//    private Map<EventType, EventLogger> loggers;
//
//    @Bean
//    public App app() {
//        return new App(client(), logger, loggers);
//    }

//    @Bean
    public Client client() {
        Client client = new Client();
        client.setId(environment.getRequiredProperty("id"));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getRequiredProperty("greeting"));
        return client;
    }
}
