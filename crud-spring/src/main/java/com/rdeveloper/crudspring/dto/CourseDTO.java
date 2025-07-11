package com.rdeveloper.crudspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rdeveloper.crudspring.enums.Category;
import com.rdeveloper.crudspring.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseDTO(
        @JsonProperty("_id") Long id,

        @NotBlank @NotNull @Size(min = 5, max = 100) String name,

        @NotNull @ValueOfEnum(enumClass = Category.class) String category,
        
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
