# Folder: test

## Purpose
This folder contains **unit tests** for the Astronaut Scheduler.  
It ensures that the application logic is correct, reliable, and works as expected.

## Responsibilities
- Provide automated tests for core scheduling functionality  
- Validate task creation, addition, conflict detection, and removal  
- Verify that the Observer and Factory patterns behave correctly  
- Ensure system robustness against invalid inputs  

## Key File
- **ScheduleManagerTest.java** → Unit tests for the scheduling logic (add, remove, conflict handling, complete).  

## Tools Used
- **JUnit 5** → Testing framework for writing and executing unit tests.  
- **Maven Surefire Plugin** → Runs tests during the Maven build process.  

## Example Run
To execute all tests, run:
```bash
mvn test
