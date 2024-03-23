package com.ys.firstproject.dto;

import com.ys.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {

    private Long id;
    private String email;
    private String password;

    public Member toMember(){
        return new Member(id, email, password);
    }
}
