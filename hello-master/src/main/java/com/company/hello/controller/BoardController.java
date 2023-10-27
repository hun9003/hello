package com.company.hello.controller;

import com.company.hello.service.MemberService;
import com.company.hello.service.PostService;
import com.company.hello.vo.MemberVo;
import com.company.hello.vo.PostVo;
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
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private MemberService memberService;

    @Autowired  // 추가
    private PostService postService;

    @PostMapping("/createPost")
    public String createPost(PostVo postVo, HttpSession session) {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo != null) {
            try {
                postVo.setAuthorId(memberVo.getMemberId());
                postService.createPost(postVo);  // 게시글 저장
            } catch (IOException e) {
                // 예외 처리 로직 추가
                e.printStackTrace(); // 예외 처리 방법은 필요에 따라 변경 가능
            }
        }
        return "redirect:/board/list";
    }


    @GetMapping("/list")
    public String list(Model model, HttpSession session) throws IOException {
        MemberVo memberVo = (MemberVo) session.getAttribute("member");
        if (memberVo != null) {
            List<PostVo> posts = postService.getAllPostsForFeed(memberVo.getMemberId());
            model.addAttribute("posts", posts);
        }
        return "board/list";
    }
}
