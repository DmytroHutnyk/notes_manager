# Notes Manager Web Application

## Description
Notes Manager is a web application designed for efficient note management. Users can create notes, assign statuses, 
and set completion dates.

The system includes two predefined user roles with specific permissions:

#### Roles description:
- **User:**  Can create, edit, delete notes, and update their statuses.
- **Admin:** Has full access, including an Admin Panel for managing notes and registered users.

New users can register with the User role by default.

The application is built using the Spring Framework, with Spring Boot for backend development and
Spring Data JPA for database interaction.
Thymeleaf is used for server-side rendering, and data is stored in an H2 in-file database.


## Default Test Accounts
The application includes predefined user accounts for testing:

| Role          | Username | Password    |
|---------------|----------|-------------|
| **User**      | `user1`  | `1234`      |
| **Admin**     | `admin`  | `qwerty`    |


## Technologies Used
- **Java 23**
- **Spring Boot**
- **Spring Web (Spring MVC)**
- **Spring Data JPA**
- **Hibernate**
- **Spring Security**
- **Gradle**
- **H2 Database**
- **Lombok**

## Prerequisites
- **Java Development Kit (JDK)** 23.
- **Gradle** for building and running the application.
- **GIT** for cloning the repository.
- A terminal or IDE (e.g., IntelliJ IDEA).


## Steps to Start the Application

### 1. Clone the Repository
Open a terminal and run:

```bash
git clone https://github.com/yourusername/your-repository.git
```
Replace yourusername and your-repository with your actual GitHub username and repository name.

### 2. Navigate to the Project Directory
Open a terminal and navigate to the root directory of your project (where the `build.gradle` file is located):

```bash
cd /path/to/your/project
```

### 3. Build the Project
Use the Gradle wrapper to build the application:

- **macOS/Linux & Windows(PowerShell):**
  ```bash
  ./gradlew build
  ```
- **Windows (CMD only):**
  ```bash
  gradlew build
  ```

This command will:
- Compile the source code.
- Resolve dependencies.
- Package the application into an executable JAR file located in the `build/libs` directory.

Verify that the JAR file is created:

- **macOS/Linux & Windows(PowerShell):**
  ```bash
  ls build/libs
  ```
- **Windows (CMD only):**
  ```bash
  dir build\libs
  ```

You should see a file like this:

```
notes_app-0.0.1-SNAPSHOT.jar
```

### 4. Run the Application
Start the application using the `java -jar` command:
 ```bash
  java -jar build/libs/notes_app-0.0.1-SNAPSHOT.jar
  ```
### 5. Known issues (work in progress)
The notes list does not update automatically due to Thymeleaf’s static rendering. 
Users need to manually refresh the page to see the latest changes.

When attempting to access the admin page without the Admin role, an error is displayed instead of an appropriate message.