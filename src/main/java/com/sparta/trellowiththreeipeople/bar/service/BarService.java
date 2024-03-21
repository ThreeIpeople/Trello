package com.sparta.trellowiththreeipeople.bar.service;

import com.sparta.trellowiththreeipeople.bar.dto.BarRequestDto;
import com.sparta.trellowiththreeipeople.bar.dto.BarResponseDto;
import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.exception.BoardNotFoundException;
import com.sparta.trellowiththreeipeople.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarService {
    private final BarRepository barRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void createBar(Long boardId, BarRequestDto barRequestDto, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 보드 아이디입니다.")
        );
        Bar bar = new Bar(barRequestDto.getTitle(), board, user.getId());
        barRepository.save(bar);
    }

    @Transactional(readOnly = true)
    public List<BarResponseDto> getBarList(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 보드 아이디입니다.")
        );

        return barRepository.findAllByBoard(boardId);
    }

    @Transactional(readOnly = true)
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
    public void updateBar(Long boardId, Long barId, BarRequestDto barRequestDto, User user) {
        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        bar.update(barRequestDto.getTitle(), user.getId());
    }

    @Transactional
    public void deleteBar(Long boardId, Long barId, User user) {
        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        barRepository.updateBarAndDeletedBy(barId, user.getId());
    }
}
