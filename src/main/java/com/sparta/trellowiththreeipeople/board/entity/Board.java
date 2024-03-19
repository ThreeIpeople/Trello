package com.sparta.trellowiththreeipeople.board.entity;

import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Board extends baseEntity{

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

    @OneToMany(mappedBy = "board")
    private List<BoardUser> users = new ArrayList<>();


    public Board(BoardRequestDto requestDto, User user) {
        this.boardName = requestDto.getBoardName();
        this.boardInfo = requestDto.getBoardInfo();
        this.users.add(new BoardUser(user, this));

    }



}
