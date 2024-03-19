package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardListResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardUpdateRequestDto;
import com.sparta.trellowiththreeipeople.user.entity.User;

import java.util.List;


public interface BoardService {

    BoardResponseDto save(String boardName,String boardInfo, User user);

    BoardResponseDto getBoardByBoardId(Long boardId, User user);

    List<BoardListResponseDto> getBoardListByUserId(User user);

    BoardResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, User user);

    void deleteBoard(Long boardId, User user);

    void inviteUserToBoard(Long boardId, Long userId, User user);
}
