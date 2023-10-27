package com.company.hello.controller;

import com.company.hello.service.PeedService;
import com.company.hello.vo.MemberVo;
import com.company.hello.vo.PeedInfoVo;
import com.company.hello.vo.PeedVo;
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
@RequestMapping("/board") // 계정 관련
public class BoardController {

    @Autowired
    private PeedService peedService;

    @GetMapping("/list")
    public String list(Model model, HttpSession session) throws IOException {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo == null) return "redirect:/account/login";
        List<PeedInfoVo> list = peedService.findPeedList(memberVo.getMemberId());
        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping("/write")
    public String writeForm() throws IOException {
        return "board/form";
    }

    @PostMapping("/write")
    public String write(HttpSession session, PeedVo peedVo) throws IOException {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo == null) return "redirect:/account/login";
        peedVo.setMemberId(memberVo.getMemberId());
        peedService.writePeed(peedVo);
        return "redirect:/board/list";
    }
}
