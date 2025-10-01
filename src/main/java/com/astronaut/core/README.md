# Folder: core

## Purpose
This folder contains the **core scheduling logic** of the Astronaut Scheduler.  
It defines how tasks are managed, validated, and coordinated within the system.

## Responsibilities
- Manage all scheduled tasks (create, view, remove, complete)  
- Detect and prevent overlapping or conflicting tasks  
- Notify observers (via Observer pattern) about task changes  
- Act as the central **controller** between the CLI, model, and observers  

## Key Class
- **ScheduleManager.java** → The central class that maintains the list of tasks, enforces rules, and triggers observer updates.  

## Design Pattern Usage
- Implements the **Observer Pattern** by notifying subscribers (e.g., `ConsoleNotifier`) when tasks are added, removed, or modified.  
- Collaborates with the **Factory Pattern** (`TaskFactory`) to create new tasks consistently.  

## Example Responsibilities in Action
- Add Task → `ScheduleManager` validates input, checks for conflicts, then stores the task.  
- Remove Task → Finds task by ID and deletes it, notifying observers.  
- Complete Task → Updates the task’s status to completed.  
- Notify Observers → Sends updates when task state changes.  

This folder is the **brain** of the Astronaut Scheduler — it ensures that tasks remain consistent, conflict-free, and always up to date.
