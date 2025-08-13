# Project Setup Guide

This guide explains how to set up projects that require additional dependencies or have build artifacts that were excluded during the repository organization.

## 🚀 Quick Setup for All Projects

### Prerequisites
- Java 21+ installed
- Maven 3.6+ or Gradle 7+
- Node.js 18+ (for React projects)
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### General Steps
1. Navigate to the project directory
2. Install dependencies
3. Build the project
4. Run the application

---

## 📂 Category-Specific Setup Instructions

### 🔧 01-Basic-Annotations Projects

All projects in this category are ready to run with Maven:

```bash
cd 01-basic-annotations/[project-name]
./mvnw clean install
./mvnw spring-boot:run
```

**Example:**
```bash
cd 01-basic-annotations/Annotationdemo
./mvnw spring-boot:run
```

---

### ⚙️ 02-Configuration-Projects

#### Maven Projects
```bash
cd 02-configuration-projects/[project-name]
./mvnw clean install
./mvnw spring-boot:run
```

#### Gradle Projects (BootProject4-ValueAnnotation)
```bash
cd 02-configuration-projects/BootProject4-ValueAnnotation
./gradlew clean build
./gradlew bootRun
```

---

### 🔗 03-Dependency-Injection Projects

All projects use Maven:
```bash
cd 03-dependency-injection/[project-name]
./mvnw clean install
./mvnw spring-boot:run
```

---

### 💾 04-Data-Persistence Projects

#### Standard Spring Data Projects
```bash
cd 04-data-persistence/[project-name]
./mvnw clean install
./mvnw spring-boot:run
```

#### SpringData Complex Projects
Some SpringData projects may have nested structures. Navigate to the specific sub-project:

```bash
# Example for SpringData/demo
cd 04-data-persistence/SpringData/demo
./mvnw clean install
./mvnw spring-boot:run

# Example for SpringData/demo2 (Blog application)
cd 04-data-persistence/SpringData/demo2
./mvnw clean install
./mvnw spring-boot:run
```

**Note:** H2 database will be created automatically. Check `application.properties` for database configuration.

---

### 🌐 05-Web-Applications Projects

#### Simple Spring MVC Projects
```bash
cd 05-web-applications/01-SpringMVC
./mvnw clean install
./mvnw spring-boot:run
```

#### React + Spring Boot Full-Stack Project

This project requires both backend and frontend setup:

**Backend Setup:**
```bash
cd 05-web-applications/React+SpringBoot/simpleFullStackProj_1
./mvnw clean install
./mvnw spring-boot:run
```

**Frontend Setup (React):**
```bash
cd 05-web-applications/React+SpringBoot/simpleFullStackProj_1/newreactproj1

# Install Node.js dependencies
npm install

# Start React development server
npm start
```

**Full-Stack Development:**
1. Start Spring Boot backend (port 8080)
2. Start React frontend (port 3000)
3. Access application at http://localhost:3000

---

### 🏥 06-Real-World-Projects

#### PatientReport (Healthcare System)
```bash
cd 06-real-world-projects/PatientReport
./mvnw clean install
./mvnw spring-boot:run
```

#### TiketBookingSystem (Gradle Project)
```bash
cd 06-real-world-projects/TiketBookingSystem
./gradlew clean build
./gradlew run
```

#### User Profile Management System
```bash
cd 06-real-world-projects/user_profile_management_system
./mvnw clean install
./mvnw spring-boot:run
```

---

### 🎯 07-Advanced-Topics Projects

#### Multi-Profiles Database Project (Gradle)
```bash
cd 07-advanced-topics/BootProj-08\ -Multi-Profiles-DB-Project
./gradlew clean build
./gradlew bootRun
```

#### Runner Implementation (Maven)
```bash
cd 07-advanced-topics/BootProj-09-RunnerImplementation
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🔧 Recreating Missing Dependencies

### For React Projects (node_modules)

If you encounter a React project without `node_modules`:

```bash
cd [react-project-directory]

# Remove existing package-lock.json if corrupted
rm -f package-lock.json

# Install dependencies
npm install

# Or using yarn
yarn install

# Start development server
npm start
```

### For Maven Projects (target/ directory)

```bash
cd [maven-project-directory]

# Clean and rebuild
./mvnw clean
./mvnw compile
./mvnw spring-boot:run
```

### For Gradle Projects (build/ directory)

```bash
cd [gradle-project-directory]

# Clean and rebuild
./gradlew clean
./gradlew build
./gradlew bootRun
```

---

## 🗄️ Database Setup

### H2 Database (Most Projects)
- **Configuration**: Check `application.properties`
- **Console**: Access at `http://localhost:8080/h2-console`
- **Default URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (usually empty)

### MySQL Projects
If a project uses MySQL, update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### MongoDB Projects
For MongoDB projects in SpringData:

```bash
# Start MongoDB service
sudo systemctl start mongod

# Or using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

---

## 🚨 Troubleshooting

### Common Issues and Solutions

#### 1. Maven Wrapper Permission Denied
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

#### 2. Gradle Wrapper Permission Denied
```bash
chmod +x gradlew
./gradlew bootRun
```

#### 3. Java Version Issues
Ensure Java 21+ is installed:
```bash
java -version
# Should show version 21 or higher
```

#### 4. Port Already in Use
```bash
# Kill process on port 8080
sudo lsof -t -i:8080 | xargs kill -9

# Or change port in application.properties
server.port=8081
```

#### 5. React Build Issues
```bash
cd [react-project]
rm -rf node_modules package-lock.json
npm cache clean --force
npm install
```

#### 6. Database Connection Issues
- Check if database service is running
- Verify connection string in `application.properties`
- Ensure database exists (for MySQL/PostgreSQL)

---

## 📱 IDE Setup

### IntelliJ IDEA
1. Open project root directory
2. IDEA will auto-detect Maven/Gradle
3. Wait for dependencies to download
4. Run main application class

### VS Code
1. Install Java Extension Pack
2. Install Spring Boot Extension Pack
3. Open project folder
4. Use `Ctrl+Shift+P` → "Java: Build Projects"

### Eclipse
1. Import → Existing Maven Projects
2. Select project root
3. Right-click project → Run As → Spring Boot App

---

## 📚 Additional Resources

### Package.json for React Projects

If missing, create `package.json` in React project root:

```json
{
  "name": "spring-boot-react-app",
  "version": "0.1.0",
  "private": true,
  "dependencies": {
    "@testing-library/jest-dom": "^5.16.4",
    "@testing-library/react": "^13.3.0",
    "@testing-library/user-event": "^13.5.0",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-scripts": "5.0.1",
    "web-vitals": "^2.1.4"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
  "eslintConfig": {
    "extends": [
      "react-app",
      "react-app/jest"
    ]
  },
  "browserslist": {
    "production": [
      ">0.2%",
      "not dead",
      "not op_mini all"
    ],
    "development": [
      "last 1 chrome version",
      "last 1 firefox version",
      "last 1 safari version"
    ]
  },
  "proxy": "http://localhost:8080"
}
```

### Maven Dependencies Template

For projects missing dependencies, basic Spring Boot starter:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 🎯 Quick Start Commands Summary

```bash
# Maven projects
./mvnw spring-boot:run

# Gradle projects  
./gradlew bootRun

# React projects
npm install && npm start

# Full-stack projects
# Terminal 1: Backend
./mvnw spring-boot:run
# Terminal 2: Frontend  
cd frontend && npm start
```

## 📞 Need Help?

If you encounter issues with any project setup:

1. Check the project's individual README (if available)
2. Verify all prerequisites are installed
3. Check application logs for specific error messages
4. Refer to Spring Boot documentation: https://spring.io/projects/spring-boot
5. React documentation: https://reactjs.org/docs/getting-started.html

---

**Happy Coding! 🚀**
