package com.project.eCommerce.Controller;

import com.project.eCommerce.Service.ProductService;
import com.project.eCommerce.entity.Product;
import com.project.eCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Page<Product> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable);

    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
      return  productService.getProductById(id);
    }

    @PostMapping("/createAsync")
    public ResponseEntity<String> createProductAsync(@RequestBody Product product) {
        productService.createProductAsync(product);
        return ResponseEntity.accepted().body("Product creation in progress");
    }

    /*@PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }*/

}

