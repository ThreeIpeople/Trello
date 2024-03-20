package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long>, BoardUserRepositoryQuery {




}
