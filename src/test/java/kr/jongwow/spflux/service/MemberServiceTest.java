package kr.jongwow.spflux.service;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class MemberServiceTest {
    private MemberRepository memberRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    private MemberService memberService;


    @Captor
    ArgumentCaptor<Member> valueCaptor;
    @Captor
    ArgumentCaptor<String> keyCaptor;

    @BeforeEach
    public void setUP() {
        MockitoAnnotations.openMocks(this);
        memberRepository = mock(MemberRepository.class);
        kafkaTemplate = mock(KafkaTemplate.class);
        memberService = new MemberService(memberRepository, kafkaTemplate);
    }

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

    @Test
    public void testCreateMemberAndSendKafkaMessage() {
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setId(100L);

        when(memberRepository.save(member)).thenReturn(Mono.just(member));

        memberService.saveMember(member).block();

        verify(kafkaTemplate, times(1)).send(eq("member.creation"), keyCaptor.capture(), valueCaptor.capture());

        assert keyCaptor.getValue().equals(member.getId().toString());
        assert valueCaptor.getValue().equals(member);
    }
}