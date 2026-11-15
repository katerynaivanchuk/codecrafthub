package com.example.codecrafthub.controller;

import com.example.codecrafthub.model.Course;
import com.example.codecrafthub.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for course operations.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * POST /api/courses
     *
     * Creates a new course.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.createCourse(course);
    }

    /**
     * GET /api/courses
     *
     * Returns all courses.
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * GET /api/courses/{id}
     *
     * Returns one course by ID.
     */
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    /**
     * PUT /api/courses/{id}
     *
     * Replaces an existing course.
     */
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course
    ) {
        return courseService.updateCourse(id, course);
    }

    /**
     * DELETE /api/courses/{id}
     *
     * Deletes an existing course.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCourseStats() {
        List<Course> courses = courseService.getAllCourses();

        Map<String, Long> byStatus = new LinkedHashMap<>();
        byStatus.put("Not Started", courses.stream()
                .filter(course -> "Not Started".equals(course.getStatus()))
                .count());
        byStatus.put("In Progress", courses.stream()
                .filter(course -> "In Progress".equals(course.getStatus()))
                .count());
        byStatus.put("Completed", courses.stream()
                .filter(course -> "Completed".equals(course.getStatus()))
                .count());

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("total", courses.size());
        stats.put("byStatus", byStatus);

        return ResponseEntity.ok(stats);
    }
}