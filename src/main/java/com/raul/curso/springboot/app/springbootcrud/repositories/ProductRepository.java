package com.raul.curso.springboot.app.springbootcrud.repositories;

import com.raul.curso.springboot.app.springbootcrud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
