package com.marine.website.user;

import com.marine.website.board.Board;
import com.marine.website.user.userdto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user/register")
    public Long registerUser(@RequestBody UserDTO userDTO) {
        System.out.println("register start");
        SiteUser user =  userService.createAPI(userDTO);
        return user.getId();
    }

    @PostMapping("/user/login")
    public Long userLogin(@RequestBody UserDTO userDTO) {

        return null;
    }


    @GetMapping("/user/getuser")
    public SiteUser getUser(@RequestBody UserDTO userDTO) {
        return userService.getUser(userDTO.getName());
    }


    @GetMapping("/user/getboardlist")
    public List<Board> getBoardList(@RequestBody UserDTO userDTO) {
        return userService.getBoardList(userDTO);
    }
}
