package com.sparta.trellowiththreeipeople.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -820663549L;

    public static final QBoard board = new QBoard("board");

    public final com.sparta.trellowiththreeipeople.common.QBaseEntity _super = new com.sparta.trellowiththreeipeople.common.QBaseEntity(this);

    public final ListPath<com.sparta.trellowiththreeipeople.bar.entity.Bar, com.sparta.trellowiththreeipeople.bar.entity.QBar> bars = this.<com.sparta.trellowiththreeipeople.bar.entity.Bar, com.sparta.trellowiththreeipeople.bar.entity.QBar>createList("bars", com.sparta.trellowiththreeipeople.bar.entity.Bar.class, com.sparta.trellowiththreeipeople.bar.entity.QBar.class, PathInits.DIRECT2);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardInfo = createString("boardInfo");

    public final StringPath boardName = createString("boardName");

    public final EnumPath<BoardColorEnum> colorEnum = createEnum("colorEnum", BoardColorEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> createdUser = createNumber("createdUser", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<BoardUser, QBoardUser> users = this.<BoardUser, QBoardUser>createList("users", BoardUser.class, QBoardUser.class, PathInits.DIRECT2);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

