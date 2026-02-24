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
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public List<PostListDto> findAll() {
        return findAllEntities().stream()
                .map(PostListDto::from)
                .toList();
    }

    public List<PostListDto> findPaged(int page, int size) {
        List<Post> allPosts = findAllEntities();
        int fromIndex = (page - 1) * size;
        if (allPosts.size() <= fromIndex) {
            return Collections.emptyList();
        }
        int toIndex = Math.min(fromIndex + size, allPosts.size());
        return allPosts.subList(fromIndex, toIndex).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public PostResponseDto findById(Long no) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return PostResponseDto.from(post);
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
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
