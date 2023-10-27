package com.company.hello.service;

import com.company.hello.dao.AccountDao;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void join(MemberVo request) throws Exception {
        // 이미 가입된 ID 있는지 확인
        MemberVo memberVo = accountDao.findMember(request.getMemberId());
        if (memberVo != null) throw new Exception("이미 가입된 회원이 존재합니다.");

        // 없으면 회원가입 처리
        accountDao.insertMember(request);
    }

    public void login(MemberVo memberVo) throws Exception {
        MemberVo member = accountDao.login(memberVo);
        if (member == null) throw new Exception("아이디 혹은 비밀번호가 틀립니다.");
    }
}
