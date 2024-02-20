[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0Z8K5T43)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=13951021)
# UWW COMPSCI 424 Program 1 in Java
 
## Your notes for me

*If you have notes or messages for me, please put them here so I can see them.*

## About this repository and autograding

Use this template for Operating Systems Program 1 in Java.

To compile and run correctly:
* **Your main class must be named `Program1`** because GitHub will use the run command `java Program1` to run your code. Other starter code files are included in this repository, but you can rename, modify, or combine those files as needed. 
* Your Java source code files (`.java` files) must be stored in the `app/src/main/java/compsci424/p1/java` directory within your project environment. The Gradle build system will compile all `.java` files in this directory when you push your changes to GitHub Classroom. (On Windows, use backslashes '\\' instead of forward slashes '/'.)

This template uses the Gradle build system to build and run your Java code. All of the major Java IDEs (Eclipse, IntelliJ, and NetBeans) support Gradle, and so does Visual Studio Code. You might need to install a Gradle plugin or change a "Build System" setting in your project settings to enable Gradle. Try it and see what happens.

The GitHub Classroom autograder will compile and run your code on a Linux virtual machine running a JDK that supports Java 17. You may use any standard Java class or feature that is supported in Java 17.

If you are encountering problems getting your code to pass tests when you push to GitHub Classroom, please [email me](osterz@uww.edu) with a description of what's happening. I will look at your repository on GitHub to try to help you debug.

## Advice for Java programmers

This advice is also posted on the Program 1 page on Canvas. Updates, if any, will be posted there.

* Standard library methods [`System.currentTimeMillis()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#currentTimeMillis()) or [`System.nanoTime()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#nanoTime()) will help you compute running time. Store returned values from these methods into `long` variables, not `int`s. You will lose information if you use `int`s.

* You may use the `LinkedList` class from the Java standard library to implement your linked lists of children for Version 1. Please don't use an `ArrayList` for this, since `ArrayLists` are implemented differently and perform differently from `LinkedLists`. For information on the `LinkedList` class, go to the [Java 17 API documentation site](https://docs.oracle.com/en/java/javase/17/docs/api/index.html) and search for "LinkedList".
