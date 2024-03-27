package com.caito.introduccion;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class TestUsuario {

    public static void main(String[] args) {
        Flux<String> nombres = Flux.just("Caito Vilas", "Rosana Medrano", "Tamara Vilas", "Martin Vilas");
        Flux<Usuario> usuario = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(),
                        nombre.split(" ")[1].toUpperCase()))
                .doOnNext(uusario -> {
                    if (uusario == null)
                        throw new RuntimeException("los nombres no puden estar vacios");
                    log.info("usuario: ".concat(uusario.getName().concat(" ").concat(uusario.getSurname())));
                });
        usuario.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), new Runnable() {
            @Override
            public void run() {
                log.info("finalizado la ejecucion del observable");
            }
        });
    }


}
