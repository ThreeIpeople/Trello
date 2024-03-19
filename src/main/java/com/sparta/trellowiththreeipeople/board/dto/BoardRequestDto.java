package com.sparta.trellowiththreeipeople.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private String boardName;
    private String boardInfo;
}
