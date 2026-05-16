package com.sdlc.springcash.services;

import com.sdlc.springcash.entities.Product;
import com.sdlc.springcash.repository.ProductRepos;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
class CacheLogic{
    public boolean isCacheable(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
      return   auth.getAuthorities()
                .stream()
                .anyMatch(a->!"ROLE_ADMIN".equals(a.getAuthority()));

    }
}
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepos productRepos;
    @Cacheable(cacheNames = "product",key = "#id",condition = "@cacheLogic.isCacheable()")
    public Product getProduct(Integer id)
    {

        return productRepos.findById(id).orElseThrow();
    }
}
