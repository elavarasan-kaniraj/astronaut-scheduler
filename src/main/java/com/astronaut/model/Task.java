package com.astronaut.model;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a scheduled task with:
 *  - Unique ID
 *  - Description
 *  - Start and end times
 *  - Priority
 *  - Completion status
 */
public class Task {
    private final String id;          // Unique identifier (UUID)
    private String description;
    private LocalTime start;
    private LocalTime end;
    private Priority priority;
    private boolean completed;

    public enum Priority { LOW, MEDIUM, HIGH }

    public Task(String description, LocalTime start, LocalTime end, Priority priority) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description is required.");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end times are required.");
        }
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        this.id = UUID.randomUUID().toString();
        this.description = description.trim();
        this.start = start;
        this.end = end;
        this.priority = (priority == null) ? Priority.MEDIUM : priority;
        this.completed = false;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getDescription() { return description; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    // --- Convenience: short id for human use (first 8 chars of UUID) ---
    public String getShortId() {
        return id.length() >= 8 ? id.substring(0, 8) : id;
    }

    // --- Mutators ---
    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description.trim();
    }

    public void markCompleted() { this.completed = true; }

    /**
     * Check if two tasks overlap using half-open interval [start, end)
     */
    public boolean overlapsWith(Task other) {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

    @Override
    public String toString() {
        return String.format("%s - %s | %s | %s | %s | id=%s",
                start, end,
                description,
                priority,
                completed ? " Completed" : " Pending",
                id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task t = (Task) o;
        return Objects.equals(id, t.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
