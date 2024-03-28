package com.caito.contrapresion;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {

    public static void main(String[] args) {
        Flux<String> ciudades = Flux.fromIterable(new ArrayList<>(
                Arrays.asList("Bs As", "New York", "Paris", "Roma", "Toronto")
        ));
        ciudades.log().subscribe();
    }
}
