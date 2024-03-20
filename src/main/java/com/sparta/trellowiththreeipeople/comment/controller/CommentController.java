package com.sparta.trellowiththreeipeople.comment.controller;

import com.sparta.trellowiththreeipeople.comment.dto.request.CreateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.CommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.service.CommentService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns/{columnId}/cards/{cardId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(
        @PathVariable Long boardId,
        @PathVariable Long columnId,
        @PathVariable Long cardId,
        @RequestBody CreateCommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CommentResponseDto result = commentService.createComment(boardId,columnId,cardId,requestDto,userDetails.getUser());
        return ResponseEntity.ok(result);
    }
}
