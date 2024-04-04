package com.caito.apirestreactivaanotaciones.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
@RequestMapping("/hello")
public class HelloControler {
    @GetMapping("/mono")
    public Mono<String> getMono(){
        return Mono.just("bienvenidos");
    }

    @GetMapping(value = "/flux")
    public Flux<String> getFlux(){
        Flux<String> message = Flux.just("BIENVENIDOS ", "FLUX")
                .delayElements(Duration.ofSeconds(1))
                .log();
        return message;
    }
}
