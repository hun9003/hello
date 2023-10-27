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
import java.util.ArrayList;

@Repository
public class MemberDao {

    private static final String FILE_PATH = "classpath:data/member.json";
    private final ObjectMapper objectMapper;

    @Autowired
    public MemberDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<MemberVo> findAll() throws IOException {
        File file = ResourceUtils.getFile(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<MemberVo>>() {
        });
    }

    public MemberVo findMember(String memberId) throws IOException {
        List<MemberVo> list = findAll();
        for (MemberVo memberVo : list) {
            if (memberVo.getMemberId().equals(memberId)) return memberVo;
        }
        return null;
    }

    public void insertMember(MemberVo request) throws IOException {
        List<MemberVo> list = findAll();
        list.add(request);
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), list);
    }

    public void updateMember(MemberVo memberToUpdate) throws IOException {
        List<MemberVo> list = findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(memberToUpdate.getMemberId())) {
                list.set(i, memberToUpdate);
                break;
            }
        }
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), list);
    }

    public void follow(String memberId, String targetId) throws IOException {
        MemberVo member = findMember(memberId);
        if (member.getFollowing() == null) {
            member.setFollowing(new ArrayList<>());
        }
        member.getFollowing().add(targetId);
        updateMember(member);
    }

    public void addFollower(String followerId, String followeeId) throws IOException {
        MemberVo follower = findMember(followerId);
        MemberVo followee = findMember(followeeId);

        if (follower != null && followee != null) {
            // 팔로우하는 사용자 목록에 팔로우 대상을 추가
            if (follower.getFollowing() == null) {
                follower.setFollowing(new ArrayList<>());
            }
            follower.getFollowing().add(followeeId);

            // 업데이트된 정보를 저장
            updateMember(follower);
        }
    }

    public void unfollow(String memberId, String targetId) throws IOException {
        MemberVo member = findMember(memberId);
        if (member.getFollowing() != null) {
            member.getFollowing().remove(targetId);
        }
        updateMember(member);
    }

    public MemberVo login(MemberVo memberVo) throws IOException {
        List<MemberVo> list = findAll();
        for (MemberVo member : list) {
            if (member.getMemberId().equals(memberVo.getMemberId())
                    && member.getMemberPassword().equals(memberVo.getMemberPassword())) return member;
        }
        return null;
    }
}
