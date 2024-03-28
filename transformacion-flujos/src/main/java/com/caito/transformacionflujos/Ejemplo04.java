package com.caito.transformacionflujos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo04 {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("d", "e", "f");
        Flux<String> concat = Flux.concat(flux1, flux2);

        concat.subscribe(e -> System.out.println(e + " "));

        Mono<String> mono = Mono.just("g");
        Flux<String> concat2 = flux1.concatWith(mono);

        concat2.subscribe(e -> System.out.println(e + " "));
    }
}
