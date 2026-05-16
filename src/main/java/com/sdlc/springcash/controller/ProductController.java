package com.sdlc.springcash.controller;

import com.sdlc.springcash.entities.Product;
import com.sdlc.springcash.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{id}")
    public Product fetchProduct(@PathVariable("id") int id)
    {

       return productService.getProduct(id);
    }


}
