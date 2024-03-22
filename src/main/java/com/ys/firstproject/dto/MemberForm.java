package com.ys.firstproject.dto;

import com.ys.firstproject.entity.Member;

public class MemberForm {

    private String email;
    private String password;

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toMember(){
        return new Member(null, email, password);
    }
}
