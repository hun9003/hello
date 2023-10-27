package com.company.hello.controller;

import com.company.hello.service.MemberService;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/friends")
    public String friendsList(Model model, HttpSession session) {
        MemberVo currentUser = (MemberVo) session.getAttribute("member");
        if (currentUser != null) {
            List<String> friends = memberService.getFollowing(currentUser.getMemberId());
            model.addAttribute("friends", friends);
        }
        return "friends";
    }
}
