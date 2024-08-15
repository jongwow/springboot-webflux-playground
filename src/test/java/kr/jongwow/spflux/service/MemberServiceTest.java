package kr.jongwow.spflux.service;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private MemberService memberService;

    @Captor
    ArgumentCaptor<Member> valueCaptor;

    @Captor
    ArgumentCaptor<String> keyCaptor;


    @Test
    public void shouldReturnMemberWhenExists() {
        Member member = new Member();
        member.setId(1L);
        member.setName("AAA Doe");
        member.setEmail("AAA.doe@example.com");

        when(memberRepository.findById(1L)).thenReturn(Mono.just(member));

        Mono<Member> result = memberService.getMemberById(1L);

        StepVerifier.create(result)
                .expectNextMatches(m -> m.getName().equals("AAA Doe"))
                .verifyComplete();
    }

    @Test
    public void testCreateMemberAndSendKafkaMessage() {
        Member member = new Member();
        member.setId(1L);
        member.setName("BBB Doe");
        member.setEmail("BBB.doe@example.com");

        when(memberRepository.save(member)).thenReturn(Mono.just(member));

        memberService.saveMember(member).block();

        verify(kafkaTemplate, times(1)).send(eq("member.creation"), keyCaptor.capture(), valueCaptor.capture());

        assert keyCaptor.getValue().equals(member.getId().toString());
        assert valueCaptor.getValue().equals(member);
    }
}