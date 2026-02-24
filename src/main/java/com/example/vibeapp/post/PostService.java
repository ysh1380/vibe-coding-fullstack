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
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
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
        if (post == null) return null;
        
        List<String> tags = postTagRepository.findByPostNo(no).stream()
                .map(PostTag::getTagName)
                .toList();
                
        return PostResponseDto.from(post, tags);
    }

    public PostResponseDto findById(Long no) {
        Post post = postRepository.findByNo(no);
        if (post == null) return null;
        
        List<String> tags = postTagRepository.findByPostNo(no).stream()
                .map(PostTag::getTagName)
                .toList();
                
        return PostResponseDto.from(post, tags);
    }

    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
        
        saveTags(post.getNo(), dto.tags());
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(dto.title());
            post.setContent(dto.content());
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.update(post);
            
            // 기존 태그 삭제 후 재저장
            postTagRepository.deleteByPostNo(no);
            saveTags(no, dto.tags());
        }
    }

    public void deletePost(Long no) {
        // 외래키 설정(ON DELETE CASCADE)으로 자동 삭제되지만, 명시적으로 처리 가능
        // postTagRepository.deleteByPostNo(no); 
        postRepository.deleteByNo(no);
    }

    private void saveTags(Long postNo, String tagsStr) {
        if (tagsStr == null || tagsStr.isBlank()) return;
        
        String[] tags = tagsStr.split(",");
        for (String tag : tags) {
            String trimmedTag = tag.trim();
            if (!trimmedTag.isEmpty()) {
                postTagRepository.save(new PostTag(null, postNo, trimmedTag));
            }
        }
    }
}
