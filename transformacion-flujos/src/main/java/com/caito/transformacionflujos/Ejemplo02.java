package com.caito.transformacionflujos;

import reactor.core.publisher.Flux;

public class Ejemplo02 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Caito", "Rosana","Tamara","Martin"})
                .filter(name -> name.length() > 5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
