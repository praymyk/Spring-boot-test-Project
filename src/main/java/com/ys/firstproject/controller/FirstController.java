package com.ys.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/welcome")
    public String niceToMeetYou(Model model){

        model.addAttribute("username", "사용자");

        return "welcome";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){

        model.addAttribute("username", "사용자");
        return "bye";
    }
}
