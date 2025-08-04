package com.jhorma.java.springboot.backend.repositories;

import com.jhorma.java.springboot.backend.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
