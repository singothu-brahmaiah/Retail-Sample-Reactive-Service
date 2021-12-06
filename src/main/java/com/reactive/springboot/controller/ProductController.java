package com.reactive.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.springboot.model.ProductDtls;
import com.reactive.springboot.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/saveProducts")
	public ResponseEntity<Mono<ProductDtls>> saveProduct(@RequestBody Mono<ProductDtls> productDtls) {
		log.info("Save product details- called saveProduct method");
		Mono<ProductDtls> insertData = productService.saveProduct(productDtls);
		return new ResponseEntity<Mono<ProductDtls>>(insertData, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getProduct", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<ProductDtls>> getProducts() {
		log.info("Fetch the all product details - Called getProducts");
		Flux<ProductDtls> productDtls = productService.getProducts();
		return new ResponseEntity<Flux<ProductDtls>>(productDtls, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Mono<ProductDtls>> getProduct(@PathVariable String id) {
		log.info("Fetch the product detail - Called getProduct");
		Mono<ProductDtls> productDtls = productService.getProduct(id);
		return new ResponseEntity<Mono<ProductDtls>>(productDtls, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public Mono<ProductDtls> updateCustomer(@RequestBody Mono<ProductDtls> productDtls, @PathVariable String id) {
		log.info("Updating customer details in Mongodb");
		return productService.updateProduct(productDtls, id);
	}

	@GetMapping(value = "/product-range", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<ProductDtls>> getProductBetweenRange(@RequestParam("min") double min,
			@RequestParam("max") double max) {
		log.info("Fetch the product detail in specific range - Called getProductBetweenRange");
		Flux<ProductDtls> productRange = productService.getProductInRange(min, max);
		return new ResponseEntity<Flux<ProductDtls>>(productRange, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Mono<Void>> deleteProduct(@PathVariable String id) {
		log.info("Delete the product - Called deleteProduct");
		Mono<Void> productId = productService.deleteProduct(id);
		return new ResponseEntity<Mono<Void>>(productId, HttpStatus.ACCEPTED);
	}

}
