package kr.jongwow.spflux.controller;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MemberService memberService;

    @Test
    public void testGetMemberById() {
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");

        when(memberService.getMemberById(1L)).thenReturn(Mono.just(member));

        webTestClient.get().uri("/members/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Member.class)
                .value(response -> {
                    assert response.getName().equals("John Doe");
                    assert response.getEmail().equals("john.doe@example.com");
                });
    }
}