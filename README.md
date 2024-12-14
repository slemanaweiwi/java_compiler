Overview
The s-Java Compiler is a custom-built compiler for a simplified version of the Java programming language. The project focuses on parsing, syntax validation, and error handling, leveraging object-oriented design principles to ensure a modular and maintainable codebase.

Features
Error Handling:
Robust error detection using try and catch mechanisms, with custom exception classes for each type of error. This provides clear and specific feedback to users, making debugging straightforward.

Syntax Validation:

Syntax rules are checked using the CheckValidSyntax class, which applies custom-built regular expressions (Regex).
Variables, methods, and their properties are validated and stored using dedicated classes like VariableDetails.
Scope Management:
The compiler dynamically updates and manages data based on the current scope, ensuring proper handling of nested blocks and logical structures.

Object-Oriented Design
The project is designed around the following key classes:

Sjavac: The main class that handles the core logic, including scope transitions, error detection, and high-level functionality.
CheckValidSyntax: Responsible for validating syntax rules and ensuring compliance with the language specifications.
VariableDetails: Manages metadata about variables and methods, ensuring accurate type and scope handling.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher installed.

