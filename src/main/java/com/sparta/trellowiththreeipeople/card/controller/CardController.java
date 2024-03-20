package com.sparta.trellowiththreeipeople.card.controller;


import com.sparta.trellowiththreeipeople.card.dto.CardRequest;
import com.sparta.trellowiththreeipeople.card.dto.CardResponse;
import com.sparta.trellowiththreeipeople.card.service.CardService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boards/{boardId}/columns/{columnsId}/")
public class CardController {

    private final CardService cardService;
    //카드 등록
    @PostMapping("cards")
    public ResponseEntity<CardResponse> createCard(
        @PathVariable Long columnsId,
        @RequestBody CardRequest cardRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CardResponse cardResponse = cardService.createCard(columnsId, cardRequest,
            userDetails.getUser().getId());
        return ResponseEntity.ok().body(cardResponse);
    }
    //전체 카드 조회
    @GetMapping("cards")
    public ResponseEntity<List<CardResponse>> getAllCards() {
        List<CardResponse> cardList = cardService.getAllCards();
        return ResponseEntity.ok().body(cardList);
    }
    //카드 선택조회
    @GetMapping("cards/{cardId}")
    public ResponseEntity<CardResponse> getCard(@PathVariable Long cardId) {
        CardResponse cardResponse = cardService.getCard(cardId);
        return ResponseEntity.ok().body(cardResponse);
    }
    @PutMapping("cards/{cardId}")
    public ResponseEntity<CardResponse> updateCard(@PathVariable Long cardId, @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = cardService.updateCard(cardId, cardRequest);
        return ResponseEntity.ok().body(cardResponse);
    }
    @DeleteMapping("cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId){
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
