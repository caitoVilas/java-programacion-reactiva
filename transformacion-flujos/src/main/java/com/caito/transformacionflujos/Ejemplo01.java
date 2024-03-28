package com.caito.transformacionflujos;

import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Caito", "Rosana","Tamara","Martin"})
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
