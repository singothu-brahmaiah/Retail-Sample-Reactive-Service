
package com.reactive.springboot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.springboot.model.ProductDtls;
import com.reactive.springboot.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Metrc project
 *
 */

@RunWith(SpringRunner.class)

@WebFluxTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService service;

	/**
	 * Test case for save product details into MongoDB
	 */

	@Test
	public void saveProductTest() {
		Mono<ProductDtls> productDtls = Mono.just(new ProductDtls("102", "Kiwi", 20, 300.12));
		when(service.saveProduct(productDtls)).thenReturn(productDtls);

		webTestClient.post().uri("/product/saveProducts").body(Mono.just(productDtls), ProductDtls.class).exchange()
				.expectStatus().isOk();
	}

	/**
	 * Test case for fetching all products from MongoDB
	 */

	@Test
	public void getProductsTest() {
		Flux<ProductDtls> productDtls = Flux.just(new ProductDtls("203", "Pizza", 2, 50000),
				new ProductDtls("204", "Cake", 3, 60000));

		when(service.getProducts()).thenReturn(productDtls);

		Flux<ProductDtls> responseBody = webTestClient.get().uri("/product").exchange().expectStatus().isOk()
				.returnResult(ProductDtls.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNext(new ProductDtls("203", "Pizza", 2, 50000))
				.expectNext(new ProductDtls("204", "Cake", 3, 60000)).verifyComplete();

	}

	/**
	 * Test case for getting single Product by using specific id
	 */

	@Test
	public void getProductTest() {
		Mono<ProductDtls> productDtls = Mono.just(new ProductDtls("203", "PizzaCake", 2, 50000));

		when(service.getProduct(any())).thenReturn(productDtls);

		Flux<ProductDtls> responseBody = webTestClient.get().uri("/product/203").exchange().expectStatus().isOk()
				.returnResult(ProductDtls.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNextMatches(p -> p.getName().equals("PizzaCake"))
				.verifyComplete();
	}

	/**
	 * Test case for update product details
	 */
	@Test
	public void updateProductTest() {
		Mono<ProductDtls> productDtls = Mono.just(new ProductDtls("203", "Pizza", 2, 50000));
		when(service.updateProduct(productDtls, "203")).thenReturn(productDtls);

		webTestClient.put().uri("/product/update/203").body(Mono.just(productDtls), ProductDtls.class).exchange()
				.expectStatus().isOk();
	}

}
