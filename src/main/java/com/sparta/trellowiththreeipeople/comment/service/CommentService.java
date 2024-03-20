package com.sparta.trellowiththreeipeople.comment.service;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.card.entity.Card;
import com.sparta.trellowiththreeipeople.card.repository.CardRepository;
import com.sparta.trellowiththreeipeople.comment.dto.request.CreateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.CommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.entity.Comment;
import com.sparta.trellowiththreeipeople.comment.repository.CommentRepository;
import com.sparta.trellowiththreeipeople.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final BarRepository barRepository;
    private final CardRepository cardRepository;

    public CommentResponseDto createComment(Long boardId, Long columnId, Long cardId,
        CreateCommentRequestDto requestDto, User user) {
        findBoard(boardId);
        findBar(columnId);
        Card card = findCard(cardId);

        Comment comment = new Comment(card, requestDto, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    private Board findBoard(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 보드입니다"));
    }

    private Bar findBar(Long columnId) {
        return barRepository.findById(columnId)
            .orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 컬럼입니다"));
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 카드입니다"));
    }
}
