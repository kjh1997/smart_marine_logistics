package com.marine.website.category;

import com.marine.website.DataNotFoundException;

import com.marine.website.board.Board;
import com.marine.website.category.categorydto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryDsl categoryRepositoryDsl;
    public Category getCategoryByName(String name) {
        Optional<Category> category = this.categoryRepository.findByName(name);
        if (category.isPresent()) {
            return category.get();
        }else{
            throw new DataNotFoundException("category not found");
        }
    }

    public String save(CategoryDTO categoryDTO) {
        System.out.println("??2");
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepositoryDsl.saveCategory(category);
        return "success";
    }

    public List<Board> getBoardListByCategoryName(CategoryDTO categoryDTO) {
        return categoryRepositoryDsl.getBoardListByCategoryName(categoryDTO.getName());
    }

    public List<Category> getList() {
        return categoryRepository.findAll();
    }

}
