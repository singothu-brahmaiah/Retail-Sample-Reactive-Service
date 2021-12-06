package com.reactive.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.springboot.model.AuthRequest;
import com.reactive.springboot.model.AuthResponse;
import com.reactive.springboot.security.JWTUtil;
import com.reactive.springboot.security.PBKDF2Encoder;
import com.reactive.springboot.service.UserService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationController {

	private JWTUtil jwtUtil;
	private PBKDF2Encoder passwordEncoder;
	private UserService userService;

	@PostMapping("/login")
	public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
		return userService.findByUsername(request.getUsername())
				.filter(userDetails -> passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword()))
				.map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}

}
