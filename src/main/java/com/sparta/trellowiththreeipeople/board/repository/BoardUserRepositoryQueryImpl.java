package com.sparta.trellowiththreeipeople.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import com.sparta.trellowiththreeipeople.board.entity.QBoard;
import com.sparta.trellowiththreeipeople.board.entity.QBoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardUserRepositoryQueryImpl implements BoardUserRepositoryQuery {


    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> findBoardUserByUserIdAndFetchBoards(Long userId) {
        QBoardUser boardUser = QBoardUser.boardUser;
        QBoard board = QBoard.board;

        List<BoardUser> boardUsers = queryFactory.selectFrom(boardUser)
    .leftJoin(boardUser.board, board)
                .where(boardUser.user.id.eq(userId))
                .fetchJoin()
                .fetch();

        return boardUsers.stream()
                .map(BoardUser::getBoard)
                .collect(Collectors.toList());
    }

    @Override

    public List<BoardUser> getBoardUserByBoardId(Long boardId) {
        QBoardUser boardUser = QBoardUser.boardUser;

        return queryFactory.selectFrom(boardUser)
                .where(boardUser.board.boardId.eq(boardId))
                .fetch();
    }
    @Override
    public BoardUser findBoardUserByBoardIdAndUserId(Long boardId, Long userId){
        QBoardUser boardUser = QBoardUser.boardUser;

        return queryFactory.selectFrom(boardUser)
                .where(boardUser.board.boardId.eq(boardId)
                        .and(boardUser.user.id.eq(userId)))
                .fetchOne();
    }

    @Override
    public void deleteAllByBoardUsers(List<BoardUser> boardUsers) {
        QBoardUser boardUser = QBoardUser.boardUser;

        queryFactory.delete(boardUser)
                .where(boardUser.in(boardUsers))
                .execute();
    }
}
