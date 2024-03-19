package com.sparta.trellowiththreeipeople.bar.entity;

import com.sparta.trellowiththreeipeople.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bar")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE bar SET deleted_at=CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at IS NULL")
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Bar(String title, Board board) {
        this.title = title;
        this.board = board;
    }

    public Long getBoardId() {
        return this.board.getId();
    }

    public void update(String title) {
        this.title = title;
    }
}
