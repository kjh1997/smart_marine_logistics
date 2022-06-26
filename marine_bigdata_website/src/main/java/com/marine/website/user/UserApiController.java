package com.marine.website.user;

import com.marine.website.board.Board;
import com.marine.website.user.userdto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/user/getuser")
    public SiteUser getUser(@RequestBody UserDTO userDTO) {
        return userService.getUser(userDTO.getName());
    }


    @GetMapping("/user/getboardlist")
    public List<Board> getBoardList(@RequestBody UserDTO userDTO) {
        return userService.getBoardList(userDTO);
    }
}
