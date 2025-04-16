package com.taller_springflux.exame_webflux_01.controller;

import com.taller_springflux.exame_webflux_01.model.Product;
import com.taller_springflux.exame_webflux_01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Flux<Product> getAllProducts(@RequestParam Double minPrice) {
        return productService.getAllProducts(minPrice);
    }
}
