package com.sparta.trellowiththreeipeople.card.service;


import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.card.dto.CardRequest;
import com.sparta.trellowiththreeipeople.card.dto.CardResponse;
import com.sparta.trellowiththreeipeople.card.entity.Card;
import com.sparta.trellowiththreeipeople.card.repository.CardRepository;
import com.sparta.trellowiththreeipeople.user.entity.User;
import com.sparta.trellowiththreeipeople.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService{
    private final CardRepository cardRepository;
    private final BarRepository barRepository;
    private final UserRepository userRepository;
    public CardResponse createCard(Long columId,CardRequest cardRequest,Long userId) {
        User checkUser = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저를 찾을수 없습니다"));
        Bar checkBar = barRepository.findById(columId).orElseThrow(()->new IllegalArgumentException("컬럼을 찾을수 없습니다"));
        Card card = new Card(checkUser,checkBar,cardRequest);
        cardRepository.save(card);
        CardResponse cardResponse = new CardResponse(card);
        return cardResponse;
    }

    public List<CardResponse> getAllCards() {
        List<CardResponse> cardList = cardRepository.findAll()
            .stream().map(CardResponse::new).toList();
        return cardList;
    }

    public CardResponse getCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("유저를 찾을수 없습니다."));
        return new CardResponse(card);
    }
    @Transactional
    public CardResponse updateCard(Long cardId,CardRequest cardRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("유저를 찾을수 없습니다."));
        card.updateCard(cardRequest);
        return new CardResponse(card);

    }

    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("유저를 찾을수 없습니다."));
        cardRepository.delete(card);
    }
}
