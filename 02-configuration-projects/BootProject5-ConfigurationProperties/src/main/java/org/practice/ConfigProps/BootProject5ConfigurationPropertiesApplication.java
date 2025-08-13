package org.practice.ConfigProps;

import org.practice.ConfigProps.beans.Company;
import org.practice.ConfigProps.beans.Company1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootProject5ConfigurationPropertiesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootProject5ConfigurationPropertiesApplication.class, args);
        Company company = context.getBean("company", Company.class);
        System.out.println(company + "\n--------------------------");

        // Fetch Company1 (Ensure application.properties has correct values)
        Company1 company1 = context.getBean("company1", Company1.class);
        System.out.println(company1);

    }
}
