package com.caito.pruebasstepverifier;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemploTest {

    @Test
    public void testFlux(){
        Flux<Integer> fluxToTest = Flux.just(1,2,3,4,5);
        StepVerifier.create(fluxToTest)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void testFluxString(){
        Flux<String> fluxToTest = Flux.just("Caito", "Rosana", "Tamara", "Martin")
                .filter(nombre -> nombre.length()<= 5)
                .map(String::toUpperCase);
        StepVerifier.create(fluxToTest)
                .expectNext("CAITO")
                .expectComplete()
                .verify();

    }
}
