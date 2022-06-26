package com.marine.website.category;

import com.marine.website.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @Column(name="category_id")
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Board> boardList;

}

