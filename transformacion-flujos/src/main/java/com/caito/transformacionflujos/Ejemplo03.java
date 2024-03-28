package com.caito.transformacionflujos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Caito", "Rosana","Tamara","Martin"})
                .flatMap(Ejemplo03::modificaNombre)
                .subscribe(System.out::println);
    }

    private static Mono<String> modificaNombre(String nombre){
        return Mono.just(nombre.concat(" modificado"));
    }
}
