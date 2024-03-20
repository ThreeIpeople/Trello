package com.sparta.trellowiththreeipeople.bar.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.sparta.trellowiththreeipeople.bar.entity.QBar.bar;

@Repository
@RequiredArgsConstructor
public class BarQueryRepositoryImpl implements BarQueryRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public void updateBarAndDeletedBy(long id, Long lastModifierId) {
        queryFactory
                .update(bar)
                .set(bar.deletedAt, LocalDateTime.now())
                .set(bar.lastModifierId, lastModifierId)
                .where(bar.id.eq(id))
                .execute();
    }
}
