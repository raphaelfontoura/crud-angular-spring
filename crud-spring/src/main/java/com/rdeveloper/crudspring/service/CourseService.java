package com.rdeveloper.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rdeveloper.crudspring.model.Course;
import com.rdeveloper.crudspring.repository.CourseRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public Course findById(@NotNull @Positive Long id) {
    return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
  }

  public Course create(@Valid Course course) {
    return courseRepository.save(course);
  }

  public Course update(@NotNull @Positive Long id, @Valid Course course) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.getName());
          recordFound.setCategory(course.getCategory());
          return courseRepository.save(recordFound);
        })
        .orElseThrow(() -> new EntityNotFoundException("Course not found"));
  }

  public boolean delete(@NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          courseRepository.deleteById(id);
          return true;
        })
        .orElse(false);
  }

}
