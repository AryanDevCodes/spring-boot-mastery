#!/bin/bash

# 🚀 Spring Boot Project Creator Script
# Usage: ./create-missing-project.sh [type] [name] [optional-location]

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Function to show usage
show_usage() {
    echo "🚀 Spring Boot Project Creator"
    echo "Usage: $0 [type] [name] [optional-location]"
    echo ""
    echo "Types:"
    echo "  annotation     - Basic annotation learning project"
    echo "  data          - JPA/Database project with H2 and MySQL"
    echo "  web           - Web application with Thymeleaf"
    echo "  mongodb       - MongoDB integration project"
    echo "  fullstack     - React + Spring Boot full-stack"
    echo "  security      - Spring Security project"
    echo "  advanced      - Advanced project with multiple features"
    echo ""
    echo "Examples:"
    echo "  $0 annotation my-annotation-demo"
    echo "  $0 data customer-management 04-data-persistence/"
    echo "  $0 fullstack task-manager 05-web-applications/"
}

# Check if curl is available
check_requirements() {
    if ! command -v curl &> /dev/null; then
        print_error "curl is required but not installed. Please install curl first."
        exit 1
    fi
    
    if ! command -v unzip &> /dev/null; then
        print_error "unzip is required but not installed. Please install unzip first."
        exit 1
    fi
}

# Function to create project based on type
create_project() {
    local type=$1
    local name=$2
    local location=${3:-"."}
    
    local deps=""
    local group_id=""
    local description=""
    
    case $type in
        "annotation")
            deps="web"
            group_id="org.annotations"
            description="Basic Spring Boot annotation learning project"
            ;;
        "data")
            deps="web,data-jpa,h2,mysql,validation"
            group_id="org.practice"
            description="Spring Boot JPA data persistence project"
            ;;
        "web")
            deps="web,thymeleaf,validation"
            group_id="org.practice"
            description="Spring Boot web application with Thymeleaf"
            ;;
        "mongodb")
            deps="web,data-mongodb,validation"
            group_id="org.practice"
            description="Spring Boot MongoDB integration project"
            ;;
        "fullstack")
            deps="web,data-jpa,h2"
            group_id="org.practice"
            description="Full-stack React + Spring Boot project"
            ;;
        "security")
            deps="web,security,data-jpa,h2,thymeleaf"
            group_id="org.practice"
            description="Spring Boot Security demonstration project"
            ;;
        "advanced")
            deps="web,data-jpa,h2,mysql,security,validation,actuator,data-mongodb"
            group_id="org.practice"
            description="Advanced Spring Boot project with multiple features"
            ;;
        *)
            print_error "Unknown project type: $type"
            show_usage
            exit 1
            ;;
    esac
    
    print_info "Creating $type project: $name"
    print_info "Dependencies: $deps"
    print_info "Location: $location"
    
    # Create directory if it doesn't exist
    mkdir -p "$location"
    cd "$location"
    
    # Generate project using Spring Initializr
    print_info "Downloading project from Spring Initializr..."
    
    local zip_file="${name}.zip"
    curl -s https://start.spring.io/starter.zip \
        -d dependencies="$deps" \
        -d groupId="$group_id" \
        -d artifactId="$name" \
        -d name="$name" \
        -d description="$description" \
        -d packageName="$group_id.$(echo $name | tr '-' '')" \
        -d javaVersion=21 \
        -d bootVersion=3.4.4 \
        -o "$zip_file"
    
    if [ $? -eq 0 ]; then
        print_status "Project downloaded successfully"
    else
        print_error "Failed to download project from Spring Initializr"
        exit 1
    fi
    
    # Extract project
    print_info "Extracting project..."
    unzip -q "$zip_file"
    rm "$zip_file"
    
    # Make Maven wrapper executable
    chmod +x "$name/mvnw"
    
    print_status "Project $name created successfully!"
    
    # Create additional files based on project type
    case $type in
        "fullstack")
            create_fullstack_structure "$name"
            ;;
        "data")
            create_sample_entities "$name"
            ;;
        "mongodb")
            create_mongodb_config "$name"
            ;;
    esac
    
    # Print next steps
    echo ""
    print_info "Next steps:"
    echo "  📁 cd $location$name"
    echo "  🚀 ./mvnw spring-boot:run"
    echo "  🌐 Open http://localhost:8080"
    echo ""
    
    if [ "$type" = "fullstack" ]; then
        echo "  📱 For React frontend:"
        echo "     cd frontend && npm start"
    fi
}

# Create full-stack structure
create_fullstack_structure() {
    local project_name=$1
    
    print_info "Creating React frontend..."
    
    # Create React app
    if command -v npx &> /dev/null; then
        npx create-react-app "$project_name/frontend" --template typescript
        
        # Add axios for API calls
        cd "$project_name/frontend"
        npm install axios
        
        # Create proxy configuration
        echo '{
  "name": "frontend",
  "proxy": "http://localhost:8080"
}' > package.json.tmp && mv package.json.tmp temp.json
        
        # Merge with existing package.json
        node -e "
        const fs = require('fs');
        const pkg = JSON.parse(fs.readFileSync('package.json'));
        const proxy = JSON.parse(fs.readFileSync('temp.json'));
        pkg.proxy = proxy.proxy;
        fs.writeFileSync('package.json', JSON.stringify(pkg, null, 2));
        " && rm temp.json
        
        cd ../..
        print_status "React frontend created with TypeScript and Axios"
    else
        print_warning "npx not found. Please create React frontend manually."
    fi
}

# Create sample entities for data projects
create_sample_entities() {
    local project_name=$1
    local package_path="$project_name/src/main/java/org/practice/$(echo $project_name | tr '-' '')"
    
    # Create entity directory
    mkdir -p "$package_path/entity"
    mkdir -p "$package_path/repository"
    mkdir -p "$package_path/service"
    mkdir -p "$package_path/controller"
    
    # Create User entity
    cat > "$package_path/entity/User.java" << 'JAVA_EOF'
package org.practice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    
    // Constructors
    public User() {}
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
JAVA_EOF
    
    print_status "Sample User entity created"
}

# Create MongoDB configuration
create_mongodb_config() {
    local project_name=$1
    local resources_path="$project_name/src/main/resources"
    
    # Update application.properties with MongoDB config
    cat >> "$resources_path/application.properties" << 'PROP_EOF'

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/learning_mongodb
spring.data.mongodb.auto-index-creation=true

# Enable MongoDB repositories
spring.data.mongodb.repositories.enabled=true
PROP_EOF
    
    print_status "MongoDB configuration added"
}

# Main script execution
main() {
    echo "🚀 Spring Boot Project Creator"
    echo "================================"
    
    # Check requirements
    check_requirements
    
    # Check arguments
    if [ $# -lt 2 ]; then
        print_error "Insufficient arguments"
        show_usage
        exit 1
    fi
    
    local type=$1
    local name=$2
    local location=$3
    
    # Validate project name
    if [[ ! $name =~ ^[a-zA-Z][a-zA-Z0-9-]*$ ]]; then
        print_error "Invalid project name. Use letters, numbers, and hyphens only."
        exit 1
    fi
    
    # Check if project already exists
    if [ -d "${location:-"."}/$name" ]; then
        print_warning "Project $name already exists in ${location:-"current directory"}"
        read -p "Do you want to overwrite it? (y/N): " -n 1 -r
        echo
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            print_info "Operation cancelled"
            exit 0
        fi
        rm -rf "${location:-"."}/$name"
    fi
    
    # Create the project
    create_project "$type" "$name" "$location"
}

# Run main function with all arguments
main "$@"
