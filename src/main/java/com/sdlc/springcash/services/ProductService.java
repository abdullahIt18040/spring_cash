package com.sdlc.springcash.services;

import com.sdlc.springcash.entities.Product;
import com.sdlc.springcash.repository.ProductRepos;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepos productRepos;
    @Cacheable(cacheNames = "product")
    public Product getProduct(Integer id)
    {

        return productRepos.findById(id).orElseThrow();
    }
}
