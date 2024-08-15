package kr.jongwow.spflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreetingService {
    public Mono<String> getGreetingMessage(String name) {
        return Mono.just("Hello, " + name + "!");
    }
}
