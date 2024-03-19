package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardColorEnum;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {

    private Long boardId;
    private String boardName;
    private String boardInfo;
    private BoardColorEnum colorEnum;
    private List<BoardUser> users;


    public BoardResponseDto(Board board){
        this.boardId =board.getBoardId();
        this.boardName = board.getBoardName();
        this.boardInfo = board.getBoardInfo();
        this.colorEnum = board.getColorEnum();
        this.users = board.getUsers();
    }

}
