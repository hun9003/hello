package com.company.hello.service;

import com.company.hello.dao.MemberDao;
import com.company.hello.vo.MemberFollowVo;
import com.company.hello.vo.MemberInfoVo;
import com.company.hello.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public List<MemberInfoVo> findAll(String memberId) throws IOException {
        List<MemberVo> list = memberDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(memberId)) {
                list.remove(i);
                break;
            }
        }

        List<MemberFollowVo> followVoList = memberDao.findFollowByMember(memberId);
        List<MemberInfoVo> infoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MemberInfoVo info = new MemberInfoVo();
            info.setMemberId(list.get(i).getMemberId());
            for (int j = 0; j < followVoList.size(); j++) {
                if (list.get(i).getMemberId().equals(followVoList.get(j).getFollowId())) {
                    info.setFollow(true);
                    break;
                }
            }
            infoList.add(info);
        }
        return infoList;
    }

    public void doFollow(MemberFollowVo request) throws IOException {
        MemberFollowVo memberFollowVo = memberDao.findMemberFollow(request);
        if (memberFollowVo != null) {
            // 현재 팔로우중이므로 취소하는 로직
            memberDao.deleteFollow(request);
        } else {
            // 팔로우하는 로직
            memberDao.insertFollow(request);
        }
    }
}
