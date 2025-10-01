package com.astronaut.core;

import com.astronaut.model.Task;
import com.astronaut.observer.TaskObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Singleton that manages scheduled tasks and notifies observers.
 */
public class ScheduleManager {
    private static final Logger logger = LogManager.getLogger(ScheduleManager.class);
    private static final ScheduleManager INSTANCE = new ScheduleManager();
    private ScheduleManager() {}

    public static ScheduleManager getInstance() { return INSTANCE; }

    private final List<Task> tasks = new ArrayList<>();
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();

    public synchronized void addObserver(TaskObserver o) { observers.add(o); }
    public synchronized void removeObserver(TaskObserver o) { observers.remove(o); }

    public synchronized boolean addTask(Task t) {
        for (Task existing : tasks) {
            if (existing.overlapsWith(t)) {
                logger.warn("Conflict: '{}' overlaps with '{}'", t.getDescription(), existing.getDescription());
                observers.forEach(obs -> obs.onConflict(existing, t));
                return false;
            }
        }
        tasks.add(t);
        tasks.sort(Comparator.comparing(Task::getStart));
        logger.info("Task added: {}", t);
        observers.forEach(obs -> obs.onTaskAdded(t));
        return true;
    }

    public synchronized boolean removeTask(String id) {
        Optional<Task> match = tasks.stream().filter(x -> x.getId().equals(id)).findFirst();
        if (match.isEmpty()) return false;
        Task removed = match.get();
        tasks.remove(removed);
        logger.info("Task removed: {}", removed);
        observers.forEach(obs -> obs.onTaskRemoved(removed));
        return true;
    }

    // --- New helper: remove by shortId (prefix of UUID) ---
    public synchronized boolean removeTaskByShortId(String shortIdPrefix) {
        if (shortIdPrefix == null || shortIdPrefix.isBlank()) return false;
        Optional<Task> match = tasks.stream()
                .filter(t -> t.getId().startsWith(shortIdPrefix))
                .findFirst();
        if (match.isEmpty()) return false;
        Task removed = match.get();
        tasks.remove(removed);
        logger.info("Task removed by shortId ({}): {}", shortIdPrefix, removed);
        observers.forEach(obs -> obs.onTaskRemoved(removed));
        return true;
    }

    // --- New helper: remove by index shown in the view (1-based) ---
    public synchronized boolean removeTaskByIndex(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > tasks.size()) return false;
        Task removed = tasks.remove(oneBasedIndex - 1);
        logger.info("Task removed by index ({}): {}", oneBasedIndex, removed);
        observers.forEach(obs -> obs.onTaskRemoved(removed));
        return true;
    }

    // --- New helper: find by index for reading or completing ---
    public synchronized Optional<Task> getTaskByIndex(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > tasks.size()) return Optional.empty();
        return Optional.of(tasks.get(oneBasedIndex - 1));
    }

    public synchronized List<Task> getAllTasks() {
        return tasks.stream().collect(Collectors.toUnmodifiableList());
    }

    public synchronized void clearAllTasks() {
        tasks.clear();
        logger.info("All tasks cleared.");
    }
}
