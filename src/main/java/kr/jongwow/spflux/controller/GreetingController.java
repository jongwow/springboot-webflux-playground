package kr.jongwow.spflux.controller;


import kr.jongwow.spflux.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService greetingService;

    @GetMapping("/greeting")
    public Mono<String> sayGreeting(@RequestParam String name) {
        return greetingService.getGreetingMessage(name);
    }
}
