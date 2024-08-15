package kr.jongwow.spflux.controller;

import kr.jongwow.spflux.domain.Member;
import kr.jongwow.spflux.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Mono<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public Flux<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public Mono<Member> createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMemberById(@PathVariable Long id) {
        return memberService.deleteMemberById(id);
    }
}