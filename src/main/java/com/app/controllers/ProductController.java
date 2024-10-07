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

@Log4j2 @AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductDao productDao;

    @Cacheable("products")
    @GetMapping
    public ResponseEntity<?> findAll() {
        // TODO: add Thread sleep to simulate latency
        // Objects on fly are better 2 use than static calls.
        return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);
    }

    @CacheEvict(value = "products", allEntries = true)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> save(@RequestBody Product product) {
        return new ResponseEntity<>(productDao.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") int id) {
        productDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
