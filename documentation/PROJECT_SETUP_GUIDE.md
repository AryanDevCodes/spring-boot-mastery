# 🚀 Complete Project Setup Guide

This guide helps you recreate any missing projects or set up the complete Spring Boot learning environment from scratch.

## 📋 Table of Contents
- [Prerequisites](#prerequisites)
- [Missing Projects Recreation](#missing-projects-recreation)
- [Database Setup](#database-setup)
- [IDE Configuration](#ide-configuration)
- [Common Issues & Solutions](#common-issues--solutions)
- [Project Templates](#project-templates)

## 🔧 Prerequisites

### Required Software
```bash
# Java 21 (recommended)
java -version

# Maven 3.6+
mvn -version

# Git
git --version

# IDE (IntelliJ IDEA, Eclipse, or VS Code)
# MongoDB (for MongoDB projects)
# MySQL (for database projects)
```

### Environment Setup
```bash
# Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk

# Set Maven options
export MAVEN_OPTS="-Xmx1024m"
```

## 🏗️ Missing Projects Recreation

### If SpringData Directory is Incomplete

The SpringData directory contains complex nested projects. If missing, recreate using:

#### 1. Simple MongoDB Project
```bash
# Create new Spring Boot project with MongoDB
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-mongodb \
  -d groupId=com.example \
  -d artifactId=mongo-demo \
  -d name=mongo-demo \
  -d packageName=com.example.mongodemo \
  -d javaVersion=21 \
  -o mongo-demo.zip

unzip mongo-demo.zip
```

#### 2. JPA Demo Project
```bash
# Create JPA project
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,mysql \
  -d groupId=com.example \
  -d artifactId=jpa-demo \
  -d name=jpa-demo \
  -d packageName=com.example.jpademo \
  -d javaVersion=21 \
  -o jpa-demo.zip

unzip jpa-demo.zip
```

#### 3. Blog System Project (Complex Relationships)
```bash
# Create advanced JPA project
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,mysql,validation,thymeleaf \
  -d groupId=com.example \
  -d artifactId=blog-system \
  -d name=blog-system \
  -d packageName=com.example.blogsystem \
  -d javaVersion=21 \
  -o blog-system.zip

unzip blog-system.zip
```

### React + Spring Boot Integration

If React integration is missing, recreate:

```bash
# Backend Spring Boot
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2 \
  -d groupId=com.example \
  -d artifactId=fullstack-backend \
  -d name=fullstack-backend \
  -d packageName=com.example.fullstack \
  -d javaVersion=21 \
  -o fullstack-backend.zip

# Frontend React
npx create-react-app fullstack-frontend
cd fullstack-frontend
npm install axios
```

## 🗄️ Database Setup

### H2 Database (In-Memory)
```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

### MySQL Database
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
```

### MongoDB Setup
```properties
# application.properties
spring.data.mongodb.uri=mongodb://localhost:27017/springboot_mongo
```

```bash
# Start MongoDB (Ubuntu/Debian)
sudo systemctl start mongod

# Start MongoDB (macOS with Homebrew)
brew services start mongodb/brew/mongodb-community

# Docker MongoDB
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

## 🔧 IDE Configuration

### IntelliJ IDEA Setup
1. **Import Project**: File → Open → Select project folder
2. **SDK Configuration**: File → Project Structure → Project → SDK: Java 21
3. **Maven Configuration**: 
   - Enable auto-import
   - Set Maven home to bundled version
4. **Annotations Processing**: Enable for Lombok support

### VS Code Setup
```json
// .vscode/settings.json
{
    "java.home": "/usr/lib/jvm/java-21-openjdk",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-21",
            "path": "/usr/lib/jvm/java-21-openjdk"
        }
    ],
    "spring-boot.ls.java.home": "/usr/lib/jvm/java-21-openjdk"
}
```

Required Extensions:
- Extension Pack for Java
- Spring Boot Extension Pack
- REST Client

## 🚨 Common Issues & Solutions

### 1. Maven Wrapper Issues
```bash
# If mvnw is not executable
chmod +x mvnw

# If Maven wrapper is missing
mvn wrapper:wrapper
```

### 2. Port Conflicts
```properties
# Change default port in application.properties
server.port=8081
```

### 3. Database Connection Issues
```bash
# Check if MySQL is running
sudo systemctl status mysql

# Check if MongoDB is running
sudo systemctl status mongod

# Reset H2 database
# Delete target/h2db.mv.db file and restart application
```

### 4. Annotation Processing
```xml
<-Djavax.net.debug=all Add to pom.xml if missing -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

## �� Project Templates

### Basic Spring Boot Template
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### Entity Template
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    // Constructors, getters, setters
}
```

### Repository Template
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
    
    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersOlderThan(@Param("age") int age);
}
```

### Service Template
```java
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
```

### Controller Template
```java
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }
}
```

## 🔄 Project Recreation Scripts

### Create Basic Annotation Project
```bash
#!/bin/bash
# create-annotation-project.sh

PROJECT_NAME=$1
GROUP_ID="org.annotations"
ARTIFACT_ID=$PROJECT_NAME

curl https://start.spring.io/starter.zip \
  -d dependencies=web \
  -d groupId=$GROUP_ID \
  -d artifactId=$ARTIFACT_ID \
  -d name=$PROJECT_NAME \
  -d packageName=$GROUP_ID.$ARTIFACT_ID \
  -d javaVersion=21 \
  -o $PROJECT_NAME.zip

unzip $PROJECT_NAME.zip
rm $PROJECT_NAME.zip

echo "✅ Created $PROJECT_NAME successfully!"
echo "📁 Navigate to: cd $PROJECT_NAME"
echo "🚀 Run with: ./mvnw spring-boot:run"
```

### Create Data Project
```bash
#!/bin/bash
# create-data-project.sh

PROJECT_NAME=$1
GROUP_ID="org.practice"
ARTIFACT_ID=$PROJECT_NAME

curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,mysql,validation \
  -d groupId=$GROUP_ID \
  -d artifactId=$ARTIFACT_ID \
  -d name=$PROJECT_NAME \
  -d packageName=$GROUP_ID.$ARTIFACT_ID \
  -d javaVersion=21 \
  -o $PROJECT_NAME.zip

unzip $PROJECT_NAME.zip
rm $PROJECT_NAME.zip

echo "✅ Created $PROJECT_NAME with JPA support!"
```

## 📚 Additional Resources

### Dependencies Reference
```xml
<-Djavax.net.debug=all Common Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Testing Dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
```

## 🆘 Getting Help

If you encounter issues:

1. **Check Project Documentation**: Each project has its own README
2. **Consult Learning Roadmap**: `documentation/LEARNING_ROADMAP.md`
3. **Reference Guide**: `documentation/SPRING_ANNOTATIONS_GUIDE.md`
4. **Quick Reference**: `documentation/ANNOTATIONS_QUICK_REFERENCE.md`

---

**Remember**: This guide ensures you can recreate any missing or corrupted projects with full functionality! 🚀
