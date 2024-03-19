package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long>, BoardUserRepositoryQuery {
    List<BoardUser> findBoardUserByUserId(Long id);


}
