package com.project.eCommerce.Service;

import com.project.eCommerce.repository.ProductRepository;
import com.project.eCommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

   @Async
    public CompletableFuture<Product> createProductAsync(Product product) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       System.out.println("Aync auth: "+auth);
        return CompletableFuture.completedFuture(productRepository.save(product));
    }
    /*public Product createProduct(Product product){
        return productRepository.save(product);

    }*/


    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }


}
