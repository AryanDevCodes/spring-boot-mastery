package org.practice.product_catalog.modal;

import org.practice.product_catalog.repo.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class ProductSeeder {

    @Bean
    CommandLineRunner seedDatabase(ProductRepo repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Product("Laptop", 75000.0),
                        new Product("Smartphone", 30000.0),
                        new Product("Keyboard", 1500.0),
                        new Product("Monitor", 12000.0)
                ));
            }
        };
    }
}
