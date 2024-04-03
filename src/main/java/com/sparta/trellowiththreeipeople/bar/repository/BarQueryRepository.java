package com.sparta.trellowiththreeipeople.bar.repository;

import com.sparta.trellowiththreeipeople.bar.dto.BarResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BarQueryRepository {
    void updateBarAndDeletedBy(long id, Long lastModifierId);

    List<BarResponseDto> findAllByBoard(Long boardId);
}
