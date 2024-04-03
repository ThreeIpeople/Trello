package com.sparta.trellowiththreeipeople.board.controller;

import com.sparta.trellowiththreeipeople.board.dto.*;
import com.sparta.trellowiththreeipeople.board.service.BoardService;
import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<String> inputBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long boardId = boardService.save(requestDto, userDetails.getUser()
        );

//        return ResponseEntity.created(URI.create("/api/boards/" + boardId))
//
        return ResponseEntity.created(URI.create("/api/boards/" + boardId)).build();
    }

    @GetMapping("")
    public ResponseEntity<List<BoardListResponseDto>> getBoardListByUserId(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<BoardListResponseDto> listResponseDto = boardService.getBoardListByUserId(userDetails.getUser());

        return ResponseEntity.ok().body(listResponseDto);
    }

    @GetMapping("{boardId}")
   // @Cacheable(cacheNames = "boardCache",value = "boardCache", key = "#boardId")
    public ResponseEntity<BoardResponseDto> getBoardByBoardId(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        BoardResponseDto responseDto = boardService.getBoardByBoardId(boardId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("{boardId}")
    public ResponseEntity<String> updateBoard(
            @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long updatedBoardId = boardService.updateBoard(boardId, requestDto, userDetails.getUser());

        return ResponseEntity.created(URI.create("api/boards/" + updatedBoardId))
                .body("보드가 수정되었습니다.");
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boardService.deleteBoard(boardId, userDetails.getUser());

        return ResponseEntity.ok().build();//.body("성공적으로 보드 삭제가 되었습니다.");
    }

    @PatchMapping("{boardId}/invite")
    public ResponseEntity<String> inviteUserToBoard(
            @PathVariable Long boardId,
            @RequestBody InviteRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boardService.inviteUserToBoard(boardId, requestDto.getUserId(), userDetails.getUser());

        return ResponseEntity.ok().body("초대가 성공적으로 되었습니다.");

    }


}
