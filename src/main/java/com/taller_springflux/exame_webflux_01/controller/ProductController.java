package com.taller_springflux.exame_webflux_01.controller;

import com.taller_springflux.exame_webflux_01.exception.ProductNotFoundException;
import com.taller_springflux.exame_webflux_01.model.Product;
import com.taller_springflux.exame_webflux_01.response.ErrorResponse;
import com.taller_springflux.exame_webflux_01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Flux<Product> getAllProducts(@RequestParam Double minPrice) {
        return productService.getAllProducts(minPrice);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok);
    }

    //Handler
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }
}
