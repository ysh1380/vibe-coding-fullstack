package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostTagRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 특정 게시글의 태그 목록을 조회합니다.
     */
    public List<PostTag> findByPostNo(Long postNo) {
        return em.createQuery("SELECT t FROM PostTag t WHERE t.postNo = :postNo", PostTag.class)
                .setParameter("postNo", postNo)
                .getResultList();
    }

    /**
     * 새로운 태그를 추가합니다.
     */
    public void save(PostTag postTag) {
        em.persist(postTag);
    }

    /**
     * 태그 ID로 특정 태그를 삭제합니다.
     */
    public void deleteById(Long id) {
        PostTag tag = em.find(PostTag.class, id);
        if (tag != null) {
            em.remove(tag);
        }
    }

    /**
     * 특정 게시글의 모든 태그를 삭제합니다.
     */
    public void deleteByPostNo(Long postNo) {
        em.createQuery("DELETE FROM PostTag t WHERE t.postNo = :postNo")
                .setParameter("postNo", postNo)
                .executeUpdate();
    }
}
