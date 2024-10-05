package com.coder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.model.Product;
import com.coder.repository.ProductRepository;
import com.coder.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product>  allProduct = productRepository.findAll();
		return allProduct;
	}

	@Override
	public Product updateProduct(Product product) {
//		Product updateProduct = productRepository.save(product);
//		return null;
		Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
           
            return productRepository.save(product);
        } else {
            
            throw new RuntimeException("Product with ID " + product.getId() + " not found.");
        }
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<Product> findByProduct = productRepository.findById(id);
		if(findByProduct.isPresent()) {
			return findByProduct.get();
		}
		return null;
	}

	@Override
	public void deleteProduct(Integer id) {
		Optional<Product> findByProductDelete = productRepository.findById(id);
		if(findByProductDelete.isPresent()) {
			Product product = findByProductDelete.get();
			productRepository.deleteById(id);
		}
	}

}
