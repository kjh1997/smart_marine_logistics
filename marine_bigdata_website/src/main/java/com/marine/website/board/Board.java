package com.marine.website.board;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marine.website.category.Category;
import com.marine.website.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private SiteUser user;
    private String context;
    private String title;
    private LocalDateTime localDateTime;
    private Long alive;
    public Board() {
        this.localDateTime = LocalDateTime.now();
    }

}
