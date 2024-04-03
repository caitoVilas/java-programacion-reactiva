package com.caito.manejoexcepciones;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {
        Flux.just(2,7,10,8,12,22,0,24)
                .map(element -> {
                    if (element == 0){
                        throw new RuntimeException("exception ocurred");
                    }
                    return element;
                })
                .onErrorContinue((ex, element) -> {
                    System.out.println("exception: " + ex);
                    System.out.println("elemento que causa la excepcion es: " + element);
                })
                .log()
                .subscribe();
    }
}
