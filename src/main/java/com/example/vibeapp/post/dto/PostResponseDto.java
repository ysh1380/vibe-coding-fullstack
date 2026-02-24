package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public class PostResponseDto {
    private Long no;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer views;

    public PostResponseDto() {}

    private PostResponseDto(Post post) {
        this.no = post.getNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.views = post.getViews();
    }

    public static PostResponseDto from(Post post) {
        if (post == null) return null;
        return new PostResponseDto(post);
    }

    // Getters
    public Long getNo() { return no; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Integer getViews() { return views; }
}
