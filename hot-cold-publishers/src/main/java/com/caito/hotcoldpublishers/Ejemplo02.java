package com.caito.hotcoldpublishers;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Ejemplo02 {
    // HOT PUBLISHERS
    public static void main(String[] args) throws InterruptedException {
        Flux<String> netFlux = Flux.fromStream(Ejemplo02::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .share(); //esto lo convierte en share
        netFlux.subscribe(part -> System.out.println("suscriber 1:" + part));
        Thread.sleep(5000);
        netFlux.subscribe(part -> System.out.println("suscriber 2:" + part));
        Thread.sleep(60000);
    }

    private static Stream<String> getVideo(){
        System.out.println("request para el video");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }
}
