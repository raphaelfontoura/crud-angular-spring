package com.rdeveloper.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdeveloper.crudspring.model.Course;
import com.rdeveloper.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {

  private final CourseRepository courseRepository;

  @GetMapping
  public List<Course> getCourses() {
    return courseRepository.findAll();
  }
  
}
