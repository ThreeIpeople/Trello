package com.sparta.trellowiththreeipeople.board.dto;

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


}
