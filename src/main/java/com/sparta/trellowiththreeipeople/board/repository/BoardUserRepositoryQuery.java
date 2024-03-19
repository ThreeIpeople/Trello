package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardUserRepositoryQuery {

    List<Board> findBoardUserByUserIdAndFetchBoards(Long userId);
}
