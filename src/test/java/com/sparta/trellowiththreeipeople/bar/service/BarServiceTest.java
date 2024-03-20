package com.sparta.trellowiththreeipeople.bar.service;

import com.sparta.trellowiththreeipeople.bar.dto.BarRequestDto;
import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.user.dto.request.CreateUserRequestDto;
import com.sparta.trellowiththreeipeople.user.entity.User;
import com.sparta.trellowiththreeipeople.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BarServiceTest {
    @Autowired
    BarService barService;

    @Autowired
    BarRepository barRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void test01() {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("username", "password");
        User user = new User(createUserRequestDto, "encryptedPassword");
        userRepository.save(user);

        Board board = new Board("testBoard", "testBoard01", user);
        boardRepository.save(board);

        barService.createBar(board.getBoardId(), new BarRequestDto("create"));
    }

    @Test
    void test02() {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("username", "password");
        User user = new User(createUserRequestDto, "encryptedPassword");
        userRepository.save(user);

        Board board = new Board("testBoard", "testBoard01", user);
        boardRepository.save(board);

        Bar bar = new Bar("created", board);
        barRepository.save(bar);

        barService.updateBar(board.getBoardId(), bar.getId(), new BarRequestDto("updated"));
    }
}