package com.sparta.trellowiththreeipeople.board.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class board extends baseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_name", nullable = false)
    private String boardName;

    @Column(name = "board_info", nullable = false)
    private String boardInfo;

    @Column(name = "color", nullable = false)
    private BoardColorEnum colorEnum = BoardColorEnum.RED;


}
