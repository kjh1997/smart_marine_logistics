package com.marine.website.board.boarddto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardListDTO {
    private List<BoardDTO> boardList;
    private long count;
}
