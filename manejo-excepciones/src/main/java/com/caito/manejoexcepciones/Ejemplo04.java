package com.caito.manejoexcepciones;

import reactor.core.publisher.Flux;

public class Ejemplo04 {
    public static void main(String[] args) {
        Flux.just(2,7,10,8,12,22,0,24)
                .map(element -> {
                    if (element == 8){
                        throw new RuntimeException("exception ocurred");
                    }
                    return element;
                })
                .onErrorMap(ex -> {
                    System.out.println("exception: " + ex);
                    return new CustomException(ex.getMessage(), ex);
                })
                .log()
                .subscribe();
    }
}

class CustomException extends Exception{
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
