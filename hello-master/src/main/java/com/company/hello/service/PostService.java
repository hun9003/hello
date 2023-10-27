package com.company.hello.service;

import com.company.hello.dao.MemberDao;
import com.company.hello.dao.PostDao;
import com.company.hello.vo.MemberVo;
import com.company.hello.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private MemberDao memberDao;

    public List<PostVo> getAllPostsForFeed(String memberId) throws IOException {
        MemberVo currentUser = memberDao.findMember(memberId);

        // 사용자와 사용자가 팔로우하는 사람들의 글을 가져옴
        List<String> following = currentUser.getFollowing();
        return postDao.findAll().stream()
                .filter(post -> following.contains(post.getAuthorId()) || post.getAuthorId().equals(currentUser.getMemberId()))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp())) // 가장 최근에 작성된 글이 상단
                .collect(Collectors.toList());

    }


    public void createPost(PostVo postVo) throws IOException {
        postDao.savePost(postVo);
    }

    public void addLike(int postId, String memberId) throws IOException {
        postDao.addLike(postId, memberId);
    }

    public void removeLike(int postId, String memberId) throws IOException {
        postDao.removeLike(postId, memberId);
    }

    public void addComment(int postId, PostVo.Comment comment) throws IOException {
        postDao.addComment(postId, comment);
    }

    public void removeComment(int postId, int commentId) throws IOException {
        postDao.removeComment(postId, commentId);
    }

    public void follow(String followerId, String followeeId) {
        memberDao.addFollower(followerId, followeeId);
    }

    public void unfollow(String followerId, String followeeId) {
        memberDao.removeFollower(followerId, followeeId);
    }
}
