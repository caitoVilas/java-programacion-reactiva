package com.caito.introduccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IntroduccionApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroduccionApplication.class, args);
        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();
        //creamos un mono
        Mono<Integer> mono = Mono.just(121);
        //creamos un flux
        Flux<Integer> flux = Flux.just(12,14,9,11,20,23,8,5,6,7);
        //nos suscribimos a mono
        mono.subscribe(elementosFromMono::add);
        flux.subscribe(elementosFromFlux::add);
        System.out.println(elementosFromMono);
        System.out.println(elementosFromFlux);
    }


}
