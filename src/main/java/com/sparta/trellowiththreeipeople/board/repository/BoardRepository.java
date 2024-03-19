package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    boolean findByBoardName(String boardName);
}
