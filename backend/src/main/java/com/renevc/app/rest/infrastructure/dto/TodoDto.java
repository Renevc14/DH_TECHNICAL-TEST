package com.renevc.app.rest.infrastructure.dto;

import com.renevc.app.rest.infrastructure.entities.TodoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.renevc.app.rest.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private Long id;

    private String title;

    private String description;

    private ZonedDateTime startDate;

    private Status status;

    private boolean favorite;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CategoryDto category;

    public static TodoEntity toEntity(TodoDto todoDto) {
        final TodoEntity todo = new TodoEntity();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setStatus(todoDto.getStatus().toString());
        todo.setFavorite(todoDto.isFavorite());
        todo.setStartDate(ZonedDateTime.now());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

        return todo;
    }

    public static TodoDto fromEntity(TodoEntity todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .status(Status.valueOf(todo.getStatus()))
                .favorite(todo.isFavorite())
                //.category(CategoryDto.fromEntity(todo.getCategory()))
                .build();
    }
}
