package com.marine.website.board;

import com.marine.website.board.boarddto.BoardDTO;
import com.marine.website.category.CategoryService;
import com.marine.website.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardRepositoryDsl boardRepositoryDsl;

    private final UserService userService;
    private final CategoryService categoryService;
    public Long save(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContext(boardDTO.getContext());

        board.setCategory(categoryService.getCategoryByName(boardDTO.getCategory()));
        board.setUser(userService.getUser(boardDTO.getUsername()));

        Long boardId = boardRepositoryDsl.save(board);
        return boardId;

    }

    public Board getBoard(BoardDTO boardDTO) {
        return boardRepository.findBytitle(boardDTO.getTitle());
    }


    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    public Board modifyBoard(BoardDTO boardDTO) {
        Board board = boardRepository.findBytitle(boardDTO.getTitle());
        System.out.println("test");
        System.out.println(board.getTitle());
        board.setTitle(boardDTO.getTitle2());
        System.out.println("?1");
        board.setContext(boardDTO.getContext());
        System.out.println("?2");
        board.setCategory(categoryService.getCategoryByName(boardDTO.getCategory()));

        boardRepositoryDsl.save(board);
        return board;
    }

    public String deleteBoard(BoardDTO boardDTO) {
        boardRepository.deleteById(boardRepository.findBytitle(boardDTO.getTitle()).getId());
        return "success";
    }


}
