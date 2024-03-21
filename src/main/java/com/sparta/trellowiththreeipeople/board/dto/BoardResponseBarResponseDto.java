package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import lombok.Getter;

@Getter
public class BoardResponseBarResponseDto {

    private final Long barId;

    private final String title;

    //private final List<CardNameResponseDto> cards;

    public BoardResponseBarResponseDto(
            Bar bar

    ) {
        this.barId = bar.getId();
        this.title = bar.getTitle();
        //this.cards = ;

    }
}
