package com.marine.website.category;

import com.marine.website.board.Board;
import com.marine.website.category.categorydto.CategoryDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.marine.website.board.QBoard.board;
import static com.marine.website.category.QCategory.category;

@Repository
@Transactional
public class CategoryRepositoryDsl {
    private final EntityManager em;
    private final JPAQueryFactory query;
    public CategoryRepositoryDsl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }


    public List<Board> getBoardListByCategoryName(String name) {
        return query.select(board)
                .from(board)
                .rightJoin(board.category, category )
                .where(statusEq(name)).fetch();
    }

    public String saveCategory(Category category) {

        System.out.println("??");
        em.persist(category);
        return "success";
    }
    private BooleanExpression statusEq(String name) {
        if (name != null) {
            return null;
        }
        return board.category.name.eq(name);
    }


}
