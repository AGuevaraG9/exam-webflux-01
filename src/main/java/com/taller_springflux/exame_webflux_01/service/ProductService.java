package com.taller_springflux.exame_webflux_01.service;

import com.taller_springflux.exame_webflux_01.model.Product;
import reactor.core.publisher.Flux;

public interface ProductService {
    Flux<Product> getAllProducts(Double minPrice);
}
