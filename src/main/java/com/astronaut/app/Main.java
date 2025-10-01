package com.astronaut.app;

import com.astronaut.core.ScheduleManager;
import com.astronaut.factory.TaskFactory;
import com.astronaut.model.Task;
import com.astronaut.observer.ConsoleNotifier;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConsoleNotifier());

        System.out.println(" Astronaut Scheduler CLI");
        System.out.println("Type 'help' for commands, 'exit' to quit.");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;
            if ("exit".equalsIgnoreCase(line)) {
                System.out.println("Goodbye ");
                break;
            }
            if ("help".equalsIgnoreCase(line)) {
                printHelp();
                continue;
            }
            if (line.startsWith("add ")) {
                handleAdd(manager, line);
                continue;
            }
            if ("view".equalsIgnoreCase(line)) {
                handleView(manager);
                continue;
            }
            if (line.startsWith("remove ")) {
                handleRemove(manager, line);
                continue;
            }
            if (line.startsWith("complete ")) {
                handleComplete(manager, line);
                continue;
            }
            System.out.println(" Unknown command. Type 'help' for commands.");
        }
        sc.close();
    }

    private static void handleAdd(ScheduleManager manager, String line) {
        try {
            int firstQuote = line.indexOf('"');
            int secondQuote = line.indexOf('"', firstQuote + 1);
            if (firstQuote == -1 || secondQuote == -1) {
                System.out.println(" Invalid add format. Use: add \"description\" HH:mm HH:mm PRIORITY");
                return;
            }
            String desc = line.substring(firstQuote + 1, secondQuote);
            String rest = line.substring(secondQuote + 1).trim();
            String[] parts = rest.split("\\s+");
            if (parts.length < 3) {
                System.out.println(" Invalid add format. Use: add \"description\" HH:mm HH:mm PRIORITY");
                return;
            }
            String start = parts[0];
            String end = parts[1];
            String priority = parts[2];
            Task t = TaskFactory.create(desc, start, end, priority);
            boolean added = manager.addTask(t);
            if (!added) {
                // conflict printed by observer
            }
        } catch (Exception ex) {
            System.out.println(" Error creating task: " + ex.getMessage());
        }
    }

    private static void handleView(ScheduleManager manager) {
        List<Task> tasks = manager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println(" No tasks scheduled.");
            return;
        }
        // Header
        System.out.printf("%-4s %-7s %-7s %-36s %-7s %-12s %s%n",
                "No.", "Start", "End", "Description", "Prio", "Status", "ShortID");
        System.out.println(
                "--------------------------------------------------------------------------------------------");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String desc = t.getDescription();
            if (desc.length() > 34)
                desc = desc.substring(0, 31) + "...";
            String status = t.isCompleted() ? "Completed" : "Pending";
            System.out.printf("%-4d %-7s %-7s %-36s %-7s %-12s %s%n",
                    i + 1,
                    t.getStart().toString(),
                    t.getEnd().toString(),
                    desc,
                    t.getPriority().name(),
                    status,
                    t.getShortId());
        }
    }

    private static void handleRemove(ScheduleManager manager, String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            System.out.println(" Use: remove <index|shortId|fullId>");
            return;
        }
        String arg = parts[1].trim();
        arg = arg.replaceAll("[<>]", ""); 
        // numeric index?
        if (arg.matches("\\d+")) {
            int idx = Integer.parseInt(arg);
            boolean removed = manager.removeTaskByIndex(idx);
            System.out.println(removed ? " Task removed." : " No task at that index.");
            return;
        }
        if (arg.length() < 3) {
            System.out.println(" ID too short. Provide index or at least 3 chars of short id.");
            return;
        }
        // try remove by short id prefix
        boolean removedByShort = manager.removeTaskByShortId(arg);
        if (removedByShort) {
            System.out.println(" Task removed (by short id).");
            return;
        }
        // try full id
        boolean removedFull = manager.removeTask(arg);
        System.out.println(removedFull ? " Task removed." : " No matching task id found.");
    }

    private static void handleComplete(ScheduleManager manager, String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            System.out.println(" Use: complete <index|shortId|fullId>");
            return;
        }
        String arg = parts[1].trim();
        arg = arg.replaceAll("[<>]", "");
        // numeric index?
        if (arg.matches("\\d+")) {
            int idx = Integer.parseInt(arg);
            Optional<Task> maybe = manager.getTaskByIndex(idx);
            if (maybe.isPresent()) {
                Task t = maybe.get();
                t.markCompleted();
                System.out.println(" Marked as completed: " + t.getDescription());
            } else {
                System.out.println(" No task at that index.");
            }
            return;
        }
        // search by short id prefix or full id using simple loops (no streams/lambdas)
        List<Task> tasks = manager.getAllTasks();
        Task found = null;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getId().startsWith(arg)) {
                found = t;
                break;
            }
        }
        if (found != null) {
            found.markCompleted();
            System.out.println(" Marked as completed: " + found.getDescription());
        } else {
            System.out.println(" No matching task id found.");
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println(" add \"description\" HH:mm HH:mm PRIORITY  -> add a task");
        System.out.println(" view                                    -> view tasks (shows No. and short id)");
        System.out.println(
                " remove <index|shortId|fullId>           -> remove by number or id (short id = first 8 chars)");
        System.out.println(" complete <index|shortId|fullId>         -> mark as completed");
        System.out.println(" help                                    -> show this help");
        System.out.println(" exit                                    -> quit");
    }
}
