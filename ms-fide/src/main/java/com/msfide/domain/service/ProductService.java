package com.msfide.domain.service;

import com.msfide.domain.Product;
import com.msfide.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll(){
        return repository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return repository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return repository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            repository.delete(productId);
            return true;
        }).orElse(false);
    }
}
