package com.caito.introduccion;

import reactor.core.publisher.Mono;

public class Ejemplo02 {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("hola");
        //mono.subscribe(System.out::println);
        mono.subscribe(data -> System.out.println(data), //onNext
                       err -> System.out.println(err),//onError
                       () -> System.out.println("completo")); //onComplete
    }
}
