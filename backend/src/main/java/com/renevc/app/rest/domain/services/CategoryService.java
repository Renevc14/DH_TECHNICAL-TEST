package com.renevc.app.rest.domain.services;

import com.renevc.app.rest.infrastructure.dto.CategoryDto;

import java.util.List;


public interface CategoryService {

    CategoryDto save(CategoryDto category);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);


    void delete(Long id);

    List<CategoryDto> getAllTodoByCategoriesForToday();
}
