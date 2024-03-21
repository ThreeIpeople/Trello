package com.sparta.trellowiththreeipeople.bar.service;

import com.sparta.trellowiththreeipeople.bar.dto.BarResponseDto;
import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import com.sparta.trellowiththreeipeople.board.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarService {
    private final BarRepository barRepository;
    private final BoardUserRepository boardUserRepository;

    @Transactional
    public void createBar(Long boardId, String title, Long userId) {
        BoardUser boardUser = boardUserRepository.findBoardUserByBoardIdAndUserId(userId, boardId);

        if (boardUser == null) {
            throw new IllegalArgumentException("초대받지 않았거나, 존재하지 않는 보드입니다.");
        }

        Bar bar = new Bar(title, boardUser.getBoard(), userId);
        barRepository.save(bar);
    }

    @Transactional(readOnly = true)
    public List<BarResponseDto> getBarList(Long boardId, Long userId) {
        BoardUser boardUser = boardUserRepository.findBoardUserByBoardIdAndUserId(userId, boardId);

        if (boardUser == null) {
            throw new IllegalArgumentException("초대받지 않았거나, 존재하지 않는 보드입니다.");
        }

        return barRepository.findAllByBoard(boardId);
    }

    @Transactional(readOnly = true)
    public BarResponseDto getBar(Long boardId, Long barId, Long userId) {
        BoardUser boardUser = boardUserRepository.findBoardUserByBoardIdAndUserId(userId, boardId);

        if (boardUser == null) {
            throw new IllegalArgumentException("초대받지 않았습니다.");
        }

        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        return new BarResponseDto(bar.getId(), bar.getTitle());
    }

    @Transactional
    public void updateBar(Long boardId, Long barId, String title, Long userId) {
        BoardUser boardUser = boardUserRepository.findBoardUserByBoardIdAndUserId(userId, boardId);

        if (boardUser == null) {
            throw new IllegalArgumentException("초대받지 않았습니다.");
        }

        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        bar.update(title, userId);
    }

    @Transactional
    public void deleteBar(Long boardId, Long barId, Long userId) {
        BoardUser boardUser = boardUserRepository.findBoardUserByBoardIdAndUserId(userId, boardId);

        if (boardUser == null) {
            throw new IllegalArgumentException("초대받지 않았습니다.");
        }

        Bar bar = barRepository.findById(barId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 바 아이디입니다.")
        );

        if (!Objects.equals(bar.getBoardId(), boardId)) {
            throw new IllegalArgumentException("보드 아이디가 해당 바의 보드와 일치하지 않습니다.");
        }

        barRepository.updateBarAndDeletedBy(barId, userId);
    }
}
