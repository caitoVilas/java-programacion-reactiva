package com.caito.pruebaservicios;

import com.caito.pruebaservicios.services.SimplyService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest

public class PruebaServiciosApplicationTests {
	@Autowired
	private SimplyService simplyService;

    public PruebaServiciosApplicationTests() {
        simplyService = new SimplyService();
    }

    @Test
	public void testMono(){
		Mono<String> mono = simplyService.findOne();
		StepVerifier.create(mono)
				.expectNext("hola")
				.verifyComplete();
	}

	@Test
	public void testFLux(){
		Flux<String> flux = simplyService.findAll();
		StepVerifier.create(flux)
				.expectNext("hola")
				.expectNext("que tal")
				.expectNext("saludos")
				.verifyComplete();
	}

	@Test
	public void testAsync(){
		Flux<String> flux = simplyService.findAllAsync();
		StepVerifier.create(flux)
				.expectNext("hola")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("que tal")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("saludos")
				.thenAwait(Duration.ofSeconds(1))
				.verifyComplete();
	}
}
