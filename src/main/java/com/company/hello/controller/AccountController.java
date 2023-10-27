package com.company.hello.controller;

import com.company.hello.service.AccountService;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/account") // 계정 관련
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/join")
    public String formJoin() throws IOException {
        return "member/join";
    }

    @GetMapping("/login")
    public String formLogin() throws IOException {
        return "member/login";
    }

    @PostMapping("/join")
    public String join(MemberVo memberVo) throws Exception {
        accountService.join(memberVo);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(MemberVo memberVo, HttpSession session) {
        try {
            accountService.login(memberVo);
            session.setAttribute("member", memberVo);
        } catch (Exception e) {
            return "redirect:/account/login?status=fail";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member");
        return "redirect:/";
    }
}
