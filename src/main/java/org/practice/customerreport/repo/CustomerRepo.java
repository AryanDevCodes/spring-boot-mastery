package org.practice.customerreport.repo;

import org.practice.customerreport.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
List<Customer> findCustomersByNameEndsWith(String name);
}
