package com.sparta.trellowiththreeipeople.card.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime deadline;
    // 필요한 다른 필드들을 추가


    public CardDTO(Long id, String title, String content,LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deadline = deadline;
    }
}
