# Folder: model

## Purpose
This folder contains the **data model classes** used in the Astronaut Scheduler.  
It defines the structure and properties of tasks that are scheduled, completed, or removed during execution.

## Responsibilities
- Represent a **Task** with all its attributes (description, start time, end time, priority, status, and unique ID).  
- Encapsulate task-related data in a single object.  
- Provide a standard format for task creation, manipulation, and display.  
- Serve as the bridge between the business logic (`core`) and persistence/notifications.  

## Key Class
- **Task.java** → Represents an individual task in the scheduler.  
  - Fields: description, start time, end time, priority, status, id  
  - Methods: getters/setters, status update (e.g., mark as completed), formatted display  

## Design Concept
- This folder uses **Plain Old Java Objects (POJOs)** to keep the data model simple and reusable.  
- It is designed to be independent of other layers, making the model reusable in different contexts.  

## Example Responsibilities in Action
- Input command `"add 'Breakfast' 07:30 08:00 MEDIUM"` → Creates a `Task` object  
- `Task` holds all relevant info → `"07:30 - 08:00 | Breakfast | MEDIUM | Pending | id=ID1"`  
- Other components (`core`, `observer`) rely on this structure to perform operations.  

This folder defines the **blueprint for tasks** and keeps all task-related data consistent and centralized.
