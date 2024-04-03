package com.sparta.trellowiththreeipeople.board.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class BoardResponseCardListDto {

    private final List<BoardResponseCardResponseDto> cards;

    public BoardResponseCardListDto(List<BoardResponseCardResponseDto> cards) {
        this.cards = cards;
    }
}
