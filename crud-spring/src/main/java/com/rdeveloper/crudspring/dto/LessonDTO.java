package com.rdeveloper.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.rdeveloper.crudspring.model.Lesson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonDTO (
        Long id,
        @NotNull @NotBlank @Length(min = 5, max = 100) String name,
        @NotNull @NotBlank @Length(min = 10, max = 11) String youtubeUrl
) {
    public static LessonDTO from(Lesson lesson) {
        return new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl());
    }
}
