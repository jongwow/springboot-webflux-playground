package kr.jongwow.spflux.service;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public  class GreetingServiceTest {
    private final GreetingService greetingService = new GreetingService();

    @Test
    public void testGetGreetingMessage(){
        Mono<String> result = greetingService.getGreetingMessage("World");

        StepVerifier.create(result)
                .expectNext("Hello, World!")
                .verifyComplete();
    }
}