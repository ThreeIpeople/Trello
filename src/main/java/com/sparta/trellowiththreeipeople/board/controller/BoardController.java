package com.sparta.trellowiththreeipeople.board.controller;

import com.sparta.trellowiththreeipeople.board.dto.BoardListResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.service.BoardServiceImpl;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("")
    public ResponseEntity<BoardResponseDto> inputBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        BoardResponseDto responseDto = boardService.save(
                requestDto.getBoardName(),
                requestDto.getBoardInfo(),
                userDetails.getUser()
        );

        return ResponseEntity.ok().body(responseDto);

    }

    @GetMapping("s")
    public ResponseEntity<List<BoardListResponseDto>> getBoardListByUserId(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        List<BoardListResponseDto> listResponseDto = boardService.getBoardListByUserId(userDetails.getUser());

        return ResponseEntity.ok().body(listResponseDto);
    }

    @GetMapping("{boardId}")
    public ResponseEntity<BoardResponseDto> getBoardByBoardId(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        BoardResponseDto responseDto = boardService.getBoardByBoardId(boardId,userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }




}
