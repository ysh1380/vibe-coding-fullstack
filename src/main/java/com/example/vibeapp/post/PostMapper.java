package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findAll();
    
    List<Post> findPaged(@Param("offset") int offset, @Param("size") int size);
    
    int countAll();
    
    Post findByNo(Long no);
    
    void save(Post post);
    
    void update(Post post);
    
    void deleteByNo(Long no);
    
    void incrementViews(Long no);
}
