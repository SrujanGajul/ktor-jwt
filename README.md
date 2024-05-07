# User Management using JWT Authentication

This is a Kotlin-based web application that uses Ktor for server-side development. The application is designed with a focus on security and user management.

## Technologies Used

- Kotlin: The primary programming language used in this project.
- Java: Used in conjunction with Kotlin.
- Gradle: The build tool used for managing dependencies and building the project.
- Ktor: A framework used for building asynchronous servers and clients in connected systems.
- JWT (JSON Web Tokens): Used for securely transmitting information between parties as a JSON object.

## Features

- User Authentication: The application uses JWT for user authentication.
- User Management: The application provides endpoints for creating and retrieving users.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 17 or later
- Gradle

### Installation

1. Clone the repository
2. Navigate to the project directory
3. Run `gradle build` to build the project
4. Run `gradle run` to start the server

## Usage

### User Endpoints

- `POST /`: Create a new user. The request body should include `username` and `password`.
- `GET /`: Retrieve all users. This endpoint requires authentication.
- `GET /{id}`: Retrieve a user by ID. This endpoint requires authentication.
