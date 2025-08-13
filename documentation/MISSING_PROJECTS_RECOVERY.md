# 🔧 Missing Projects Recovery Guide

## 🚨 Common Missing or Incomplete Projects

### 1. SpringData Directory Issues

**Problem**: SpringData contains nested projects with complex dependencies that may be incomplete.

**Solution**: Recreate individual projects within SpringData:

#### A. Corona Vaccine Demo (JPA Basic)
```bash
mkdir -p 04-data-persistence/SpringData/corona-vaccine-demo
cd 04-data-persistence/SpringData/corona-vaccine-demo

# Create project
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,mysql \
  -d groupId=com.example \
  -d artifactId=corona-vaccine-demo \
  -d name=corona-vaccine-demo \
  -d packageName=com.example.vaccine \
  -d javaVersion=21 \
  -o corona-vaccine-demo.zip

unzip corona-vaccine-demo.zip && rm corona-vaccine-demo.zip
```

**Add Entity**:
```java
// src/main/java/com/example/vaccine/entity/CoronaVaccine.java
@Entity
@Table(name = "corona_vaccines")
public class CoronaVaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vaccineName;
    private String company;
    private String country;
    private Integer price;
    private Integer requiredDoseCount;
    
    // Constructors, getters, setters
}
```

#### B. Blog System (Complex Relationships)
```bash
mkdir -p 04-data-persistence/SpringData/blog-system
cd 04-data-persistence/SpringData/blog-system

curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,mysql,validation,thymeleaf \
  -d groupId=com.example \
  -d artifactId=blog-system \
  -d name=blog-system \
  -d packageName=com.example.blog \
  -d javaVersion=21 \
  -o blog-system.zip

unzip blog-system.zip && rm blog-system.zip
```

**Add Entities**:
```java
// User.java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}

// Post.java
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "post_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}

// Comment.java
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

// Tag.java
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();
}
```

#### C. MongoDB Task Manager
```bash
mkdir -p 04-data-persistence/SpringData/mongodb-tasks
cd 04-data-persistence/SpringData/mongodb-tasks

curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-mongodb \
  -d groupId=com.example \
  -d artifactId=mongodb-tasks \
  -d name=mongodb-tasks \
  -d packageName=com.example.tasks \
  -d javaVersion=21 \
  -o mongodb-tasks.zip

unzip mongodb-tasks.zip && rm mongodb-tasks.zip
```

**Add MongoDB Entity**:
```java
// Task.java
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @DBRef
    private User assignedTo;
    
    // Constructors, getters, setters
}
```

### 2. React Integration Missing

**Problem**: React + Spring Boot integration might be incomplete.

**Solution**: Create complete full-stack setup:

```bash
mkdir -p 05-web-applications/React+SpringBoot/complete-fullstack
cd 05-web-applications/React+SpringBoot/complete-fullstack

# Backend
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2 \
  -d groupId=com.example \
  -d artifactId=fullstack-backend \
  -d name=fullstack-backend \
  -d packageName=com.example.fullstack \
  -d javaVersion=21 \
  -o backend.zip

unzip backend.zip -d backend && rm backend.zip

# Frontend
npx create-react-app frontend
cd frontend
npm install axios react-router-dom

# Create proxy for development
echo '{ "proxy": "http://localhost:8080" }' >> package.json
```

**Backend API**:
```java
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {
    
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from Spring Boot!");
    }
    
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

**Frontend Component**:
```jsx
// src/components/UserList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function UserList() {
    const [users, setUsers] = useState([]);
    
    useEffect(() => {
        axios.get('/api/users')
            .then(response => setUsers(response.data))
            .catch(error => console.error('Error:', error));
    }, []);
    
    return (
        <div>
            <h2>Users</h2>
            <ul>
                {users.map(user => (
                    <li key={user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default UserList;
```

### 3. Build Tool Issues

**Problem**: Some projects might have corrupted Maven/Gradle files.

**Solutions**:

#### Maven Wrapper Recreation
```bash
# Navigate to project directory
cd problematic-project

# Remove corrupted wrapper
rm -rf .mvn mvnw mvnw.cmd

# Regenerate Maven wrapper
mvn wrapper:wrapper

# Make executable
chmod +x mvnw
```

#### Gradle Wrapper Recreation
```bash
# Navigate to project directory
cd problematic-project

# Remove corrupted wrapper
rm -rf gradle gradlew gradlew.bat

# Regenerate Gradle wrapper
gradle wrapper

# Make executable
chmod +x gradlew
```

### 4. Database Connection Issues

**Problem**: Projects might have incorrect database configurations.

**Solutions**:

#### H2 Database (Recommended for Learning)
```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

#### MySQL Database
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/learning_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
```

#### MongoDB Configuration
```properties
# application.properties
spring.data.mongodb.uri=mongodb://localhost:27017/learning_mongodb
spring.data.mongodb.auto-index-creation=true
```

### 5. Missing Dependencies

**Problem**: Some projects might have missing or incorrect dependencies.

**Solution**: Standard dependency template for different project types:

#### Basic Web Project
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### JPA Project
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
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

#### MongoDB Project
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
</dependencies>
```

## 🚀 Quick Recovery Scripts

### Complete Project Recreation Script
```bash
#!/bin/bash
# recover-project.sh

PROJECT_TYPE=$1  # annotation, data, web, mongodb
PROJECT_NAME=$2

case $PROJECT_TYPE in
  "annotation")
    DEPS="web"
    GROUP_ID="org.annotations"
    ;;
  "data")
    DEPS="web,data-jpa,h2,mysql,validation"
    GROUP_ID="org.practice"
    ;;
  "web")
    DEPS="web,thymeleaf"
    GROUP_ID="org.practice"
    ;;
  "mongodb")
    DEPS="web,data-mongodb"
    GROUP_ID="org.practice"
    ;;
  *)
    echo "Usage: $0 {annotation|data|web|mongodb} project-name"
    exit 1
    ;;
esac

curl https://start.spring.io/starter.zip \
  -d dependencies=$DEPS \
  -d groupId=$GROUP_ID \
  -d artifactId=$PROJECT_NAME \
  -d name=$PROJECT_NAME \
  -d packageName=$GROUP_ID.$PROJECT_NAME \
  -d javaVersion=21 \
  -o $PROJECT_NAME.zip

unzip $PROJECT_NAME.zip
rm $PROJECT_NAME.zip

echo "✅ Created $PROJECT_NAME ($PROJECT_TYPE type)"
echo "📁 Location: $(pwd)/$PROJECT_NAME"
```

### Usage Examples
```bash
# Create annotation project
./recover-project.sh annotation my-annotation-demo

# Create data project with JPA
./recover-project.sh data my-data-demo

# Create web project
./recover-project.sh web my-web-demo

# Create MongoDB project
./recover-project.sh mongodb my-mongo-demo
```

## 🔧 Manual Recovery Steps

If automated scripts don't work:

1. **Backup existing work**: Copy any custom code you've written
2. **Delete corrupted project**: Remove the problematic directory
3. **Create fresh project**: Use Spring Initializr (start.spring.io)
4. **Restore custom code**: Copy back your custom implementations
5. **Test the project**: Run `./mvnw spring-boot:run` to verify

## 📞 Emergency Contacts

If you're still stuck:
- Check Stack Overflow for specific error messages
- Consult Spring Boot documentation: https://spring.io/projects/spring-boot
- Review the working projects in this repository for reference

---

**This guide ensures you can recover from any project corruption or missing files'/media/ashish/AryanRaj/Education/JAVAW/Spring&Boot/spring-boot-mastery-complete/documentation' && cat >> PROJECT_SETUP_GUIDE.md << 'EOF'

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
EOF* 🛠️✨
