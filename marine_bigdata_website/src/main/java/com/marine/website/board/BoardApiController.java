package com.marine.website.board;


import com.marine.website.board.boarddto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/board/getList")
    public List<Board> getBoardListAll() {
        return boardService.boardList();
    }

    @PostMapping("/board/create")
    public Long createBoard(@RequestBody BoardDTO boardDTO) {
        System.out.println("1");
        Long id = boardService.save(boardDTO);
        return id;
    }

    @GetMapping("/board/getboard")
    public Board getBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.getBoard(boardDTO);
    }

    @PostMapping("/board/modify")
    public Board modifyBoard(@RequestBody BoardDTO boardDTO) {
        System.out.println(boardDTO.getTitle() + boardDTO.getCategory() + boardDTO.getContext());
        return boardService.modifyBoard(boardDTO);
    }

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.deleteBoard(boardDTO);
    }
}
