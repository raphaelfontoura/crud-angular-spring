package com.rdeveloper.crudspring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rdeveloper.crudspring.model.Course;
import com.rdeveloper.crudspring.service.CourseService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService service) {
    this.courseService = service;
  }

  @GetMapping
  public List<Course> getCourses() {
    return courseService.list();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Course createCourse(@RequestBody @Valid Course course) {
    return courseService.create(course);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable @NotNull @Positive Long id) {
    return ResponseEntity.ok().body(courseService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
    return ResponseEntity.ok().body(courseService.update(id, course));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    courseService.delete(id);
  }

  @PostMapping("redirect")
  public ResponseEntity<Void> redirectUrl(@RequestBody @Valid Course course, HttpServletResponse response) {
    var result = courseService.create(course);
    var uri = UriComponentsBuilder.fromUriString("https://google.com/search?q={course}")
      .build(result.getName());
    try {
      response.sendRedirect(uri.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.created(uri).build();
  }

}
