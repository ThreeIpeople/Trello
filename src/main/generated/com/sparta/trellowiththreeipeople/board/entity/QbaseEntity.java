package com.sparta.trellowiththreeipeople.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QbaseEntity is a Querydsl query type for baseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QbaseEntity extends EntityPathBase<baseEntity> {

    private static final long serialVersionUID = 80619511L;

    public static final QbaseEntity baseEntity = new QbaseEntity("baseEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteAt = createDateTime("deleteAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QbaseEntity(String variable) {
        super(baseEntity.class, forVariable(variable));
    }

    public QbaseEntity(Path<? extends baseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QbaseEntity(PathMetadata metadata) {
        super(baseEntity.class, metadata);
    }

}

