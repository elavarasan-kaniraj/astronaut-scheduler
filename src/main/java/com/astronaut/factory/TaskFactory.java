package com.astronaut.factory;

import com.astronaut.model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Factory class for creating Task objects from string inputs.
 * Demonstrates the Factory design pattern.
 *
 * Example usage:
 *   Task t = TaskFactory.create("Exercise", "07:00", "08:00", "HIGH");
 */
public class TaskFactory {

    /**
     * Creates a Task from string inputs.
     * @param description description of task
     * @param startStr start time in HH:mm format
     * @param endStr end time in HH:mm format
     * @param priorityStr priority (LOW, MEDIUM, HIGH)
     * @return new Task object
     * @throws IllegalArgumentException if inputs are invalid
     */
    public static Task create(String description, String startStr, String endStr, String priorityStr) {
        try {
            LocalTime start = LocalTime.parse(startStr); // HH:mm format
            LocalTime end = LocalTime.parse(endStr);
            Task.Priority priority = Task.Priority.valueOf(priorityStr.toUpperCase());
            return new Task(description, start, end, priority);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(" Invalid time format. Use HH:mm (e.g., 07:30)", ex);
        } catch (IllegalArgumentException ex) {
            if (ex.getMessage().contains("No enum constant")) {
                throw new IllegalArgumentException(" Invalid priority. Use LOW, MEDIUM, or HIGH.");
            }
            throw ex; // rethrow other validation errors
        }
    }
}
