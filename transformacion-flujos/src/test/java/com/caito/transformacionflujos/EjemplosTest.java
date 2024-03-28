package com.caito.transformacionflujos;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class EjemplosTest {

    @Test
    public void transformMap() {
        List<String> name = Arrays.asList("google", "facebook", "linkedin", "github");
        Flux<String> flux = Flux.fromIterable(name)
                .filter(n -> n.length() > 5)
                .map(n -> n.toUpperCase())
                .log();

        StepVerifier.create(flux)
                .expectNext("GOOGLE", "FACEBOOK", "LINKEDIN", "GITHUB")
                .expectComplete();
    }

    @Test
    public void transformFlapMap(){
        List<String> name = Arrays.asList("google", "facebook", "linkedin", "github");
        Flux<String> flux = Flux.fromIterable(name)
                .filter(n -> n.length() > 5)
                .flatMap(n -> Mono.just(n.toUpperCase()))
                .log();
        StepVerifier.create(flux)
                .expectNext("GOOGLE", "FACEBOOK", "LINKEDIN", "GITHUB")
                .expectComplete();
    }

    @Test
    public void combinarFlujos(){
        Flux<String> flux1 = Flux.just("Blenders", "Old", "Johnnie");
        Flux<String> flux2 = Flux.just("Pride", "Monk", "Walker");

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

        StepVerifier.create(fluxMerge)
                .expectNext("Blenders", "Old", "Johnnie","Pride",  "Monk",  "Walker")
                .verifyComplete();

    }

    @Test
    public void combinarFlujosDelayConcat(){
        Flux<String> flux1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = Flux.concat(flux1, flux2).log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void combinarFlujosDelayZip(){
        Flux<String> flux1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = Flux.zip(flux1, flux2, (f1, f2) -> {
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier.create(fluxMerge)
                .expectNext("Blenders Pride", "Old Monk", "Johnnie Walker")
                .verifyComplete();
    }
}
