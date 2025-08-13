package org.practice.bootproj2dependencyinjection;

import org.practice.bootproj2dependencyinjection.beans.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootProj2DependencyInjectionApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BootProj2DependencyInjectionApplication.class, args);
        Student student = context.getBean("student",Student.class);
        student.Preparation();
        ((ConfigurableApplicationContext) context).close();
    }

}
