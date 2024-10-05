package com.coder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.model.Product;
import com.coder.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
      Product productId = productService.getProductById(id);
      if(ObjectUtils.isEmpty(productId)) {
    	  return new ResponseEntity<>("product not found",HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>(productId,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
    	 Product saveProduct =  productService.saveProduct(product);
    	 if(ObjectUtils.isEmpty(saveProduct)) {
    		 return new ResponseEntity<>("Product Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
    	 }
    	 return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
    	 Product productId = productService.getProductById(id);
         if(ObjectUtils.isEmpty(productId)) {
       	  return new ResponseEntity<>("product not found",HttpStatus.INTERNAL_SERVER_ERROR);
         }
         return ResponseEntity.ok(productService.updateProduct(productDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new  ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK);
    }
}

