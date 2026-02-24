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
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private List<Post> findAllEntities() {
        return postRepository.findAll();
    }

    public List<PostListDto> findAll() {
        return findAllEntities().stream()
                .map(PostListDto::from)
                .toList();
    }

    public List<PostListDto> findPaged(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findPaged(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.countAll();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public PostResponseDto getPostDetail(Long no) {
        postRepository.incrementViews(no);
        Post post = postRepository.findByNo(no);
        return post != null ? PostResponseDto.from(post) : null;
    }

    public PostResponseDto findById(Long no) {
        Post post = postRepository.findByNo(no);
        return post != null ? PostResponseDto.from(post) : null;
    }

    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(dto.title());
            post.setContent(dto.content());
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.update(post);
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
