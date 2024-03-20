package com.sparta.trellowiththreeipeople.card.controller;


import com.sparta.trellowiththreeipeople.card.dto.CardDTO;
import com.sparta.trellowiththreeipeople.card.dto.CardRequest;
import com.sparta.trellowiththreeipeople.card.dto.CardResponse;
import com.sparta.trellowiththreeipeople.card.service.CardService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/bars/{barId}")
public class CardController{
    private final CardService cardService;
    @PostMapping("/cards")
    public ResponseEntity<CardResponse> createCard(
        @PathVariable Long barId,
        @RequestBody CardRequest cardRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CardResponse cardResponse = cardService.createCard(barId, cardRequest, userDetails.getUser().getId());
        return ResponseEntity.ok().body(cardResponse);
    }
    //전체 카드 조회
    @GetMapping("/cards")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        List<CardDTO> cardList = cardService.getAllCards();
        return ResponseEntity.ok().body(cardList);
    }
    //카드 선택조회
    @GetMapping("/cards/{cardId}")
    public ResponseEntity<CardResponse> getCard(@PathVariable Long cardId) {
        CardResponse cardResponse = cardService.getCard(cardId);
        return ResponseEntity.ok().body(cardResponse);
    }
    @PutMapping("/cards/{cardId}")
    public ResponseEntity<CardResponse> updateCard(@PathVariable Long cardId, @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = cardService.updateCard(cardId, cardRequest);
        return ResponseEntity.ok().body(cardResponse);
    }
    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Long cardId){
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
