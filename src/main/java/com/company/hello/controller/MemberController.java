package com.company.hello.controller;

import com.company.hello.service.MemberService;
import com.company.hello.vo.MemberFollowVo;
import com.company.hello.vo.MemberInfoVo;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member") // 회원 관련
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) throws IOException {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo == null) return "redirect:/account/login";

        List<MemberInfoVo> list = memberService.findAll(memberVo.getMemberId());
        model.addAttribute("list", list);
        return "member/list";
    }

    @PostMapping("/follow")
    public String follow(HttpSession session, MemberFollowVo memberFollowVo) throws IOException {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo == null) return "redirect:/account/login";
        memberFollowVo.setMemberId(memberVo.getMemberId());
        memberService.doFollow(memberFollowVo);
        return "redirect:/member/list";
    }
}
