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

## Project Structure
src/main/java/com/astronaut/
├── app/ # Application entry point (CLI)
├── core/ # Schedule management logic
├── factory/ # Task creation logic (Factory pattern)
├── model/ # Task model/entity
├── observer/ # Notification system (Observer pattern)
└── resources/ # Configurations (Log4j2)


## Build & Run
### 1. Compile and Package
```bash
mvn clean package
2. Run Application
bash

java -jar target/astronaut-scheduler-1.0.0.jar
3. Example Commands
bash

add "Morning Exercise" 07:00 07:30 HIGH
view
complete ID1
remove ID1
exit
Logging
All actions are logged in logs/app.log using Log4j2.

Testing
Run unit tests with:

bash

mvn test