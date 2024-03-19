package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardListResponseDto {

    private String boardName;

    public BoardListResponseDto(Board board) {
        this.boardName = board.getBoardName();
    }
}
