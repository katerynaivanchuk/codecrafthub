package com.example.codecrafthub.exception;

/**
 * Thrown when a requested course does not exist.
 */
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
