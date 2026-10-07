# Design Notes

## Why I used ArrayList instead of an array

The number of students, courses and enrollments is not known in advance
and keeps changing while the program runs. An array has a fixed size, so
I would have to guess a maximum or copy it into a bigger array by hand
whenever it filled up. `ArrayList` grows and shrinks automatically and
has ready-made methods such as `add`, `remove`, `get`, `size` and
`isEmpty`. This keeps the service code short and easy to read, for
example `students.add(student)` and `students.remove(student)`.

## Where I used static members and why

- **`IdGenerator` counters** (`studentIdCounter`, `courseIdCounter`,
  `enrollmentIdCounter`) are `static` because an ID must be unique across
  the whole program, not per object. One shared counter per entity type
  gives `1, 2, 3...` no matter which service creates the object. The
  methods `getNextStudentId()`, `getNextCourseId()` and
  `getNextEnrollmentId()` are static so they can be called without
  creating an `IdGenerator` object. Its constructor is `private` because
  the class is only a utility.
- **`InputValidator`** has only static methods (`parseInt`,
  `requireNonEmpty`, `requireEmail`), because it keeps no state and is
  just a helper.
- **`Enrollment.ACTIVE`, `COMPLETED` and `CANCELLED`** are
  `static final` constants. The status values are the same for every
  enrollment, so they are stored once in the class, and using constants
  avoids typing mistakes in strings.
- **`Main`'s fields and methods** are static because `main` is static and
  the program has only one console and one set of services.

## Where I used inheritance and what I gained from it

`Student` and `Trainer` both extend `Person`, which holds the shared
fields `id`, `firstName`, `lastName` and `email`.

- **Less duplicated code:** these fields and their getters/setters are
  written once in `Person`, and both subclasses reuse them through
  `super(...)` in their constructors.
- **Method overriding / polymorphism:** `Person.getDisplayName()` returns
  the full name. `Student` overrides it to add the batch, and `Trainer`
  overrides it to add the expertise. Calling `getDisplayName()` on a
  `Person` reference runs the version for the real object type.
- **Easy to extend:** a new kind of person, such as `Admin`, only needs
  to extend `Person` and add its own fields.

Other concepts used:

- **Encapsulation:** all fields are `private`, with public getters and
  setters.
- **Constructor overloading:** `Student` can be created with or without
  an email, and `StudentService.addStudent` has two overloaded versions.
- **`toString()` overriding:** each entity prints readable details
  instead of `ClassName@hash`.

## Clean code choices

- **Separation of concerns:** `entity` classes hold data, `service`
  classes hold the logic, and `Main` only shows menus, reads input and
  calls services.
- **Meaningful names:** `addStudent`, `findCourseById`, `deactivateStudent`
  and `getEnrollmentsForStudent` describe exactly what they do.
- **Small methods:** each method does one job. In `Main`, `addStudent()`
  and `printStudents()` are separate from `studentMenu()`.
- **Custom exceptions:** `EntityNotFoundException` and
  `InvalidInputException` give clear error messages that `Main` catches
  and shows to the user.