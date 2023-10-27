package com.company.hello.dao;

import com.company.hello.vo.MemberVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class AccountDao {

    private static final String FILE_PATH = "classpath:data/member.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public AccountDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<MemberVo> findAll() throws IOException {

        File file = ResourceUtils.getFile(FILE_PATH);
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    public MemberVo findMember(String memberId) throws IOException {
        List<MemberVo> list = findAll(); // foreach
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(memberId)) return list.get(i);
        }

//        for (MemberVo memberVo : list) {
//            if (memberVo.getMemberId().equals(memberId)) return memberVo;
//        }
        return null;
    }

    public void insertMember(MemberVo request) throws IOException {
        List<MemberVo> list = findAll();
        list.add(request);
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), list);
    }

    public MemberVo login(MemberVo memberVo) throws IOException {
        List<MemberVo> list = findAll(); // foreach
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(memberVo.getMemberId())
            && list.get(i).getMemberPassword().equals(memberVo.getMemberPassword())) return list.get(i);
        }
        return null;
    }
}
