package com.reactive.springboot.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.reactive.springboot.dto.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String>{

}
