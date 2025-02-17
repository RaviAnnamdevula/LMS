# Library Management System (LMS)

## Overview
This Library Management System (LMS) is a **Spring Boot** application designed to manage books, students, and transactions efficiently. The system allows users to perform CRUD operations on books, authors, and students while handling transactions related to book borrowings and returns.

## Features
- **Book Management**: Add, update, delete, and fetch book details.
- **Student Management**: Manage student information, including registration and retrieval.
- **Transaction Handling**: Process book borrowals and returns while ensuring business logic constraints.
- **Exception Handling**: Custom exceptions and centralized error handling for better system stability.

## Technologies Used
- **Java 17**
- **Spring Boot** (REST API development)
- **Spring Data JPA** (Database interaction)
- **H2/MySQL** (Database management)
- **Maven** (Project build and dependency management)

## Project Structure
```
minor1/
│── src/
│   ├── main/
│   │   ├── java/com/project/minor1/
│   │   │   ├── controller/ (Handles API requests)
│   │   │   ├── model/ (Contains entity classes)
│   │   │   ├── exception/ (Custom exception handling)
│   │   │   ├── service/ (Business logic implementation)
│   │   │   ├── repository/ (Data access layer)
│   ├── resources/
│   │   ├── application.properties (Configuration settings)
│── pom.xml (Maven build file)
│── README.md (Project documentation)
```

## Installation and Setup

### Prerequisites
Ensure you have the following installed:
- **Java 17**
- **Maven**
- **MySQL or H2 Database**

### Steps to Run the Project
1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd minor1
   ```
2. **Configure the Database**
   - Update `application.properties` with database credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. **Build the Project**
   ```sh
   mvn clean install
   ```
4. **Run the Application**
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints

### Book Management
- `GET /books` - Fetch all books
- `POST /books` - Add a new book
- `PUT /books/{id}` - Update book details
- `DELETE /books/{id}` - Remove a book

### Student Management
- `GET /students` - Fetch all students
- `POST /students` - Register a student

### Transactions
- `POST /transactions/borrow` - Borrow a book
- `POST /transactions/return` - Return a book

## Contribution Guidelines
1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Commit changes with clear messages.
4. Push to the branch and create a pull request.



