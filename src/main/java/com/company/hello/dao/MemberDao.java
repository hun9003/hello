package com.company.hello.dao;

import com.company.hello.vo.MemberFollowVo;
import com.company.hello.vo.MemberVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDao {

    private static final String FILE_PATH = "classpath:data/member.json";
    private static final String FILE_PATH_FOLLOW = "classpath:data/member_follow.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public MemberDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<MemberVo> findAll() throws IOException {
        File file = ResourceUtils.getFile(FILE_PATH);
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    public List<MemberFollowVo> findAllFollowList() throws IOException {
        File file = ResourceUtils.getFile(FILE_PATH_FOLLOW);
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    public List<MemberFollowVo> findFollowByMember(String memberId) throws IOException {
        List<MemberFollowVo> list = findAllFollowList();
        List<MemberFollowVo> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMemberId().equals(memberId)) result.add(list.get(i));
        }

        return result;
    }

    public MemberFollowVo findMemberFollow(MemberFollowVo request) throws IOException {
        List<MemberFollowVo> list = findAllFollowList(); // foreach
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(request.getMemberId())
                    && list.get(i).getFollowId().equals(request.getFollowId())) return list.get(i);
        }

//        for (MemberVo memberVo : list) {
//            if (memberVo.getMemberId().equals(memberId)) return memberVo;
//        }
        return null;
    }

    public void deleteFollow(MemberFollowVo request) throws IOException {
        List<MemberFollowVo> list = findAllFollowList(); // foreach
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(request.getMemberId())
                    && list.get(i).getFollowId().equals(request.getFollowId())) {
                list.remove(i);
                break;
            }
        }
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH_FOLLOW), list);
    }

    public void insertFollow(MemberFollowVo request) throws IOException {
        List<MemberFollowVo> list = findAllFollowList(); // foreach
        list.add(request);
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH_FOLLOW), list);
    }
}
