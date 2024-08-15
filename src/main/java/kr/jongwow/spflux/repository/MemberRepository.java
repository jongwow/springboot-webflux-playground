package kr.jongwow.spflux.repository;

import kr.jongwow.spflux.domain.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemberRepository extends ReactiveCrudRepository<Member, Long> {
}
