package com.coder.service;

import java.util.List;

import com.coder.model.Product;

public interface ProductService {

	public Product saveProduct(Product product);
	public List<Product> getAllProducts();
	public  Product updateProduct( Product product);
    public Product getProductById(Integer id);
    public void deleteProduct(Integer id);
}
