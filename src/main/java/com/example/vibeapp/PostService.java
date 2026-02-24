package com.example.vibeapp;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPostList() {
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public Post getPostDetail(Long no) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return post;
    }

    public void createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
