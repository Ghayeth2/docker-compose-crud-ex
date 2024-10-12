package com.app.controllers;

import com.app.dao.ProductDao;
import com.app.models.Product;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2 @AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductDao productDao;


    /*
    the cache will serialize my response body, and ResponseEntity isn't meant to be serialized
    To avoid having error i need to cache a List<> not ResponseEntity
     */
    @Cacheable("products")
    @GetMapping
    public List<?> findAll() throws InterruptedException {
        Thread.sleep(2000);
        // TODO: add Thread sleep to simulate latency
        // Objects on fly are better 2 use than static calls.
        return productDao.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product product, @RequestParam("id") int id) {
        Optional<Product> productOptional = productDao.findById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity<>(productDao.save(
                    Product.builder()
                            .name(product.getName())
                            .price(product.getPrice())
                            .description(product.getDescription())
                            .id(productOptional.get().getId())
                            .build()
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CacheEvict(value = "products", allEntries = true)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        return new ResponseEntity<>(productDao.save(product), HttpStatus.CREATED);
    }

    @CacheEvict(value = "products", allEntries = true)
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") int id) {
        productDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
