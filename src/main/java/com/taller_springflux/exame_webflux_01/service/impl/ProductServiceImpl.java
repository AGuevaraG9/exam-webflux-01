package com.taller_springflux.exame_webflux_01.service.impl;

import com.taller_springflux.exame_webflux_01.model.Product;
import com.taller_springflux.exame_webflux_01.repository.ProductRepository;
import com.taller_springflux.exame_webflux_01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
        //42:43
    }
}
