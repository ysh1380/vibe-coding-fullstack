package com.example.vibeapp;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // 초기 데이터 10개 추가
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                i,
                "바이브 코딩과 함께하는 프리미엄 교육 " + i,
                "이것은 서버 사이드 렌더링된 게시글 내용입니다. " + i,
                LocalDateTime.now().minusDays(11 - i),
                LocalDateTime.now().minusDays(11 - i),
                (int) (Math.random() * 1000)
            ));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public Post findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }
}
