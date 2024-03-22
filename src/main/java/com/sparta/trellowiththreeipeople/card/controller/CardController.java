package com.sparta.trellowiththreeipeople.card.controller;


import com.sparta.trellowiththreeipeople.card.dto.CardDTO;
import com.sparta.trellowiththreeipeople.card.dto.CardRequest;
import com.sparta.trellowiththreeipeople.card.dto.CardResponse;
import com.sparta.trellowiththreeipeople.card.service.CardService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import java.net.URI;
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
    public ResponseEntity<String> createCard(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @RequestBody CardRequest cardRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long cardId = cardService.createCard(barId, cardRequest, userDetails.getUserId());
        return ResponseEntity.created(URI.create("/api/boards/"+ boardId+"/bars/"+barId+"/cards/"+cardId))
            .body("카드가 생성 되었습니다.");
    }
    //전체 카드 조회
    @GetMapping("/cards")
    public ResponseEntity<List<CardDTO>> getAllCards(
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<CardDTO> cardList = cardService.getAllCards(boardId,userDetails.getUserId());
        return ResponseEntity.ok().body(cardList);
    }
    //카드 선택조회
    @GetMapping("/cards/{cardId}")
    public ResponseEntity<CardResponse> getCard(
            @PathVariable Long boardId,
            @PathVariable Long barId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CardResponse cardResponse = cardService.getCard(cardId,barId,boardId,userDetails.getUserId());
        return ResponseEntity.ok().body(cardResponse);
    }
    @PutMapping("/cards/{cardId}")
    public ResponseEntity<CardResponse> updateCard(
            @PathVariable Long cardId,
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = cardService.updateCard(cardId, cardRequest,userDetails.getUserId(),boardId);
        return ResponseEntity.ok().body(cardResponse);
    }
    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<String> deleteCard(
        @PathVariable Long cardId,
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        cardService.deleteCard(cardId,userDetails.getUserId(),boardId);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
