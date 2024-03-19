package com.sparta.trellowiththreeipeople.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private String boardName;

    private String boardInfo;

}
