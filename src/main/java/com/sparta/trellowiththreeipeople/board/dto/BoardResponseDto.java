package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardColorEnum;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {

    private Long boardId;
    private String boardName;
    private String boardInfo;
    private BoardColorEnum colorEnum;
    private List<BoardUser> users;
    private List<Bar> bars;


    public BoardResponseDto(Board board){
        this.boardId =board.getBoardId();
        this.boardName = board.getBoardName();
        this.boardInfo = board.getBoardInfo();
        this.colorEnum = board.getColorEnum();
        this.users = board.getUsers();
        this.bars = board.getBars();
    }

}
