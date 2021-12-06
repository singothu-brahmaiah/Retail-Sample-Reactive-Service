package com.reactive.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.springboot.model.ProductDtls;
import com.reactive.springboot.model.ProfileDetails;
import com.reactive.springboot.repository.ProductRepository;
import com.reactive.springboot.utils.AppUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private WebClient webClient;

	public Mono<ProductDtls> saveProduct(Mono<ProductDtls> productDtls) {
		return productDtls.map(AppUtils::modelToEntity).flatMap(repository::insert).map(AppUtils::entityToModel);
	}

	public Flux<ProductDtls> getProducts() {
		return repository.findAll().map(AppUtils::entityToModel);
	}

	public Mono<ProductDtls> getProduct(String id) {
		return repository.findById(id).map(AppUtils::entityToModel);
	}

	public Mono<ProductDtls> updateProduct(Mono<ProductDtls> productDtls, String id) {
		log.info("Updating product details into MongoDB");
		return repository.findById(id) // updating based on id
				.flatMap(p -> productDtls.map(AppUtils::modelToEntity)) // converting model to entity
				.doOnNext(c -> c.setId(id)) // keeping id as it is
				.flatMap(repository::save) // saving data
				.map(AppUtils::entityToModel);// last sending from entity to model
	}

	public Flux<ProductDtls> getProductInRange(double min, double max) {
		return repository.findByPriceBetween(Range.closed(min, max));
	}

	public Mono<Void> deleteProduct(String id) {
		return repository.deleteById(id);
	}

	public Mono<ProfileDetails> getProfile(String id) {
		log.info(" ProductService :: getProfile :: Entry - " + id );
		Mono<ProfileDetails> user = webClient.get()
				.uri("/"+id)
				//.accept(MediaType.TEXT_EVENT_STREAM)
				.retrieve()
				//.onStatus(httpStatus -> HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus),
		            //    clientResponse -> Mono.error(new BusinessException(clientResponse.statusCode().toString(), "Error in Item")))
				.bodyToMono(ProfileDetails.class);
				//.flatMap(i -> itemDao.getItemsByID(i.getId()))
				//.switchIfEmpty(Flux.error(new BusinessException("404", "Item not found")));
		log.info(" ProductService :: getItemsByUId :: Exit - " + id );
		return user;
	}

}
