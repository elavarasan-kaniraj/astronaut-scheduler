# Folder: observer

## Purpose
This folder demonstrates the **Observer Pattern** in the Astronaut Scheduler.  
It allows the system to automatically notify observers whenever tasks are added, removed, or modified, ensuring that all parts of the system stay updated without tight coupling.

## Responsibilities
- Define the **Observer interface** (`TaskObserver`)  
- Provide **concrete observer implementations** (`ConsoleNotifier`)  
- Allow the `ScheduleManager` (subject) to notify all observers of state changes  
- Decouple task updates from how they are displayed or handled  

## Key Classes
- **TaskObserver.java** → Interface that defines the `update()` method to receive notifications.  
- **ConsoleNotifier.java** → Concrete observer that prints task updates, conflicts, and removals to the console.  

## Design Pattern Usage
- Implements the **Observer Pattern**:  
  - `ScheduleManager` (Subject) maintains a list of observers.  
  - Observers (`ConsoleNotifier`) subscribe to updates.  
  - When a task changes, `ScheduleManager` notifies all observers automatically.  

## Example Responsibilities in Action
- Add Task → Observer notified → Console shows:  
  `"✅ Task added: 07:30 - 08:00 | Breakfast | MEDIUM | Pending | id=ID2"`  
- Remove Task → Observer notified → Console shows removal message.  
- Conflict Detected → Observer notified → Console shows warning about overlapping tasks.  

This folder ensures that **task updates are automatically broadcast** to all interested parties without requiring direct dependencies.
