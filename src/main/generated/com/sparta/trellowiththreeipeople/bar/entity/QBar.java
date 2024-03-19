package com.sparta.trellowiththreeipeople.bar.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBar is a Querydsl query type for Bar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBar extends EntityPathBase<Bar> {

    private static final long serialVersionUID = -1936981981L;

    public static final QBar bar = new QBar("bar");

    public final NumberPath<Board> board = createNumber("board", Board.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QBar(String variable) {
        super(Bar.class, forVariable(variable));
    }

    public QBar(Path<? extends Bar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBar(PathMetadata metadata) {
        super(Bar.class, metadata);
    }

}

