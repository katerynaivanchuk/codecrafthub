 package com.example.codecrafthub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Represents a course that the user wants to learn.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Course {

    /**
     * The ID is generated automatically by the service.
     *
     * READ_ONLY means clients do not need to provide it in POST or PUT requests.
     */
    @JsonProperty(value = "id")
    private Long id;

    /**
     * Course name is required and cannot be blank.
     */
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String name;

    /**
     * Course description is required and cannot be blank.
     */
    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String description;

    /**
     * Java uses camelCase, but JSON uses target_date.
     *
     * The expected JSON date format is:
     *
     *     YYYY-MM-DD
     *
     * Example:
     *
     *     2026-12-31
     */
    @NotNull(message = "target_date is required")
    @JsonProperty("target_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    /**
     * The status must be exactly one of:
     *
     * - Not Started
     * - In Progress
     * - Completed
     */
    @NotBlank(message = "status is required")
    @Pattern(
            regexp = "Not Started|In Progress|Completed",
            message = "status must be exactly one of: Not Started, In Progress, Completed"
    )
    @JsonProperty("status")
    private String status;

    /**
     * The creation timestamp is generated automatically.
     *
     * READ_ONLY prevents clients from changing it.
     */
    @JsonProperty(value = "created_at")
    private Instant createdAt;

    /**
     * Required by Jackson when reading JSON.
     */
    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}