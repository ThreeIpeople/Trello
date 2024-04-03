package com.sparta.trellowiththreeipeople.card.dto;


import com.sparta.trellowiththreeipeople.card.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardResponse {

    Long cardId;
    String title;
    String content;



    public CardResponse(long id, String title, String content) {
        this.cardId = id;
        this.title = title;
        this.content = content;
    }

    public static CardResponse of(Card card) {
        return new CardResponse(card.getId(), card.getTitle(), card.getContent());
    }
}
