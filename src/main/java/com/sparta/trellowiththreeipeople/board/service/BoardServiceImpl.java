package com.sparta.trellowiththreeipeople.board.service;

import com.sparta.trellowiththreeipeople.board.dto.BoardListResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardResponseUsersResponseDto;
import com.sparta.trellowiththreeipeople.board.dto.BoardUpdateRequestDto;
import com.sparta.trellowiththreeipeople.board.entity.Board;
import com.sparta.trellowiththreeipeople.board.entity.BoardUser;
import com.sparta.trellowiththreeipeople.board.repository.BoardRepository;
import com.sparta.trellowiththreeipeople.board.repository.BoardUserRepository;
import com.sparta.trellowiththreeipeople.exception.BoardUserNotFoundException;
import com.sparta.trellowiththreeipeople.exception.UserNotFoundException;
import com.sparta.trellowiththreeipeople.user.entity.User;
import com.sparta.trellowiththreeipeople.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardUserRepository boardUserRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional

    public BoardResponseDto save(String boardName, String boardInfo, User user) {
        if (boardRepository.existsBoardByBoardName(boardName)) {

            throw new IllegalArgumentException("존재하는 보드 이름입니다.");
        }
        Board board = new Board(boardName, boardInfo, user);
        boardRepository.save(board);

        List<BoardResponseUsersResponseDto> users = board.getUsers().stream()
                .map(BoardResponseUsersResponseDto::new)
                .toList();

        return new BoardResponseDto(board, users);


    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardByBoardId(Long boardId, User user) {
        Board board = getBoard(boardId);
        BoardUser boardUser = getBoardUser(boardId, user);
        if (!isContainsBoardUser(board, boardUser)) {

            throw new IllegalArgumentException("해당 보드는 초대받은 유저만 확인할 수 있습니다.");
        }
        List<BoardResponseUsersResponseDto> users = board.getUsers().stream()
                .map(BoardResponseUsersResponseDto::new)
                .toList();

        return new BoardResponseDto(board, users);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardListByUserId(User user) {

        return boardUserRepository.findBoardUserByUserIdAndFetchBoards(user.getId())
                .stream()
                .map(BoardListResponseDto::new)
                .toList();
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardUpdateRequestDto requestDto, User user) {
        Board board = getBoard(boardId);

        BoardUser boardUser = getBoardUser(boardId, user);
        if (!isContainsBoardUser(board, boardUser)) {

            throw new IllegalArgumentException("해당 보드는 보드 사용유저만 수정할 수 있습니다.");
        }
        board.update(requestDto, boardUser);

        List<BoardResponseUsersResponseDto> users = board.getUsers().stream()
                .map(BoardResponseUsersResponseDto::new)
                .toList();

        return new BoardResponseDto(board, users);


    }


    @Override
    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board board = getBoard(boardId);

        if (!board.getCreatedUser().equals(user.getId())) {
            throw new ValidationException("보드 삭제는 보드 생성자만 삭제할 수 있습니다.");
        }
        List<BoardUser> boardUsers = boardUserRepository.getBoardUserByBoardId(boardId);

        boardUserRepository.deleteAllByBoardUsers(boardUsers);
        boardRepository.delete(board);

    }

    @Override
    @Transactional
    public void inviteUserToBoard(Long boardId, Long userId, User user) {
        Board board = getBoard(boardId);

        BoardUser boardUser = getBoardUser(boardId, user);
        if (!isContainsBoardUser(board, boardUser)) {
            throw new IllegalArgumentException("보드 초대는 보드 사용자만 초대가능합니다.");
        }
        User invitedUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("해당하는 유저를 찾을 수 없습니다.")
        );
        board.inviteUser(invitedUser);

    }

    private static boolean isContainsBoardUser(Board board,BoardUser boardUser) {
        return boardUser.getBoard().equals(board);
    }

    private BoardUser getBoardUser(Long boardId, User user) {
        return boardUserRepository.findBoardUserByBoardIdAndUserId(boardId, user.getId()).orElseThrow(
                ()-> new BoardUserNotFoundException("해당 하는 보드 유저를 찾을 수 없습니다."));
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findBoardByBoardId(boardId).orElseThrow(
                () -> new IllegalArgumentException("보드Id에 해당하는 보드를찾을 수 없습니다.")

        );
    }
}
