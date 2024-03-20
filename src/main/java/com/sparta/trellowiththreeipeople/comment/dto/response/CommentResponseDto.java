package com.sparta.trellowiththreeipeople.comment.dto.response;

import com.sparta.trellowiththreeipeople.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String content;

    public CommentResponseDto(Comment comment) {
        content = comment.getContent();
    }
}
