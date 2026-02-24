package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 모든 게시글을 최신순으로 조회합니다.
     */
    public List<Post> findAll() {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.no DESC", Post.class)
                .getResultList();
    }

    /**
     * 페이징된 게시글 목록을 조회합니다.
     */
    public List<Post> findPaged(int offset, int size) {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.no DESC", Post.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    /**
     * 전체 게시글 수를 반환합니다.
     */
    public int countAll() {
        Long count = em.createQuery("SELECT COUNT(p) FROM Post p", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    /**
     * 번호로 특정 게시글을 조회합니다.
     */
    public Post findByNo(Long no) {
        return em.find(Post.class, no);
    }

    /**
     * 새로운 게시글을 저장합니다.
     */
    public void save(Post post) {
        em.persist(post);
    }

    /**
     * 기존 게시글 정보를 수정합니다.
     */
    public void update(Post post) {
        em.merge(post);
    }

    /**
     * 번호로 게시글을 삭제합니다.
     */
    public void deleteByNo(Long no) {
        Post post = findByNo(no);
        if (post != null) {
            em.remove(post);
        }
    }

    /**
     * 조회수를 1 증가시킵니다.
     */
    public void incrementViews(Long no) {
        Post post = findByNo(no);
        if (post != null) {
            // 변경 감지(Dirty Checking)를 통해 자동 업데이트됨
            post.setViews(post.getViews() + 1);
        }
    }
}
