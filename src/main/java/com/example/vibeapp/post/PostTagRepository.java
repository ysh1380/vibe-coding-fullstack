package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostTagRepository {
    /**
     * 새로운 태그를 추가합니다.
     */
    void save(PostTag postTag);

    /**
     * 태그 ID로 특정 태그를 삭제합니다.
     */
    void deleteById(Long id);

    /**
     * 특정 게시글의 모든 태그를 삭제합니다.
     */
    void deleteByPostNo(Long postNo);
}
