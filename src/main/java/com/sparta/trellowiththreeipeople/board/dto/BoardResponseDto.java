package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardColorEnum;
import java.util.List;
import lombok.Getter;



@Getter
public class BoardResponseDto  {

    private Long boardId;
    private String boardName;
    private String boardInfo;
    private BoardColorEnum colorEnum;
    private List<BoardResponseUsersResponseDto> users;
    private List<BoardResponseBarResponseDto> bars;

     public BoardResponseDto(Long boardId,
         String boardName,
         String boardInfo,
         BoardColorEnum colorEnum,
         List<BoardResponseUsersResponseDto> users,
         List<BoardResponseBarResponseDto> bars
     ) {

         this.boardId = boardId;
         this.boardName = boardName;
         this.boardInfo = boardInfo;
         this.colorEnum = colorEnum;
         this.users = users;
         this.bars = bars;
     }

    public BoardResponseDto(
            Board board
    ) {
        this.boardId = board.getBoardId();
        this.boardName = board.getBoardName();
        this.boardInfo = board.getBoardInfo();
        this.colorEnum = board.getColorEnum();

    }

    public void addUsers(List<BoardResponseUsersResponseDto> users) {
        this.users = users;
    }

    public void addBars(List<BoardResponseBarResponseDto> bars) {
        this.bars = bars;
    }

}
