package com.renevc.app.rest.infrastructure.validators;

import org.springframework.util.StringUtils;

import com.renevc.app.rest.infrastructure.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validateCategory(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();
        if (categoryDto == null) {
            errors.add("Please fill the name");
            errors.add("Please fill the description");
            return  errors;
        }
        if (StringUtils.isEmpty(categoryDto.getName())) {
            errors.add("Please fill the name");
        }
        if (StringUtils.isEmpty(categoryDto.getDescription())) {
            errors.add("Please fill the description");
        }
        return errors;
    }
}
