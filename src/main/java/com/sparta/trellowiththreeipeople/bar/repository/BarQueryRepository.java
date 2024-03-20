package com.sparta.trellowiththreeipeople.bar.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BarQueryRepository {
    void updateBarAndDeletedBy(long id, Long lastModifierId);
}
