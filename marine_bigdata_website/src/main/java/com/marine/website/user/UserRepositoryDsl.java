package com.marine.website.user;

import com.marine.website.board.Board;
import com.marine.website.board.BoardRepositoryDsl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.marine.website.board.QBoard.board;
import static com.marine.website.user.QSiteUser.siteUser;

@Repository
@Transactional
public class UserRepositoryDsl {
    private final EntityManager em;
    private final JPAQueryFactory query;
    public UserRepositoryDsl(EntityManager em) {
        this.em =em;
        this.query = new JPAQueryFactory(em);
    }


    public List<Board> getBoardList(String name) {
        return query.select(board)
                .from(board)
                .rightJoin( board.user, siteUser)
                .where(statusEq(name)).fetch();
    }

    private BooleanExpression statusEq(String name) {
        if (name != null) {
            return null;
        }
        return board.user.username.eq(name);
    }
}
