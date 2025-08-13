package org.practice.customerreport.controller;

import org.practice.customerreport.modal.Customer;
import org.practice.customerreport.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/customerDetails")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/")
    public String customerDetails(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("customer", new Customer()); // For form binding
        return "customers"; // e.g., "index" or "customers"
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("toastMessage", "Customer added successfully!");
        return "redirect:/customerDetails/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.deleteById(id);
        redirectAttributes.addFlashAttribute("toastMessage", "Customer deleted successfully!");
        return "redirect:/customerDetails/";
    }

    @GetMapping("/edit/{id}")
    public String updateCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "edit";
    }
    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("toastMessage", "Customer updated successfully!");
        return "redirect:/customerDetails/";
    }
    @GetMapping("/view")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }


}
