package com.raul.curso.springboot.app.springbootcrud.services;

import com.raul.curso.springboot.app.springbootcrud.entities.Product;
import com.raul.curso.springboot.app.springbootcrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> productDB = productRepository.findById(id);
        productDB.ifPresent(prod ->{
            productRepository.delete(prod);
        });
        return productDB;
    }
    
    @Transactional
	@Override
	public Optional<Product> update(Long id, Product product) {
    	Optional<Product> productDB = productRepository.findById(id);
        if(productDB.isPresent()){
        	Product prod = productDB.orElseThrow();
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            return Optional.of(productRepository.save(prod)) ;
        }
        return productDB;
	}
}
