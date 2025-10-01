package com.astronaut.observer;

import com.astronaut.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConsoleNotifier is a concrete Observer that:
 *   - Prints notifications to the console (for the user)
 *   - Logs events using Log4j2 (for debugging/troubleshooting)
 */
public class ConsoleNotifier implements TaskObserver {
    private static final Logger logger = LogManager.getLogger(ConsoleNotifier.class);

    @Override
    public void onConflict(Task existing, Task incoming) {
        String msg = String.format(" CONFLICT: '%s' overlaps with existing '%s'",
                                   incoming.getDescription(),
                                   existing.getDescription());
        System.out.println(msg);   // user-facing message
        logger.warn(msg);          // log for debugging
    }

    @Override
    public void onTaskAdded(Task added) {
        String msg = " Task added: " + added;
        System.out.println(msg);
        logger.info(msg);
    }

    @Override
    public void onTaskRemoved(Task removed) {
        String msg = " Task removed: " + removed;
        System.out.println(msg);
        logger.info(msg);
    }
}
