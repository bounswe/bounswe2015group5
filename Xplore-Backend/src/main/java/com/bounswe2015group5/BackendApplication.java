package com.bounswe2015group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(ConversionServiceConfiguration.class)
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getBeanFactory().registerSingleton("myBeanName", new String("String handled as Singleton in Spring"));
//        context.register(RelationPKConverter.class);
//        context.refresh();
    }
}
