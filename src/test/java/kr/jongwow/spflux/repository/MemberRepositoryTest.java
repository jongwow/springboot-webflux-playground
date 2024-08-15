package kr.jongwow.spflux.repository;

import kr.jongwow.spflux.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataR2dbcTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll().block();  // 테스트 전 모든 데이터를 삭제
    }

    @Test
    public void testSaveAndFindMember() {
        Member member = new Member();
        member.setName("Jay Doe");
        member.setEmail("jay.doe@example.com");

        Member savedMember = memberRepository.save(member).block();
        StepVerifier.create(memberRepository.findById(savedMember.getId()))  // 그 이후로는 단일 값 사용
                .assertNext(m -> {
                    assert m.getName().equals("Jay Doe");
                    assert m.getEmail().equals("jay.doe@example.com");
                })
                .verifyComplete();

    }

    @Test
    public void testGetAllMembers() {
        Flux<Member> savedMembers = memberRepository.findAll();
        System.out.println("Hello World1");
        List<Member> listMono = savedMembers.collectList().block();
        listMono.stream().forEach(m -> System.out.println(m.toString()));
        System.out.println("Hello World2");
    }

}