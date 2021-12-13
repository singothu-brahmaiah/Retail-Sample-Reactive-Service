package com.reactive.springboot.security;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class HealthCheck implements ReactiveHealthIndicator {
	
	@Override
    public Mono<Health> health() {
        return checkDownstreamHealth().onErrorResume(
          ex -> Mono.just(new Health.Builder().down(ex).build())
        );
    }

    private Mono<Health> checkDownstreamHealth() {
        // we could use WebClient to check health reactively
        return Mono.just(new Health.Builder().down().build());
    }

}
