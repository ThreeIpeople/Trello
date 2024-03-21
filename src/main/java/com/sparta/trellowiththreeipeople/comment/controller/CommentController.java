package com.sparta.trellowiththreeipeople.comment.controller;

import com.sparta.trellowiththreeipeople.comment.dto.request.CreateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.request.UpdateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.CommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.UpdateCommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.service.CommentService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns/{columnId}/cards/{cardId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(
        @PathVariable Long boardId,
        @PathVariable Long columnId,
        @PathVariable Long cardId,
        @RequestBody CreateCommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        CommentResponseDto result = commentService.createComment(boardId, columnId, cardId,
            requestDto, userDetails.getUser());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(
        @PathVariable Long boardId,
        @PathVariable Long columnId,
        @PathVariable Long cardId,
        @RequestBody UpdateCommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UpdateCommentResponseDto result = commentService.updateComment(boardId, columnId, cardId,
            requestDto, userDetails.getUser());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(
        @PathVariable Long boardId,
        @PathVariable Long columnId,
        @PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(boardId, columnId, cardId, userDetails.getUser());
        return ResponseEntity.ok("삭제가 완료 됐습니다.");
    }
}
