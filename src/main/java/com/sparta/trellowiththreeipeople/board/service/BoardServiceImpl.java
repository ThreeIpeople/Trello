package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardListResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.board.repository.BoardUserRepository;
import com.sparta.trellowiththreeipeople.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardUserRepository boardUserRepository;

    @Override
    public BoardResponseDto save(String boardName, String boardInfo, User user){
        if(boardRepository.findBoardByBoardName(boardName)){
            throw new IllegalArgumentException("존재하는 보드 이름입니다.");
        }
        Board board = new Board(boardName, boardInfo, user);
        boardRepository.save(board);

        return new BoardResponseDto(board);


    }

    @Override
    public BoardResponseDto getBoardByBoardId(Long boardId, User user) {
        Board board = boardRepository.findBoardByBoardId(boardId).orElseThrow(
                ()-> new IllegalArgumentException("보드Id에 해당하는 보드를찾을 수 없습니다.")
        );
        BoardUser boardUser = boardUserRepository.findBoardUserByUserId(user.getId());
        if(! board.getUsers().contains(boardUser)){
            throw new IllegalArgumentException("해당 보드는 초대받은 유저만 확인할 수 있습니다.");
        }

        return new BoardResponseDto(board);
    }

    @Override
    public List<BoardListResponseDto> getBoardListByUserId(User user) {

       return boardUserRepository.findBoardUserByUserIdAndFetchBoards(user.getId())
               .stream()
                .map(BoardListResponseDto::new)
               .toList();
    }

}
