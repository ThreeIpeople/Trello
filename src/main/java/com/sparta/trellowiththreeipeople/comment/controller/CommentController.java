package com.sparta.trellowiththreeipeople.comment.controller;

import com.sparta.trellowiththreeipeople.comment.dto.request.CreateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.request.UpdateCommentRequestDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.CommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.dto.response.UpdateCommentResponseDto;
import com.sparta.trellowiththreeipeople.comment.service.CommentService;
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
@RequestMapping("/api/boards/{boardId}/bars/{barId}/cards/{cardId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @PathVariable Long cardId,
        @RequestBody CreateCommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        CommentResponseDto result = commentService.createComment(boardId, barId, cardId,
            requestDto, userDetails.getUser());
        return ResponseEntity.created(
                URI.create(
                    "/api/boards/" + boardId + "/bars/" + barId + "/cards/" + cardId + "/comments/"
                        + result.getId()))
            .body("댓글 생성 완료");
    }

    @GetMapping()
    public ResponseEntity getComments(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @PathVariable Long cardId
    ) {
        List<CommentResponseDto> result = commentService.getComments(boardId, barId, cardId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity getComment(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @PathVariable Long cardId,
        @PathVariable Long commentId
    ) {
        CommentResponseDto result = commentService.getComment(boardId, barId, cardId, commentId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @PathVariable Long cardId,
        @PathVariable Long commentId,
        @RequestBody UpdateCommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UpdateCommentResponseDto result = commentService.updateComment(boardId, barId, cardId,
            commentId,
            requestDto, userDetails.getUser());
        return ResponseEntity.created(URI.create(
            "/api/boards/" + boardId + "/bars/" + barId + "/cards/" + cardId + "/comments/"
                + result.getId())).body("댓글 수정 완료");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(
        @PathVariable Long boardId,
        @PathVariable Long barId,
        @PathVariable Long cardId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(boardId, barId, cardId, commentId, userDetails.getUser());
        return ResponseEntity.ok("삭제가 완료 됐습니다.");
    }
}
