package com.taller_springflux.exame_webflux_01.service.impl;

import com.taller_springflux.exame_webflux_01.exception.ProductNotFoundException;
import com.taller_springflux.exame_webflux_01.model.Product;
import com.taller_springflux.exame_webflux_01.repository.ProductRepository;
import com.taller_springflux.exame_webflux_01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Flux<Product> getAllProducts(Double minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice)
                .filter(product -> product.getPrice() > 0)
                .map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                });
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                })
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with id: "+ id +" not found!")))
                .flatMap(Mono::just);
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        if (product.getPrice() <= 0) {
            return Mono.error(new ProductNotFoundException("The product must have a valid price"));
        }
        return Mono.just(product)
                //Mono<T> o Flux<T>
                .map(prod -> {
                    prod.setName(prod.getName().toUpperCase());
                    return prod;
                })
                //Mono<Publisher<T>> o Flux<Publisher<T>>
                .flatMap(productRepository::save);
    }
}
