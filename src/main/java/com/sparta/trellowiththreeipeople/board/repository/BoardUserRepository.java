package com.sparta.trellowiththreeipeople.board.repository;

import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long>, BoardUserRepositoryQuery {

    @Query("SELECT bu FROM BoardUser bu WHERE bu.user.id = :userId AND bu.board.boardId = :boardId")
    Optional<BoardUser> findBoardUserByUserIdAndBoardId(@Param("userId") Long userId, @Param("boardId") Long boardId);
}
