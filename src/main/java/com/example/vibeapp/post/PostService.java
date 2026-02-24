package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    private List<Post> findAllEntities() {
        return postMapper.findAll();
    }

    public List<PostListDto> findAll() {
        return findAllEntities().stream()
                .map(PostListDto::from)
                .toList();
    }

    public List<PostListDto> findPaged(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.findPaged(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalPosts = postMapper.countAll();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public PostResponseDto findById(Long no) {
        postMapper.incrementViews(no);
        Post post = postMapper.findByNo(no);
        return PostResponseDto.from(post);
    }

    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postMapper.save(post);
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postMapper.findByNo(no);
        if (post != null) {
            post.setTitle(dto.title());
            post.setContent(dto.content());
            post.setUpdatedAt(LocalDateTime.now());
            postMapper.update(post);
        }
    }

    public void deletePost(Long no) {
        postMapper.deleteByNo(no);
    }
}
