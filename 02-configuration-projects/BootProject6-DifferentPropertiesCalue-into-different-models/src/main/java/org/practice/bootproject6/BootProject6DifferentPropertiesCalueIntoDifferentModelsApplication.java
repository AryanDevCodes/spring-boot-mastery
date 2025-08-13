package org.practice.bootproject6;

import org.practice.bootproject6.beans.PropertiesTeams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootProject6DifferentPropertiesCalueIntoDifferentModelsApplication {

    public static void main(String[] args) {
       ApplicationContext context= SpringApplication.run(BootProject6DifferentPropertiesCalueIntoDifferentModelsApplication.class, args);
       PropertiesTeams teams=context.getBean("teamDetails",PropertiesTeams.class);
        System.out.println(teams);
    }

}
