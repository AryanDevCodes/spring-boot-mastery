package org.practice.customerreport.modal;

import org.practice.customerreport.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(CustomerService customerService) {
        return args -> {
            customerService.save(new Customer("Aryan Raj", "Delhi", 9876543210L, "aryan.raj@example.com"));
            customerService.save(new Customer("John Doe", "New York", 9876543211L, "john.doe@example.com"));
            customerService.save(new Customer("Jane Smith", "London", 9876543212L, "jane.smith@example.com"));
            customerService.save(new Customer("Emily Davis", "Berlin", 9876543213L, "emily.davis@example.com"));

            for (int i = 1; i <= 50; i++) {
                customerService.save(new Customer(
                        "Customer " + i,
                        "City " + i,
                        9000000000L + i,
                        "customer" + i + "@example.com"
                ));
            }
        };
    }
}
