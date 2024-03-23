package com.ys.firstproject.controller;

import com.ys.firstproject.dto.MemberForm;
import com.ys.firstproject.entity.Member;
import com.ys.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
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
        log.info(member.toString());
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return "";
    }

    /*
    *  회원 아이디를 이용한 단일 회원 조회 메소드 작성해보기
    */
    @GetMapping("/members/{id}")
    public String showMember(@PathVariable long id, Model model){

        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", member);

        return "";
    }

    /**
     * 회원 전체 정보 조회용 메소드 작성해보기
     */
    @GetMapping("/members")
    public String index(Model model){

        // ArrayList로 형변환 하지 않고 ArticleRepository 클레스에서 오버라이딩을 통해 findAll() 메소드가 ArrayList를 반환
        ArrayList<Member> memberArrayList = memberRepository.findAll();

        return "";
    }

}
