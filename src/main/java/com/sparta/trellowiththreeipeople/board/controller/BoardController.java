package com.sparta.trellowiththreeipeople.board.controller;

import com.sparta.trellowiththreeipeople.board.dto.BoardRequestDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardServiceImpl boardService;

    @RequestMapping("")
    public ResponseEntity<BoardResponseDto> inputBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        BoardResponseDto responseDto = boardService.save(requestDto,userDetails.getUser());

        return ResponseEntity.ok().body(responseDto);

    }
}
