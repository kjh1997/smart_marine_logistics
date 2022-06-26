package com.marine.website.board;


import com.marine.website.user.SiteUser;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.marine.website.board.QBoard.board;
import static com.marine.website.category.QCategory.category;

@Repository
@Transactional
public class BoardRepositoryDsl {
    private final EntityManager em;
    private JPAQueryFactory query;
    public BoardRepositoryDsl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public List<Board> getBoardListByContainTitle(String title) {
        return em.createQuery("select b from board b where title list %:title%")
                .setParameter("title", title)
                .getResultList();
    }


//    public List<SiteUser> findAll() {
//        return em.find
//    }
}
