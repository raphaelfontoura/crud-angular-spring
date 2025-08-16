package com.rdeveloper.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.rdeveloper.crudspring.enums.Category;
import com.rdeveloper.crudspring.model.Course;
import com.rdeveloper.crudspring.model.Lesson;
import com.rdeveloper.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();
            Course course = new Course();
            course.setName("Angular com Spring");
            course.setCategory(Category.FRONT_END);

            Lesson lesson = new Lesson();
            lesson.setName("Introdução");
            lesson.setYoutubeUrl("2j0v1g3q4a8");
            lesson.setCourse(course);

            Lesson lesson2 = new Lesson();
            lesson2.setName("Angular");
            lesson2.setYoutubeUrl("2j0v1g3q4a8");
            lesson2.setCourse(course);

            course.getLessons().add(lesson);
            course.getLessons().add(lesson2);

            courseRepository.save(course);
        };
    }

}
