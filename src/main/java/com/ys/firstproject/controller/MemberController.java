package com.ys.firstproject.controller;

import com.ys.firstproject.dto.MemberForm;
import com.ys.firstproject.entity.Member;
import com.ys.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup(){
        return "member/newmember";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm m){

        Member member = m.toMember();
        Member savedMember = memberRepository.save(member);

        return "";
    }
}
