package com.company.hello.controller;

import com.company.hello.service.MemberService;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/board") // 계정 관련
public class BoardController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public String list() throws IOException {
        return "board/list";
    }
}
