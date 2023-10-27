package com.company.hello.service;

import com.company.hello.dao.MemberDao;
import com.company.hello.dao.PeedDao;
import com.company.hello.vo.MemberFollowVo;
import com.company.hello.vo.PeedInfoVo;
import com.company.hello.vo.PeedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class PeedService {

    @Autowired
    private PeedDao peedDao;

    @Autowired
    private MemberDao memberDao;

    public void writePeed(PeedVo peedVo) throws IOException  {
        peedVo.setPostId(String.valueOf(UUID.randomUUID()));
        peedVo.setCreatedAt(LocalDateTime.now().toString());
        peedVo.setUpdatedAt(LocalDateTime.now().toString());
        peedDao.insertPeed(peedVo);
    }

    public List<PeedInfoVo> findPeedList(String memberId) throws IOException {
        List<PeedVo> all = peedDao.findAll();
        List<MemberFollowVo> followVoList = memberDao.findFollowByMember(memberId);
        List<PeedInfoVo> result = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getMemberId().equals(memberId)) {
                PeedInfoVo info = new PeedInfoVo();
                info.setPostId(all.get(i).getPostId());
                info.setMemberId(all.get(i).getMemberId());
                info.setContent(all.get(i).getContent());
                info.setCreatedAt(all.get(i).getCreatedAt());
                info.setUpdatedAt(all.get(i).getUpdatedAt());
                result.add(info);
            } else {
                for (int j = 0; j < followVoList.size(); j++) {
                    if (all.get(i).getMemberId().equals(followVoList.get(j).getFollowId())) {
                        PeedInfoVo info = new PeedInfoVo();
                        info.setPostId(all.get(i).getPostId());
                        info.setMemberId(all.get(i).getMemberId());
                        info.setContent(all.get(i).getContent());
                        info.setCreatedAt(all.get(i).getCreatedAt());
                        info.setUpdatedAt(all.get(i).getUpdatedAt());
                        result.add(info);
                        break;
                    }
                }
            }
        }
        result.sort(Comparator.comparing(PeedInfoVo::getCreatedAt).reversed());
        return result;
    }

}
