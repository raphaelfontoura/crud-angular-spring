package com.rdeveloper.crudspring.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.rdeveloper.crudspring.model.Course;
import com.rdeveloper.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Validated
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
  public Course createCourse(@RequestBody @Valid Course course) {
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    return courseRepository.save(course);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable @NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
    return courseRepository.findById(id)
        .map(record -> {
          record.setName(course.getName());
          record.setCategory(course.getCategory());
          Course updated = courseRepository.save(record);
          return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          courseRepository.deleteById(id);
          return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
  }

}
