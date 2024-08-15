package kr.jongwow.spflux.service;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Mono<Member> getMemberById(Long id){
        return memberRepository.findById(id);
    }

    public Flux<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Mono<Member> saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Mono<Void> deleteMemberById(Long id) {
        return memberRepository.deleteById(id);
    }
}
