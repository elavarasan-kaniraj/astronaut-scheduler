package com.astronaut.core;

import com.astronaut.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleManagerTest {

    @Test
    public void addTask_conflictDetected() {
        ScheduleManager manager = ScheduleManager.getInstance();

        // Clear any previous tasks by removing all (singleton reused in tests)
        manager.getAllTasks().forEach(t -> manager.removeTask(t.getId()));

        Task t1 = new Task("Task 1", LocalTime.of(9,0), LocalTime.of(10,0), Task.Priority.MEDIUM);
        Task t2 = new Task("Task 2", LocalTime.of(9,30), LocalTime.of(10,30), Task.Priority.HIGH);

        assertTrue(manager.addTask(t1));
        // overlapping should be rejected
        assertFalse(manager.addTask(t2));
        // cleanup
        manager.removeTask(t1.getId());
    }
}
