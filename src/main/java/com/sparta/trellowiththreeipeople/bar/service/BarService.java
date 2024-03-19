package com.sparta.trellowiththreeipeople.bar.service;

import com.sparta.trellowiththreeipeople.bar.dto.BarRequestDto;
import com.sparta.trellowiththreeipeople.bar.dto.BarResponseDto;
import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BarService {
    private final BarRepository barRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void createBar(Long boardId, BarRequestDto barRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 보드 아이디입니다.")
        );
        Bar bar = new Bar(barRequestDto.getTitle(), board);
        barRepository.save(bar);
    }

    public List<BarResponseDto> getBarList(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 보드 아이디입니다.")
        );
        return barRepository.findAllByBoard(board)
                .stream()
                .map(bar -> new BarResponseDto(bar.getId(), bar.getTitle()))
                .toList();
    }

    public BarResponseDto getBar(Long boardId, Long barId) {
        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        return new BarResponseDto(bar.getId(), bar.getTitle());
    }

    @Transactional
    public void updateBar(Long boardId, Long barId, BarRequestDto barRequestDto) {
        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        bar.update(barRequestDto.getTitle());
    }

    @Transactional
    public void deleteBar(Long boardId, Long barId) {
        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        barRepository.delete(bar);
    }
}
