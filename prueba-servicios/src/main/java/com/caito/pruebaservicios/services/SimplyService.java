package com.caito.pruebaservicios.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SimplyService {

    public Mono<String> findOne(){
        return Mono.just("hola");
    }

    public Flux<String> findAll(){
        return Flux.just("hola", "que tal", "saludos");
    }

    public Flux<String> findAllAsync(){
        return Flux.just("hola", "que tal", "saludos").delaySequence(Duration.ofSeconds(10));
    }
}
