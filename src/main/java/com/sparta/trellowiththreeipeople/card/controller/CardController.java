package com.sparta.trellowiththreeipeople.card.controller;


import com.sparta.trellowiththreeipeople.card.dto.CardRequestDto;
import com.sparta.trellowiththreeipeople.card.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boards/{boardId}/columns/{columnsId}/")
public class CardController {
//    @PostMapping("cards")
//    public ResponseEntity<Card> createCard(@RequestBody CardRequestDto cardRequestDto){
//
//    }

}
