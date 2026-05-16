package com.sdlc.springcash.repository;

import com.sdlc.springcash.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepos extends JpaRepository<Product,Integer> {


}
