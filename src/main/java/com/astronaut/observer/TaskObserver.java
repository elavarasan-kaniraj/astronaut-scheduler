package com.astronaut.observer;

import com.astronaut.model.Task;

/**
 * Observer interface for receiving notifications from ScheduleManager.
 * 
 * Follows the Observer design pattern.
 * 
 * Implementations can:
 *   - Print messages to console
 *   - Write to logs
 *   - Send alerts, etc.
 */
public interface TaskObserver {

    /**
     * Called when a conflict occurs between two tasks.
     * @param existing the existing scheduled task
     * @param incoming the new conflicting task
     */
    void onConflict(Task existing, Task incoming);

    /**
     * Called when a task is successfully added.
     * @param added the task that was added
     */
    void onTaskAdded(Task added);

    /**
     * Called when a task is removed.
     * @param removed the task that was removed
     */
    void onTaskRemoved(Task removed);
}
