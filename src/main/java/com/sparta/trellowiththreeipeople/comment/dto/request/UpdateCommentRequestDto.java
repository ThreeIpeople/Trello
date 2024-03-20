package com.sparta.trellowiththreeipeople.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {
    @NotBlank(message = "댓글은 필수 항목입니다")
    private String Comment;
}
