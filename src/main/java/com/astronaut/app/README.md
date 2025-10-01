# Folder: app

## Purpose
This folder contains the **application entry point** of the Astronaut Scheduler.  
It includes the `Main.java` class, which launches the **Command-Line Interface (CLI)** and acts as the starting point of the program.

## Responsibilities
- Start the Astronaut Scheduler console application  
- Provide a user-friendly CLI for interacting with the system  
- Parse user commands and delegate execution to the `core` package  
- Connect together different design pattern implementations (Factory, Observer, etc.)

## Key Class
- **Main.java** → The entry point (`public static void main`) that runs the scheduler CLI.

## Example Run
```bash
java -jar target/astronaut-scheduler-1.0.0.jar
When executed, the application displays:

Astronaut Scheduler CLI
Type 'help' for commands, 'exit' to quit.


Users can then add, view, remove, and complete tasks via CLI commands.