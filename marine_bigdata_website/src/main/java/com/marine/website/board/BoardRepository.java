package com.marine.website.board;

import com.marine.website.category.Category;
import com.marine.website.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findBytitle(String title);


}
