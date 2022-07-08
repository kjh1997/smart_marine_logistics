package com.marine.website.board;


import com.marine.website.board.boarddto.BoardDTO;
import com.marine.website.board.boarddto.BoardListDTO;
import com.marine.website.board.boarddto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardApiController {
    private final BoardRepositoryDsl boardRepositoryDsl;
    private final BoardService boardService;

//    @GetMapping("/board/count")
//    public int getBoardListCount(){
//        return boardService.boardListCount();
//    }
//    @GetMapping("/board/getList")
//    public List<BoardDTO> getBoardListAll(@RequestParam Integer page ) {
//        List<Board> data = boardRepositoryDsl.getBoardList(page);
//        List<BoardDTO> result = new ArrayList<>();
//        for(Board i : data){
//            System.out.println(i.getUser().getUsername() + "  ||  " + i.getId() + "  ||  "+ i.getLocalDateTime() + "  ||  "+ i.getContext() + "  ||  "+  i.getTitle());
//            BoardDTO dto = new BoardDTO();
//            dto.setId(i.getId());
//            dto.setTitle(i.getTitle());
//            dto.setContext(i.getContext());
//            dto.setLocalDateTime(i.getLocalDateTime());
//            dto.setUsername(i.getUser().getUsername());
//            dto.setCategory(i.getCategory().getName());
//            dto.setTitle2("");
//            result.add(dto) ;
//        }
//        return result;
//    }
    @GetMapping("/board/getList")
    public BoardListDTO TEST_CONTROLLER(@RequestParam Integer page, String keyword, String category ) {
        BoardListDTO result = boardService.getBoardList(page, keyword, category);
        System.out.println(result.getCount());
        return result;
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

//    @PostMapping("/board/modify")
//    public Board modifyBoard(@RequestBody BoardDTO boardDTO) {
//        System.out.println(boardDTO.getTitle() + boardDTO.getCategory() + boardDTO.getContext());
//        return boardService.modifyBoard(boardDTO);
//    }

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.deleteBoard(boardDTO);
    }
}
