package com.sparta.trellowiththreeipeople.bar.controller;

import com.sparta.trellowiththreeipeople.bar.dto.BarRequestDto;
import com.sparta.trellowiththreeipeople.bar.dto.BarResponseDto;
import com.sparta.trellowiththreeipeople.bar.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/bars")
public class BarController {
    private final BarService barService;

    @PostMapping
    public ResponseEntity<String> createBar(@PathVariable Long boardId, @RequestBody BarRequestDto barRequestDto) {
        barService.createBar(boardId, barRequestDto);
        return ResponseEntity.ok().body("Bar가 정상적으로 생성되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<BarResponseDto>> getBarList(@PathVariable Long boardId) {
        List<BarResponseDto> barResponseDtoList = barService.getBarList(boardId);
        return ResponseEntity.ok().body(barResponseDtoList);
    }

    @GetMapping("/{barId}")
    public ResponseEntity<BarResponseDto> getBar(@PathVariable Long boardId, @PathVariable Long barId) {
        BarResponseDto barResponseDto = barService.getBar(boardId, barId);
        return ResponseEntity.ok().body(barResponseDto);
    }

    @PutMapping("/{barId}")
    public ResponseEntity<String> updateBar(@PathVariable Long boardId, @PathVariable Long barId, @RequestBody BarRequestDto barRequestDto) {
        barService.updateBar(boardId, barId, barRequestDto);
        return ResponseEntity.ok().body("Bar가 정상적으로 수정되었습니다.");

    }

    @DeleteMapping("/{barId}")
    public ResponseEntity<String> deleteBar(@PathVariable Long boardId, @PathVariable Long barId) {
        barService.deleteBar(boardId, barId);
        return ResponseEntity.ok().body("Bar가 정상적으로 삭제되었습니다.");
    }
}
