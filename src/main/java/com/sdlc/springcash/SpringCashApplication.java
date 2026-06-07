package com.sdlc.springcash;

import com.sdlc.springcash.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching

public class SpringCashApplication  implements CommandLineRunner {
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SpringCashApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

   var rs = productService.fetchProductById(1);
        System.out.println("abdullah al amamun ,,,,,,,,,,,,,,,,,,,,,,,1");
        System.out.println(productService.fetchProductById(1));
        System.out.println("abdullah al amamun ,,,,,,,,,,,,,,,,,,,,,,,2");
        System.out.println(productService.getProduct(2));
        System.out.println("abdullah al amamun ,,,,,,,,,,,,,,,,,,,,,,,3");
        System.out.println(productService.getProduct(2));
//
//
//        var rs1 = productService.getProduct(1);
//
//
//
//        System.out.println("product : "+rs1);

    }
}
