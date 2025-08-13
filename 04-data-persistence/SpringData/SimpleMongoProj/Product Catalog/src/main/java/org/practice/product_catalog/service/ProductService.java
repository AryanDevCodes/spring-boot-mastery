package org.practice.product_catalog.service;


import org.practice.product_catalog.modal.Product;
import org.practice.product_catalog.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Product Not Found"));
    }

    public List<Product> searchProduct(String keyword) {
        return productRepo.findByNameContaining(keyword);
    }
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

}
