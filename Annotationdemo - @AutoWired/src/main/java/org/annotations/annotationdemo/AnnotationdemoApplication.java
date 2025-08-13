package org.annotations.annotationdemo;

import org.annotations.annotationdemo.controller.GameController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnnotationdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AnnotationdemoApplication.class, args);
		GameController componentAnnotation =context.getBean(GameController.class);
		System.out.println(componentAnnotation.showAnnotation());
	}
}
