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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/member/" + savedMember.getId();
    }

    /*
    *  회원 아이디를 이용한 단일 회원 조회 메소드 작성해보기
    */
    @GetMapping("/member/{id}")
    public String showMember(@PathVariable long id, Model model){

        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", member);

        return "member/show";
    }

    /**
     * 회원 전체 정보 조회용 메소드 작성해보기
     */
    @GetMapping("/members")
    public String index(Model model){

        // ArrayList로 형변환 하지 않고 ArticleRepository 클레스에서 오버라이딩을 통해 findAll() 메소드가 ArrayList를 반환
        ArrayList<Member> memberArrayList = memberRepository.findAll();
        model.addAttribute("member", memberArrayList);

        return "member/index";
    }

    /**
     * 회원정보 수정페이지 이동용 메서드
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/member/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Member memberEntity = memberRepository.findById(id).orElse(null);
        log.info(memberEntity.toString());
        model.addAttribute("member", memberEntity);

        return "member/edit";
    }

    /**
     * 회원정보 수정용 메서드
     * @param form
     * @return
     */
    @PostMapping("/member/update")
    public String update(MemberForm form){

        Member memberEntity = form.toMember();
        log.info(memberEntity.toString());

        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        log.info(target.toString());

        if(target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/member/" +memberEntity.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        //step1. 삭제 대상 찾기
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());

        //step2. 대상 확인시 삭제 처리 + 메시지 전송
        if(target != null){

            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제성공");
        }

        return "redirect:/members";
    }

}
