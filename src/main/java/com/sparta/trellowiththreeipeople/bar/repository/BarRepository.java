package com.sparta.trellowiththreeipeople.bar.repository;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long>, BarQueryRepository {
    List<Bar> findAllByBoard(Board board);
}
