package com.company.hello.service;

import com.company.hello.dao.MemberDao;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public List<MemberVo> getAllMembers() throws IOException {
        return memberDao.findAll();
    }

    public void join(MemberVo request) throws Exception {
        // 이미 가입된 ID 있는지 확인
        MemberVo memberVo = memberDao.findMember(request.getMemberId());
        if (memberVo != null) throw new Exception("이미 가입된 회원이 존재합니다.");

        // 없으면 회원가입 처리
        memberDao.insertMember(request);
    }

    public MemberVo login(MemberVo memberVo) throws Exception {
        MemberVo member = memberDao.login(memberVo);
        if (member == null) throw new Exception("아이디 혹은 비밀번호가 틀립니다.");
        return member;
    }

    public void follow(String memberId, String targetId) throws Exception {
        MemberVo memberVo = memberDao.findMember(memberId);
        if (memberVo.getFollowing().contains(targetId)) {
            throw new Exception("이미 팔로우 중입니다.");
        }
        memberDao.follow(memberId, targetId);
    }

    public void unfollow(String memberId, String targetId) throws Exception {
        MemberVo memberVo = memberDao.findMember(memberId);
        if (!memberVo.getFollowing().contains(targetId)) {
            throw new Exception("팔로우 중이 아닙니다.");
        }
        memberDao.unfollow(memberId, targetId);
    }

    public List<String> getFollowing(String memberId) throws IOException {
        MemberVo memberVo = memberDao.findMember(memberId);
        if (memberVo == null) {
            // 회원을 찾을 수 없는 경우 예외를 throw
            throw new IOException("회원을 찾을 수 없습니다."); // 예외 처리 방법은 필요에 따라 변경 가능
        }
        return memberVo.getFollowing();
    }
}