package com.sparta.trellowiththreeipeople.comment.dto.response;

import com.sparta.trellowiththreeipeople.comment.entity.Comment;
import lombok.Getter;

@Getter
public class UpdateCommentResponseDto {

    private String content;

    public UpdateCommentResponseDto(Comment comment) {
        this.content = comment.getContent();
    }
}
