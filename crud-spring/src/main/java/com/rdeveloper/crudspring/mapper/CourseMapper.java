package com.rdeveloper.crudspring.mapper;

import org.springframework.stereotype.Component;

import com.rdeveloper.crudspring.dto.CourseDTO;
import com.rdeveloper.crudspring.enums.Category;
import com.rdeveloper.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
            course.getId(), 
            course.getName(), 
            course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(Category.fromValue(courseDTO.category()));
        return course;
    }

}
