package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public class PostListDto {
    private Long no;
    private String title;
    private LocalDateTime createdAt;
    private Integer views;

    public PostListDto() {}

    private PostListDto(Post post) {
        this.no = post.getNo();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.views = post.getViews();
    }

    public static PostListDto from(Post post) {
        if (post == null) return null;
        return new PostListDto(post);
    }

    // Getters
    public Long getNo() { return no; }
    public String getTitle() { return title; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Integer getViews() { return views; }
}
