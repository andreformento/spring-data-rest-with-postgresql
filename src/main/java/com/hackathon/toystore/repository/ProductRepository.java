package com.hackathon.toystore.repository;

import com.hackathon.toystore.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
