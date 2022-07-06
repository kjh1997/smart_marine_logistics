package com.marine.website.board;


import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.marine.website.board.QBoard.board;
import static com.marine.website.user.QSiteUser.siteUser;
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
    public List<Board> getBoardList(int page){
        int offset = (page - 1)*10;
        return query
                .select(board)
                .from(board)
                .join(board.user, siteUser)
                .join(board.category, category)
                .offset(offset).limit(10).fetch();

    }

    private BooleanExpression statusEQ(String name) {
        if (name == null) {
            return null;
        }
        return board.user.username.eq(name);
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

    private BooleanExpression categoryStateEQ(String categoryName){
        if(categoryName == null){
            return null;
        }
        return board.category.name.eq(categoryName);
    }
    private BooleanExpression searchTitleStateEQ(String searchKeyword){
        if(searchKeyword == null){
            return null;
        }
        return board.title.contains(searchKeyword);
    }

    public List<Board> getBoardListTest(int page,String searchKeyword,String searchCategory){
        int offset = (page - 1)*10;
        return query
                .select(board)
                .from(board)
                .join(board.user, siteUser)
                .join(board.category, category)
                .where(categoryStateEQ(searchCategory))
                .where(searchTitleStateEQ(searchKeyword))
                .offset(offset).limit(10).fetch();

}
















}
