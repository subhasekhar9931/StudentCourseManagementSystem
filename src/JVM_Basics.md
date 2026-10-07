# JVM Basics

## What is JDK, JRE and JVM?

**JVM (Java Virtual Machine)** is the program that actually runs Java
code. It reads bytecode, converts it into instructions the computer's
processor understands, and manages memory (including garbage
collection). Each operating system has its own JVM.

**JRE (Java Runtime Environment)** is everything needed to *run* a Java
program: the JVM plus the standard class libraries (such as
`java.util.ArrayList`). A user who only wants to run a Java application
needs just the JRE.

**JDK (Java Development Kit)** is everything needed to *write and run*
Java programs. It contains the JRE plus developer tools, mainly `javac`
(the compiler), `java` (the launcher), and tools like `javadoc` and
`jar`.

The relationship is nested:

    JDK  ⊃  JRE  ⊃  JVM

In this project I used JDK 17.0.9, because I needed `javac` to compile
my code.

## What is bytecode?

Bytecode is the intermediate, platform-independent code produced when
`javac` compiles a `.java` file. It is stored in a `.class` file. It is
not human-readable source code, and it is not machine code for a
specific CPU either. It is instructions for the JVM.

For example, compiling `Main.java` produces `Main.class`, and the JVM
then runs that file.

    Main.java  --javac-->  Main.class (bytecode)  --JVM-->  runs on the machine

## What does "write once, run anywhere" mean?

Java source code is compiled once into bytecode, not into machine code
for one particular computer. That same `.class` file can be copied to
Windows, macOS or Linux without changing or recompiling the code.

It works because each platform has its own JVM that understands the same
bytecode. The JVM handles the differences between operating systems, so
the programmer does not have to. I compiled LearnTrack on Windows, and
the same bytecode would run on any machine with a compatible JVM.