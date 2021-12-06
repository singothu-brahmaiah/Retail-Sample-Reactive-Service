package com.reactive.webclient.api.service;

import org.springframework.stereotype.Service;

import com.reactive.webclient.api.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

	/*
	 * public Flux<Item> getItems() {
	 * 
	 * List<Item> productsList = new ArrayList<Item>(); productsList.add(new
	 * Item(2001, "car", 2)); productsList.add(new Item(2002, "cookies", 8));
	 * productsList.add(new Item(2003, "cooker", 1)); productsList.add(new
	 * Item(2004, "car", 3)); productsList.add(new Item(2005, "candy", 5));
	 * productsList.add(new Item(2006, "Cheez-It", 4)); productsList.add(new
	 * Item(2007, "Nuts", 3)); productsList.add(new Item(2008, "Sugar", 4));
	 * productsList.add(new Item(2009, "Pulses", 5)); productsList.add(new
	 * Item(2010, "Cereals", 6)); productsList.add(new Item(2011, "salsa", 7));
	 * 
	 * return Flux.fromIterable(productsList);
	 * 
	 * }
	 * 
	 * public Flux<Item> getItemsByID(Integer id) { return getItems().filter(p ->
	 * p.getOrderUserId().equals(id)); }
	 */

	/*
	 * public Flux<User> getUser() { //User u = new User(); return Flux.range(1, 6)
	 * //.delayElements(Duration.ofSeconds(1)) .doOnNext( i ->
	 * System.out.println("count in stream flow : " + i)) .map(i -> new User(i,
	 * "User-" + i, "982712311"+i++)); }
	 */

	public Flux<User> getUser() {
		// User u = new User();
		return Flux.range(1, 6)
				// .delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("count in stream flow : " + i)).map(i -> new User(i, "User-" + i));
	}

   public Mono<User> getUserId(String input){
		return getUser()
		 .filter(p -> p.getId() == Integer.valueOf(input))
		 //.doOnError((Consumer<? super Throwable>) new BusinessException(input))
		 //.switchIfEmpty( Mono.error(new BusinessException(HttpStatus.BAD_REQUEST.toString(), "Empty message")))
		 .next();
		

   }
}
