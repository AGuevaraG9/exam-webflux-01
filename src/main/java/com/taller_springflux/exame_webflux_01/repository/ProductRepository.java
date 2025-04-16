package com.taller_springflux.exame_webflux_01.repository;

import com.taller_springflux.exame_webflux_01.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByPriceGreaterThan(Double minPrice);
}
