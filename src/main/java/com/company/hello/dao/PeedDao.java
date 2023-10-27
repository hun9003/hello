package com.company.hello.dao;

import com.company.hello.vo.PeedVo;
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
public class PeedDao {

    private static final String FILE_PATH = "classpath:data/peed.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public PeedDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<PeedVo> findAll() throws IOException {

        File file = ResourceUtils.getFile(FILE_PATH);
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, new TypeReference<>() {});
    }
    public void insertPeed(PeedVo peedVo) throws IOException {
        List<PeedVo> list = findAll();
        list.add(peedVo);
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), list);
    }

    public List<PeedVo> findPeedList(String memberId) throws IOException {
        List<PeedVo> list = findAll();
        List<PeedVo> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberId().equals(memberId)) result.add(list.get(i));
        }

        return result;
    }
}
