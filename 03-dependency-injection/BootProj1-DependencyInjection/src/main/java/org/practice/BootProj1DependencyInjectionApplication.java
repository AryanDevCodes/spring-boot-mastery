package org.practice;

import org.practice.beans.WishMessageGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BootProj1DependencyInjectionApplication {
    @Bean
    public LocalDateTime createLoacalDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt;
    }
    public static void main(String[] args) {
     ApplicationContext context =  SpringApplication.run(BootProj1DependencyInjectionApplication.class, args);
     WishMessageGenerator generator = context.getBean("wmg", WishMessageGenerator.class);
     String Message = generator.generateWishMessage();
        System.out.println(Message);

     ((ConfigurableApplicationContext) context).close();
    }

}
