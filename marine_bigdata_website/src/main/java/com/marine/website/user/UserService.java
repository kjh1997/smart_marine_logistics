package com.marine.website.user;


import com.marine.website.DataNotFoundException;
import com.marine.website.board.Board;
import com.marine.website.user.userdto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryDsl userRepositoryDsl;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        }else{
            throw new DataNotFoundException("siteuser not found");
        }
    }

    //Test 진행 중
    public SiteUser getEmail(String email) {
        Optional<SiteUser> siteUser = this.userRepository.findByemail(email);
        if (siteUser.isPresent()) {
            return siteUser.get(); //all siteuser information move
        }else{
            throw new DataNotFoundException("siteuser not found");
        }
    }

    public List<Board> getBoardList(UserDTO userDTO) {
        return userRepositoryDsl.getBoardList(userDTO.getName());
    }
}
