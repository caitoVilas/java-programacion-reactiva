package com.caito.apirestreactivemongo.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ContactRouter {
    @Bean
    public RouterFunction<ServerResponse> ContactRouter(ContacHandler contacHandler){
        return RouterFunctions
                .route(GET("/functional"), contacHandler::getAll)
                .andRoute(GET("/functional/{id}"), contacHandler::getOne)
                .andRoute(GET("/functional/email/{email}"), contacHandler::getByEmail)
                .andRoute(POST("/functional"), contacHandler::create)
                .andRoute(PUT("/functional/{id}"), contacHandler::update)
                .andRoute(DELETE("/functional/{id}"), contacHandler::delete);
    }
}
