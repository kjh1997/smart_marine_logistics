package com.marine.website.category;


import com.marine.website.board.Board;
import com.marine.website.category.categorydto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/category/save")
    public String saveCategory(@RequestBody CategoryDTO categoryDTO) {
        System.out.println(categoryDTO.getName());
        String result = categoryService.save(categoryDTO);
        return result;
    }

    @GetMapping("/category/getboardlist")
    public List<Board> getboardList(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.getBoardListByCategoryName(categoryDTO);
    }

}
