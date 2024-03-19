package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import org.springframework.stereotype.Service;


public interface BoardService {

    BoardResponseDto save(BoardRequestDto boardRequestDto, User user);
}
