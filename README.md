# Astronaut Scheduler

## Overview
Astronaut Scheduler is a **Java-based console application** that demonstrates the use of multiple **design patterns** while solving a real-world inspired problem: managing astronaut daily tasks and schedules.  
It is implemented using **Maven**, with clear separation of concerns and modular folder structure.

## Key Features
- Add, view, complete, and remove tasks with priorities and time ranges  
- Prevent overlapping tasks with conflict detection  
- Real-time notifications using the **Observer Pattern**  
- Flexible task creation using the **Factory Pattern**  
- Clean logging with **Log4j2**  
- Unit testing with **JUnit 5**  

## Design Patterns Used
- **Factory Pattern** → For creating tasks  
- **Observer Pattern** → For notifying changes  
- Additional patterns explained in `design_patterns/`  


### Folder Explanations
- **app/** → Contains `Main.java`, the console entry point that starts the scheduler.  
- **core/** → Implements the core scheduling logic (`ScheduleManager`) including conflict detection and task management.  
- **factory/** → Encapsulates task creation logic using the **Factory Pattern** (`TaskFactory`).  
- **model/** → Defines data models, mainly the `Task` class representing scheduled items.  
- **observer/** → Implements the **Observer Pattern** to notify observers (e.g., console) about task updates or conflicts.  
- **resources/** → Holds external configuration files such as `log4j2.xml` for logging.  


## Build & Run
### 1. Compile and Package
```bash
mvn clean package

2. Run Application
   java -jar target/astronaut-scheduler-1.0.0.jar

3. Example Commands

   add "Morning Exercise" 07:00 07:30 HIGH
   view
   complete ID1
   remove ID1
   exit

Logging
   All actions are logged in logs/app.log using Log4j2.

Testing
   Run unit tests with:
      mvn test