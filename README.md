# LearnTrack

LearnTrack is a console-based Student & Course Management System built
with Core Java. An admin uses a text menu to manage students, courses
and enrollments. All data is stored in memory (`ArrayList`), so it is
cleared when the program exits.

## Features

- **Students:** add, view all, search by ID, deactivate
- **Courses:** add, view all, activate / deactivate
- **Enrollments:** enroll a student in a course, view a student's
  enrollments, mark an enrollment completed or cancelled
- Invalid input (wrong menu option, letters instead of numbers, unknown
  IDs) shows an error message instead of crashing the program

## Project structure

```
src/com/airtribe/learntrack/
├── entity/     Person, Student, Trainer, Course, Enrollment
├── service/    StudentService, CourseService, EnrollmentService
├── exception/  EntityNotFoundException, InvalidInputException
├── util/       IdGenerator, InputValidator
└── ui/         Main
docs/           Setup_Instructions.md, JVM_Basics.md, Design_Notes.md
```

## Requirements

JDK 17 (any JDK 8 or newer works). Check with `java -version` and
`javac -version`.

## How to compile and run

From the project root (the folder that contains `src`).

**Windows (Command Prompt):**

```
mkdir out
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out com.airtribe.learntrack.ui.Main
```

**Windows (PowerShell):**

```
mkdir out
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out com.airtribe.learntrack.ui.Main
```

**Linux / macOS:**

```
mkdir out
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.ui.Main
```

**IntelliJ IDEA:** open the project, right-click `Main.java` and choose
*Run 'Main.main()'*.

## Sample session

```
1. Student Management -> 1. Add student
2. Course Management  -> 1. Add course
3. Enrollment Management -> 1. Enroll student in course
```