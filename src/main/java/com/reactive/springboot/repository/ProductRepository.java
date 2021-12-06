package com.reactive.springboot.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.springboot.entity.Product;
import com.reactive.springboot.model.ProductDtls;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<ProductDtls> findByPriceBetween(Range<Double> priceRange);

}
