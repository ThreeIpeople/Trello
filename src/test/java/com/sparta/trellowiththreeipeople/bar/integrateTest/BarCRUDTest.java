package com.sparta.trellowiththreeipeople.bar.integrateTest;

import com.sparta.trellowiththreeipeople.bar.dto.BarRequestDto;
import com.sparta.trellowiththreeipeople.bar.entity.Bar;
import com.sparta.trellowiththreeipeople.bar.repository.BarRepository;
import com.sparta.trellowiththreeipeople.bar.service.BarService;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class BarCRUDTest {

    @Autowired
    BarService barService;

    @Autowired
    BarRepository barRepository;

    @Autowired
    BoardRepository boardRepository;

    @Nested
    @DisplayName("Bar create test")
    class createBarTest {
        @Test
        void createBar_success() {
            Board board = new Board();
            boardRepository.save(board);

            BarRequestDto barRequestDto = new BarRequestDto("createBar_success");
            barService.createBar(board.getId(), barRequestDto);
        }
    }

    @Nested
    @DisplayName("getBarList test")
    class getBarListTest {
        @Test
        void getBarList_success() {
            Board board = new Board();
            boardRepository.save(board);

            Bar bar = new Bar("getBarList_success", board);
            Bar bar2 = new Bar("getBarList_success2", board);
            Bar bar3 = new Bar("getBarList_success3", board);
            barRepository.saveAll(List.of(bar, bar2, bar3));

            assert barService.getBarList(board.getId()).size() == 3;
        }
    }

    @Nested
    @DisplayName("getBar test")
    class getBarTest {
        @Test
        void getBar_success() {
            Board board = new Board();
            boardRepository.save(board);

            Bar bar = new Bar("getBar_success", board);
            barRepository.save(bar);

            assert Objects.equals(barService.getBar(board.getId(), bar.getId()).getId(), bar.getId());
        }
    }

    @Nested
    @DisplayName("update bar test")
    class updateBarTest {
        @Test
        void updateBar_success() {
            Board board = new Board();
            boardRepository.save(board);

            Bar bar = new Bar("getBar_success", board);
            barRepository.save(bar);

            barService.updateBar(board.getId(), bar.getId(), new BarRequestDto("updateBar_success"));

            Bar findedBar = barRepository.findById(bar.getId()).orElse(new Bar());

            assert findedBar.getTitle().equals("updateBar_success");
        }

    }

    @Nested
    @DisplayName("delete bar test")
    class deleteBarTest {
        @Test
        void deleteBar_success() {
            Board board = new Board();
            boardRepository.save(board);

            Bar bar = new Bar("deleteBar_success", board);
            barRepository.save(bar);

            barService.deleteBar(board.getId(), bar.getId());
            System.out.println(barService.getBarList(board.getId()));
        }

    }
}

