package com.marine.website.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSiteUser is a Querydsl query type for SiteUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSiteUser extends EntityPathBase<SiteUser> {

    private static final long serialVersionUID = -520923997L;

    public static final QSiteUser siteUser = new QSiteUser("siteUser");

    public final StringPath auth = createString("auth");

    public final ListPath<com.marine.website.board.Board, com.marine.website.board.QBoard> boardList = this.<com.marine.website.board.Board, com.marine.website.board.QBoard>createList("boardList", com.marine.website.board.Board.class, com.marine.website.board.QBoard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QSiteUser(String variable) {
        super(SiteUser.class, forVariable(variable));
    }

    public QSiteUser(Path<? extends SiteUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSiteUser(PathMetadata metadata) {
        super(SiteUser.class, metadata);
    }

}

