# Folder: factory

## Purpose
This folder demonstrates the **Factory Pattern** for creating tasks in the Astronaut Scheduler.  
It centralizes task creation logic, ensuring tasks are built consistently and validated before being added to the schedule.

## Responsibilities
- Encapsulate the logic for creating new `Task` objects  
- Validate input (time format, priority, description) before creating a task  
- Simplify object creation for the CLI and `ScheduleManager`  
- Hide the complexity of task creation from other components  

## Key Class
- **TaskFactory.java** → Provides a method to create tasks with required attributes (description, time range, priority).  

## Design Pattern Usage
- **Factory Pattern** is used here:  
  - Client code requests a task from the factory  
  - The factory validates and constructs the `Task` object  
  - This ensures separation of concerns (clients don’t handle low-level creation logic)  

## Example Responsibilities in Action
- Input: `"Morning Exercise" 07:00 07:30 HIGH`  
- `TaskFactory` → Parses input, validates times and priority, creates a `Task` instance  
- Output: A fully initialized `Task` object ready to be managed by `ScheduleManager`  

This folder ensures that **task creation is standardized, validated, and decoupled** from other system components.
