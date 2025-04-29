package com.rdeveloper.crudspring.dto;

import com.rdeveloper.crudspring.model.Lesson;

public record LessonDTO (
        Long id,
        String name,
        String youtubeUrl
) {
    public static LessonDTO from(Lesson lesson) {
        return new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl());
    }
}
