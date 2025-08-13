# Spring Boot Annotations - Project Index

This workspace contains multiple Spring Boot projects demonstrating various annotations and concepts. Each project focuses on specific aspects of Spring Boot development.

## 📁 Project Organization

### 🎯 **Basic Annotation Demonstrations**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `Annotationdemo/` | Basic component registration | `@Component` |
| `Annotationdemo - @AutoWired/` | Dependency injection patterns | `@Autowired`, `@Component` |
| `Annotationdemo - @Primary/` | Bean precedence | `@Primary`, `@Component` |
| `Annotationdemo - @Qualifier/` | Specific bean selection | `@Qualifier`, `@Autowired` |
| `Annotationdemo - @Bean & @Configuration/` | Java configuration | `@Bean`, `@Configuration` |
| `Annotationdemo - @Controller - @Service - @Repository/` | Stereotype annotations | `@Controller`, `@Service`, `@Repository` |

### ⚙️ **Configuration Projects**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `BootProject4-ValueAnnotation/` | Property injection | `@Value` |
| `BootProject5-ConfigurationProperties/` | Bulk property binding | `@ConfigurationProperties` |
| `BootProject6-DifferentPropertiesCalue-into-different-models/` | Multi-model configuration | `@ConfigurationProperties` |

### 🔄 **Dependency Injection Projects**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `BootProj1-DependencyInjection/` | Basic DI patterns | `@Autowired`, `@Component` |
| `BootProj2-DependencyInjection/` | Advanced DI scenarios | `@Autowired`, `@Service` |
| `BootProject3-DependencyInjection/` | Complex DI examples | `@Repository`, `@Service` |

### 🌍 **Environment & Profiles**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `springprofiles/` | Environment-specific configuration | `@Profile`, `@Service` |
| `BootProj-08-Multi-Profiles-DB-Project/` | Multi-environment database setup | `@Profile`, `@Repository` |
| `BootProj-09-RunnerImplementation/` | Application startup runners | `@Component` |

### 💾 **Data & Persistence Projects**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `BootProj-10_simpleSpringData/` | Basic Spring Data JPA | `@Entity`, `@Repository`, `@Service` |
| `SpringData/demo1/` | JPA fundamentals | `@Entity`, `@Repository` |
| `SpringData/demo2/` | Advanced JPA relationships | `@OneToMany`, `@ManyToOne` |
| `SpringData/SimpleMongoProj/` | MongoDB integration | `@Document`, `@Repository` |

### 🌐 **Web Applications**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `01-SpringMVC/` | Spring MVC fundamentals | `@Controller`, `@RequestMapping` |
| `WebProj-1_simpleWebApplication/` | Basic web application | `@Controller` |
| `React+SpringBoot/` | Full-stack integration | `@RestController`, `@CrossOrigin` |

### 🏥 **Real-World Applications**
| Project | Focus | Key Annotations |
|---------|-------|----------------|
| `PatientReport/` | Healthcare domain application | `@Service`, `@Repository`, `@Controller` |
| `CustomerReport-RootApp/` | Customer management system | `@Service`, `@Repository`, `@Entity` |
| `TiketBookingSystem/` | Booking system implementation | `@Service`, `@Component` |
| `user_profile_management_system/` | User management with security | `@RestController`, `@Service`, `@Repository` |

## 🎓 **Learning Path Recommendations**

### **Beginner Path:**
1. Start with `Annotationdemo/` - Learn basic `@Component`
2. Move to `Annotationdemo - @AutoWired/` - Understand dependency injection
3. Try `BootProject4-ValueAnnotation/` - Learn property injection
4. Explore `BootProj-10_simpleSpringData/` - Basic data operations

### **Intermediate Path:**
1. `Annotationdemo - @Primary/` and `@Qualifier/` - Bean selection strategies
2. `BootProject5-ConfigurationProperties/` - Configuration management
3. `springprofiles/` - Environment-specific setups
4. `SpringData/demo1/` - JPA relationships

### **Advanced Path:**
1. `PatientReport/` - Complete layered application
2. `SpringData/demo2/` - Complex entity relationships
3. `React+SpringBoot/` - Full-stack development
4. `user_profile_management_system/` - Security integration

## 📚 **Documentation**
- `LEARNING_ROADMAP.md` - **🗺️ Step-by-step learning guide with weekly structure**
- `SPRING_ANNOTATIONS_GUIDE.md` - **📖 Complete educational guide to all Spring Boot annotations**
- `ANNOTATIONS_QUICK_REFERENCE.md` - **⚡ Quick lookup reference for common annotations**
- `readme.md` - Comprehensive Spring Boot Data guide (14,000+ lines)
- `intro to Spring Data.pdf` - Additional learning material
- Each project contains its own README with specific instructions

## 🚀 **Quick Start**
1. Choose a project from the index above
2. Navigate to the project directory
3. Check the project's README for specific instructions
4. Run using `./mvnw spring-boot:run` or `./gradlew bootRun`

## 📝 **Notes**
- All projects are independent and can be run separately
- Each project demonstrates specific Spring Boot concepts
- Projects build upon each other in complexity
- Real-world applications combine multiple annotation patterns
