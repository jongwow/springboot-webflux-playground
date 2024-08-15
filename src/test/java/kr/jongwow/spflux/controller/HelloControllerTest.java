package kr.jongwow.spflux.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(HelloController.class)
class HelloControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldReturnDefaultMessage(){
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello World!");
    }
}