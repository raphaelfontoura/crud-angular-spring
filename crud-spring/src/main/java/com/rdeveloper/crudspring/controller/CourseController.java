package com.rdeveloper.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Course createCourse(@RequestBody Course course) {
    // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    return courseRepository.save(course);
  }
}
