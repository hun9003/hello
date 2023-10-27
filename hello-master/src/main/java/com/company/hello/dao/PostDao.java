package com.company.hello.dao;

import com.company.hello.vo.PostVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class PostDao {

    private static final String FILE_PATH = "classpath:data/posts.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public PostDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<PostVo> findAll() throws IOException {
        File file = ResourceUtils.getFile(FILE_PATH);
        return objectMapper.readValue(file, new TypeReference<List<PostVo>>() {
        });
    }

    public PostVo findPostById(int postId) throws IOException {
        List<PostVo> posts = findAll();
        for (PostVo post : posts) {
            if (post.getPostId() == postId) return post;
        }
        return null;
    }

    public void savePost(PostVo post) throws IOException {
        List<PostVo> posts = findAll();
        posts.add(post);
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), posts);
    }

    public void updatePost(PostVo post) throws IOException {
        List<PostVo> posts = findAll();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getPostId() == post.getPostId()) {
                posts.set(i, post);
                break;
            }
        }
        objectMapper.writeValue(ResourceUtils.getFile(FILE_PATH), posts);
    }

    public void addLike(int postId, String memberId) throws IOException {
        PostVo post = findPostById(postId);
        if (!post.getLikes().contains(memberId)) {
            post.getLikes().add(memberId);
            updatePost(post);
        }
    }

    public void removeLike(int postId, String memberId) throws IOException {
        PostVo post = findPostById(postId);
        post.getLikes().remove(memberId);
        updatePost(post);
    }

    public void addComment(int postId, PostVo.Comment comment) throws IOException {
        PostVo post = findPostById(postId);
        post.getComments().add(comment);
        updatePost(post);
    }

    public void removeComment(int postId, int commentId) throws IOException {
        PostVo post = findPostById(postId);
        post.getComments().removeIf(comment -> comment.getCommentId() == commentId);
        updatePost(post);
    }
}
