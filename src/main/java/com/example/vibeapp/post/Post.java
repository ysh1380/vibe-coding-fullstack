package com.example.vibeapp.post;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    
    private String title;
    
    @Lob
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer views;

    public Post() {}

    public Post(Long no, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Integer views) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.views = views;
    }

    // Getters
    public Long getNo() { return no; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Integer getViews() { return views; }

    // Setters
    public void setNo(Long no) { this.no = no; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setViews(Integer views) { this.views = views; }
}
