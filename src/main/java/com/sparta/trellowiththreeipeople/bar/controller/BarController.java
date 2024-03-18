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
    public void createBar(@PathVariable Long boardId, @RequestBody BarRequestDto barRequestDto) {
        barService.createBar(boardId, barRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<BarResponseDto>> getBarList(@PathVariable Long boardId) {
        List<BarResponseDto> barResponseDtoList = barService.getBarList(boardId);
        return ResponseEntity.status(200).body(barResponseDtoList);
    }

    @GetMapping("/{barId}")
    public ResponseEntity<BarResponseDto> getBar(@PathVariable Long boardId, @PathVariable Long barId) {
        BarResponseDto barResponseDto = barService.getBar(boardId, barId);
        return ResponseEntity.status(200).body(barResponseDto);
    }

    @PutMapping("/{barId}")
    public void updateBar(@PathVariable Long boardId, @PathVariable Long barId, @RequestBody BarRequestDto barRequestDto) {
        barService.updateBar(boardId, barId, barRequestDto);
    }

    @DeleteMapping("/{barId}")
    public void deleteBar(@PathVariable Long boardId, @PathVariable Long barId) {
        barService.deleteBar(boardId, barId);
    }
}
