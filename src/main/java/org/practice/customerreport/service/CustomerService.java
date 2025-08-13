package org.practice.customerreport.service;

import org.practice.customerreport.modal.Customer;
import org.practice.customerreport.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("No Customer Found For ID"));
    }
    public void save(Customer customer) {
        customerRepo.save(customer);
    }
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }
    public List<Customer> findCustomersByNameEndsWith(String name) {
        return customerRepo.findCustomersByNameEndsWith(name);
    }
    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }
    public Customer updateCustomer(Long id,Customer customer) {
        Customer existing = findById(id);
        existing.setName(customer.getName());
        existing.setAddress(customer.getAddress());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        return customerRepo.save(existing);
    }
}
