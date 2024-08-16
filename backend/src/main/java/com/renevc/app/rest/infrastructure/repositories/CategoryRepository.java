package com.renevc.app.rest.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renevc.app.rest.infrastructure.entities.CategoryEntity;

import java.time.ZonedDateTime;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    @Query("select t.category.id from TodoEntity t where t.id = :todoId")
    Long findCategoryByTodoId(Long todoId);

    @Query("select c from CategoryEntity c inner join TodoEntity t on t.category.id = c.id where t.startDate >= :startDate and t.startDate <= :endDate ")
    List<CategoryEntity> getAllTodoByCategoriesForToday(@Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate);

}
