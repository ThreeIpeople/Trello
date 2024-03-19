package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardUserRepositoryQuery {

    List<Board> findBoardUserByUserIdAndFetchBoards(Long userId);
}
