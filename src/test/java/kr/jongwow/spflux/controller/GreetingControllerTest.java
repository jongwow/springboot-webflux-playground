package kr.jongwow.spflux.controller;


import kr.jongwow.spflux.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
@WebFluxTest(GreetingController.class)
class GreetingControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private GreetingService greetingService;

    @Test
    public void shouldReturnGreetingMessage(){
        when(greetingService.getGreetingMessage("John")).thenReturn(Mono.just("Hello, John!"));

        webTestClient.get().uri("/greeting?name=John")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, John!");
    }
}