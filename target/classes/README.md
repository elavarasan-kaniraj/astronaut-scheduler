# Folder: resources

## Purpose
This folder contains **configuration files** and supporting resources for the Astronaut Scheduler.  
These files are not source code but are essential for logging, application settings, and runtime behavior.

## Responsibilities
- Store configuration for **Log4j2 logging**  
- Hold any other resource files required by the application (e.g., property files, configuration XMLs)  

## Key File
- **log4j2.xml** → Defines logging behavior and output location (`logs/app.log`).  

## Example Behavior
When a task is added, the following entry is written to `app.log`:
[HH:MM:SS] INFO com.astronaut.core.ScheduleManager - Task added: 07:30 - 08:00 | Breakfast | MEDIUM | Pending | id=ID2

This folder ensures that **logging and configuration are centralized** and can be updated without modifying source code.
