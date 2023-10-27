package com.company.hello.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PeedInfoVo {
    private String postId;
    private String memberId;
    private String content;
    private String createdAt;
    private String updatedAt;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt.toString();
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFormatCreatedAt() {
        // 입력 문자열을 LocalDateTime 객체로 파싱
        LocalDateTime dateTime = LocalDateTime.parse(getCreatedAt());

        // 원하는 포맷으로 날짜-시간 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 날짜-시간 포맷 변경
        return dateTime.format(formatter);
    }
}
