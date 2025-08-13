package org.practice.product_catalog.controller;

import org.practice.product_catalog.modal.Product;
import org.practice.product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // List all products or search
    @GetMapping
    public String listProducts(Model model, @RequestParam(required = false) String search) {
        List<Product> productList = (search != null && !search.isEmpty()) ?
                productService.searchProduct(search) :
                productService.getAllProducts();

        model.addAttribute("products", productList);  // Match Thymeleaf `${products}`
        model.addAttribute("search", search);         // Preserve search value in input
        return "product/list";
    }

    // Show product details
    @GetMapping("/{id}")
    public String showProduct(Model model, @PathVariable long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/details";
    }

    // Show form for new product
    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    // Save a new product
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/form";
        }
        productService.saveProduct(product);
        return "redirect:/products";  // Corrected path from /product → /products
    }
}
