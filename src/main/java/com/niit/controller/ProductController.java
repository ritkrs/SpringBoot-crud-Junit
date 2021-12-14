package com.niit.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.exception.ItemNotFoundException;
import com.niit.model.Product;
import com.niit.service.ProductService;
//https://www.onlinetutorialspoint.com/spring-boot/spring-boot-exception-handling-rest-service-crud-operations.html
@RestController
public class ProductController {
 
    @Autowired
    private ProductService service;
     
    // RESTful API methods for Retrieval operations
    @GetMapping("/products")
    public List<Product> getList() {
        return service.listAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
    	if(id <= 0){
    		throw new ItemNotFoundException("Invalid Item");
        }
    	Product product=service.getById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    // RESTful API method for Create operation
    @PostMapping("/products")
    public void add(@RequestBody Product product) {
        service.save(product);
    }
    // RESTful API method for Update operation
    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
        try {
            Product existProduct = service.getById(id);
            service.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
     
    // RESTful API method for Delete operation
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}