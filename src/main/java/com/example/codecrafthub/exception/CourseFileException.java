package com.example.codecrafthub.exception;

/**
 * Thrown when courses.json cannot be read or written.
 */
public class CourseFileException extends RuntimeException {

    public CourseFileException(String message, Throwable cause) {
        super(message, cause);
    }
}