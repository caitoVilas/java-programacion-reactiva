package com.caito.apirestreactivaanotaciones.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HelloRouter {
    @Bean
    public RouterFunction<ServerResponse> functionalRouter(HelloHandler handler){
        return RouterFunctions
                .route(RequestPredicates.GET("/functional/mono"), handler::monoMessage)
                .andRoute(RequestPredicates.GET("/functional/flux"), handler::fluxMessage);
    }
}
