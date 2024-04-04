package com.caito.apirestreactivaanotaciones.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HelloHandler {

    public Mono<ServerResponse> monoMessage(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("bienvenido handler"),String.class);
    }

    public Mono<ServerResponse> fluxMessage(ServerRequest request){
        return ServerResponse.ok()
                //.contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Flux.just("Bienvenido ", "handler ", "flux ").delayElements(Duration.ofSeconds(1)),
                        String.class);
    }
}
