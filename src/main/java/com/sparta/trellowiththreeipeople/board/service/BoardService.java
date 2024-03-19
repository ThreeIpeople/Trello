package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.user.entity.User;



public interface BoardService {

    BoardResponseDto save(BoardRequestDto boardRequestDto, User user);
}
