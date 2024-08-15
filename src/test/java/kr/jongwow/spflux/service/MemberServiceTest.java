package kr.jongwow.spflux.service;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

class MemberServiceTest {
    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    public void testGetMemberById() {
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");

        when(memberRepository.findById(1L)).thenReturn(Mono.just(member));

        Mono<Member> result = memberService.getMemberById(1L);

        StepVerifier.create(result)
                .expectNextMatches(m -> m.getName().equals("John Doe"))
                .verifyComplete();
    }

}