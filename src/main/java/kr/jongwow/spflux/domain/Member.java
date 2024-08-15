package kr.jongwow.spflux.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("members")
public class Member {
    @Id
    private Long id;
    private String name;
    private String email;
}
