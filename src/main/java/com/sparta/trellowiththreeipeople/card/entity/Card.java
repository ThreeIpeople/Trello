package com.sparta.trellowiththreeipeople.card.entity;

import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.card.dto.CardRequest;
import com.sparta.trellowiththreeipeople.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @Column(name = "deadline", nullable = true)
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "column_id")
    private Bar bar;


    //문자열 형식의 날짜 ex)20240319 를 LocalDateTime 형식으로 변환
    public Card(User user, Bar bar,CardRequest cardRequest) {
        this.title = cardRequest.getTitle();
        this.content = cardRequest.getContent();
        this.user = user;
        this.bar = bar;
        if (cardRequest.getDeadline() != null && !cardRequest.getDeadline().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(cardRequest.getDeadline(), formatter);
            this.deadline = date.atStartOfDay(); // LocalDate를 LocalDateTime으로 변환
        }

    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
    public void updateCard(CardRequest cardRequest){
        this.title = cardRequest.getTitle();
        this.content = cardRequest.getContent();
        if(cardRequest.getDeadline()!=null&&!cardRequest.getDeadline().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(cardRequest.getDeadline(), formatter);
            this.deadline = date.atStartOfDay(); // LocalDate를 LocalDateTime으로 변환
        }
    }
}
