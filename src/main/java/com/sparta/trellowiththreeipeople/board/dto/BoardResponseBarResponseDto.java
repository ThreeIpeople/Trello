package com.sparta.trellowiththreeipeople.board.dto;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.card.dto.CardNameResponseDto;
import com.sparta.trellowiththreeipeople.card.entity.Card;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseBarResponseDto {

    private final Long barId;

    private final String title;

    //private final List<CardNameResponseDto> cards;

    public BoardResponseBarResponseDto(
            Bar bar

    ){
        this.barId = bar.getId();
        this.title = bar.getTitle();
        //this.cards = ;

    }
}
