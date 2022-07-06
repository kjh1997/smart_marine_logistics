package com.marine.website.user;


import com.marine.website.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String auth;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList;
}
