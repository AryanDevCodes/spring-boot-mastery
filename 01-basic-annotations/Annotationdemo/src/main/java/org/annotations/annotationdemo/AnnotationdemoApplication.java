package org.annotations.annotationdemo;

import org.annotations.annotationdemo.controller.Component_Annotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnnotationdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AnnotationdemoApplication.class, args);
		Component_Annotation componentAnnotation =context.getBean(Component_Annotation.class);
		System.out.println(componentAnnotation.showAnnotation());
	}
}
