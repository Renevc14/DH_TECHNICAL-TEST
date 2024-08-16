package com.renevc.app.rest.infrastructure.services;

import com.renevc.app.rest.domain.services.CategoryService;
import com.renevc.app.rest.domain.exception.EntityNotFoundException;
import com.renevc.app.rest.domain.exception.ErrorCodes;
import com.renevc.app.rest.domain.exception.InvalidEntityException;
import com.renevc.app.rest.infrastructure.dto.CategoryDto;
import com.renevc.app.rest.infrastructure.entities.CategoryEntity;
import com.renevc.app.rest.infrastructure.repositories.CategoryRepository;
import com.renevc.app.rest.infrastructure.validators.CategoryValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryDto save(CategoryDto category) {
        List<String> errors = CategoryValidator.validateCategory(category);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", category);
            throw new InvalidEntityException("Category is not valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(category)));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null) {
            log.error("Category id is null");
            return null;
        }
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Category found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
        CategoryDto categoryDto =  CategoryDto.fromEntity(categoryEntity);
        return categoryDto;
    }


    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Category id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllTodoByCategoriesForToday() {
        return categoryRepository.getAllTodoByCategoriesForToday(ZonedDateTime.now().withHour(0).withMinute(0),
                ZonedDateTime.now().withHour(23).withMinute(59))
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
