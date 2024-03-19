package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto save(BoardRequestDto requestDto, User user){
        if(boardRepository.findByBoardName(requestDto.getBoardName())){
            throw new IllegalArgumentException("존재하는 보드 이름입니다.");
        }
        Board board = new Board(requestDto,user);
        boardRepository.save(board);

        return new BoardResponseDto(board);


    }

}
