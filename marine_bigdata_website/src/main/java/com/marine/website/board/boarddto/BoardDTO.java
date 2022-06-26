package com.marine.website.board.boarddto;

import com.marine.website.user.userdto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private String title;
    private String title2;

    private String context;

    private String username;

    private String category;
}
