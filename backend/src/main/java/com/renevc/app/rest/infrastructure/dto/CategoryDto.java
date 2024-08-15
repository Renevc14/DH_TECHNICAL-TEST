package com.renevc.app.rest.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

import com.renevc.app.rest.infrastructure.entities.CategoryEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private String description;
    //Rebice user pero no lo muestra
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDto user;

    private List<TodoDto> todoList;

    public static CategoryEntity toEntity(CategoryDto categoryDto) {
        CategoryEntity category = new CategoryEntity();

        category.setUser(UserDto.toEntity(categoryDto.getUser()));
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    public static CategoryDto fromEntity(CategoryEntity category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .todoList(
                        category.getTodoList() != null ? category.getTodoList().stream().map(TodoDto::fromEntity).collect(Collectors.toList()) : null
                )
                .build();
    }
}