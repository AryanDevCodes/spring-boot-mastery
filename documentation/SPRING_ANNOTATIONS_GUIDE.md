# Spring Boot Annotations - Complete Educational Guide

## 📚 Table of Contents

1. [Introduction to Spring Annotations](#introduction-to-spring-annotations)
2. [Core Spring Annotations](#core-spring-annotations)
3. [Dependency Injection Annotations](#dependency-injection-annotations)
4. [Configuration Annotations](#configuration-annotations)
5. [Web Layer Annotations](#web-layer-annotations)
6. [Data Layer Annotations](#data-layer-annotations)
7. [Testing Annotations](#testing-annotations)
8. [Advanced Annotations](#advanced-annotations)
9. [Best Practices](#best-practices)
10. [Common Pitfalls](#common-pitfalls)
11. [Real-World Examples](#real-world-examples)

---

## Introduction to Spring Annotations

### What are Annotations?
Annotations in Spring Boot are metadata that provide information about your code to the Spring framework. They tell Spring how to manage your classes, inject dependencies, configure components, and much more.

Think of annotations as **instructions** or **labels** that you attach to your code to tell Spring:
- "This class is a component, please manage it"
- "Inject this dependency here"
- "This method handles web requests"
- "Map this class to a database table"

### Why Use Annotations?
- **Declarative Programming**: Say what you want, not how to do it
- **Reduced Boilerplate**: Less XML configuration, more readable code
- **Convention over Configuration**: Smart defaults reduce setup time
- **Type Safety**: Compile-time checking for many configurations

---

## Core Spring Annotations

### 1. `@Component`
**Purpose**: Marks a class as a Spring-managed component (bean).

**How it works**: Spring scans for classes with `@Component` and creates instances (beans) of them in the Application Context.

```java
@Component
public class GameService {
    public String getServiceName() {
        return "Game Service";
    }
}
```

**Key Points**:
- Generic stereotype annotation
- Spring creates a singleton instance by default
- Bean name defaults to class name with lowercase first letter
- Can specify custom name: `@Component("customName")`

**When to use**: For general-purpose Spring beans that don't fit other stereotypes.

---

### 2. `@Service`
**Purpose**: Specialized `@Component` for service layer classes.

**How it works**: Same as `@Component` but semantically indicates this class contains business logic.

```java
@Service
public class CustomerService {
    private final CustomerRepository repository;
    
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    
    public Customer processCustomer(Customer customer) {
        // Business logic here
        return repository.save(customer);
    }
}
```

**Key Points**:
- Semantic annotation for business layer
- Makes code more readable and maintainable
- Spring may add service-specific features in the future
- Often used with `@Transactional`

**When to use**: For classes that contain business logic and orchestrate other components.

---

### 3. `@Repository`
**Purpose**: Specialized `@Component` for data access layer classes.

**How it works**: Marks data access classes and enables automatic exception translation.

```java
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Customer findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), id);
    }
}
```

**Key Points**:
- Automatic exception translation (SQLException → DataAccessException)
- Semantic annotation for data layer
- Works with various data access technologies
- Often not needed with Spring Data repositories

**When to use**: For custom data access implementations, especially with JDBC or custom ORM code.

---

### 4. `@Controller`
**Purpose**: Specialized `@Component` for web layer classes that handle HTTP requests.

**How it works**: Marks classes as Spring MVC controllers that can handle web requests.

```java
@Controller
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer-list"; // Returns view name
    }
}
```

**Key Points**:
- Handles web requests and returns view names
- Works with view resolvers (Thymeleaf, JSP, etc.)
- Can return Model and View objects
- Use with `@RequestMapping` family annotations

**When to use**: For traditional MVC applications that return HTML views.

---

### 5. `@RestController`
**Purpose**: Combination of `@Controller` + `@ResponseBody` for REST APIs.

**How it works**: Every method returns data that gets converted to JSON/XML automatically.

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers(); // Automatically converted to JSON
    }
    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
```

**Key Points**:
- Perfect for REST APIs and microservices
- Automatic JSON/XML serialization
- No view resolution needed
- Return objects directly

**When to use**: For REST APIs, AJAX endpoints, and microservices.

---

## Dependency Injection Annotations

### 1. `@Autowired`
**Purpose**: Tells Spring to inject dependencies automatically.

**How it works**: Spring finds beans of the required type and injects them.

#### Field Injection (Not Recommended)
```java
@Service
public class OrderService {
    @Autowired
    private CustomerService customerService; // Injected automatically
}
```

#### Constructor Injection (Recommended)
```java
@Service
public class OrderService {
    private final CustomerService customerService;
    
    @Autowired // Optional in Spring 4.3+
    public OrderService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
```

#### Setter Injection
```java
@Service
public class OrderService {
    private CustomerService customerService;
    
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
```

**Key Points**:
- Constructor injection is preferred (immutable, testable)
- `@Autowired` is optional on constructors with Spring 4.3+
- Field injection is easy but hard to test
- Can mark as `required = false` for optional dependencies

**Best Practice**: Always use constructor injection for mandatory dependencies.

---

### 2. `@Qualifier`
**Purpose**: Specifies which bean to inject when multiple candidates exist.

**How it works**: Uses bean name or qualifier value to resolve ambiguity.

```java
// Multiple implementations
@Service("emailService")
public class EmailNotificationService implements NotificationService {
    // Implementation
}

@Service("smsService")  
public class SmsNotificationService implements NotificationService {
    // Implementation
}

// Using qualifier
@Service
public class OrderService {
    private final NotificationService notificationService;
    
    public OrderService(@Qualifier("emailService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
```

**Key Points**:
- Resolves ambiguity when multiple beans of same type exist
- Can use bean names or custom qualifier values
- Works with `@Autowired` on fields, constructors, or methods
- Alternative to `@Primary`

**When to use**: When you have multiple implementations and need to specify which one to use.

---

### 3. `@Primary`
**Purpose**: Marks a bean as the primary choice when multiple candidates exist.

**How it works**: Spring prefers `@Primary` beans when autowiring by type.

```java
@Service
@Primary
public class EmailNotificationService implements NotificationService {
    // This will be preferred over other implementations
}

@Service
public class SmsNotificationService implements NotificationService {
    // Secondary choice
}

@Service
public class OrderService {
    // Will get EmailNotificationService automatically
    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
```

**Key Points**:
- Simplifies injection when you have a preferred implementation
- Only one `@Primary` bean per type should exist
- Can be overridden with `@Qualifier`
- Cleaner than using `@Qualifier` everywhere

**When to use**: When you have multiple implementations but one is the default choice.

---

## Configuration Annotations

### 1. `@Value`
**Purpose**: Injects values from properties files, environment variables, or expressions.

**How it works**: Spring resolves the value at runtime and injects it.

#### Property Injection
```java
@Component
public class DatabaseConfig {
    
    @Value("${database.url}")
    private String databaseUrl;
    
    @Value("${database.timeout:30}") // Default value: 30
    private int timeout;
    
    @Value("${app.name:MyApp}") // Default: MyApp
    private String applicationName;
}
```

#### Expression Language (SpEL)
```java
@Component
public class CalculationService {
    
    @Value("#{systemProperties['user.home']}")
    private String userHome;
    
    @Value("#{T(java.lang.Math).random() * 100}")
    private double randomNumber;
    
    @Value("#{someBean.someProperty}")
    private String valueFromOtherBean;
}
```

#### application.properties
```properties
database.url=jdbc:mysql://localhost:3306/mydb
database.timeout=45
app.name=SpringBootApp
```

**Key Points**:
- Use `${}` for property values
- Use `#{}` for Spring Expression Language
- Provide default values with `:`
- Can inject into fields, constructor parameters, or method parameters

**When to use**: For external configuration, environment-specific values, and simple property injection.

---

### 2. `@ConfigurationProperties`
**Purpose**: Binds external properties to a Java object in a type-safe way.

**How it works**: Spring maps properties with a common prefix to object fields.

```java
@Component
@ConfigurationProperties(prefix = "app.database")
public class DatabaseProperties {
    
    private String url;
    private String username;
    private String password;
    private int maxConnections;
    private boolean ssl;
    
    // Getters and setters (or use Lombok @Data)
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    // ... other getters and setters
}
```

#### application.yml
```yaml
app:
  database:
    url: jdbc:mysql://localhost:3306/mydb
    username: admin
    password: secret
    max-connections: 20
    ssl: true
```

#### Usage
```java
@Service
public class DatabaseService {
    
    private final DatabaseProperties dbProps;
    
    public DatabaseService(DatabaseProperties dbProps) {
        this.dbProps = dbProps;
    }
    
    public void connect() {
        System.out.println("Connecting to: " + dbProps.getUrl());
        System.out.println("Max connections: " + dbProps.getMaxConnections());
    }
}
```

**Key Points**:
- Type-safe configuration binding
- Supports complex objects and collections
- Validation with Bean Validation annotations
- Better than multiple `@Value` annotations
- IDE autocomplete support

**When to use**: For complex configuration objects, type safety, and validation.

---

### 3. `@Configuration`
**Purpose**: Marks a class as a source of bean definitions.

**How it works**: Spring processes `@Bean` methods in these classes to create beans.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    @Primary
    public NotificationService emailService() {
        return new EmailNotificationService();
    }
}
```

**Key Points**:
- Java-based configuration alternative to XML
- Methods annotated with `@Bean` return objects managed by Spring
- Bean dependencies can be injected as method parameters
- Can use conditions, profiles, and other advanced features

**When to use**: For third-party beans, complex bean creation logic, or when you prefer Java over XML configuration.

---

### 4. `@Bean`
**Purpose**: Marks a method as a bean producer in a `@Configuration` class.

**How it works**: Spring calls the method and manages the returned object as a bean.

```java
@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public ConnectionPool connectionPool() {
        return new ConnectionPool();
    }
    
    @Bean("customBeanName")
    public CustomService customService(PasswordEncoder encoder) {
        return new CustomService(encoder);
    }
}
```

**Key Points**:
- Method name becomes bean name by default
- Can specify custom name with `@Bean("name")`
- Supports lifecycle methods (init/destroy)
- Method parameters are automatically injected
- Can use with conditions and profiles

**When to use**: For beans that require complex initialization or third-party classes you can't annotate.

---

## Web Layer Annotations

### 1. `@RequestMapping`
**Purpose**: Maps HTTP requests to handler methods.

**How it works**: Spring routes incoming requests to methods based on URL patterns and HTTP methods.

```java
@Controller
@RequestMapping("/api/customers")
public class CustomerController {
    
    // Handles GET /api/customers
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    
    // Handles POST /api/customers
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
    
    // Handles GET /api/customers/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
```

**Specialized Variants**:
```java
@GetMapping("/customers")           // GET requests
@PostMapping("/customers")          // POST requests  
@PutMapping("/customers/{id}")      // PUT requests
@DeleteMapping("/customers/{id}")   // DELETE requests
@PatchMapping("/customers/{id}")    // PATCH requests
```

**Key Points**:
- Base annotation for request mapping
- Specialized annotations (`@GetMapping`, etc.) are more concise
- Can specify HTTP methods, content types, headers
- Supports path variables and request parameters

**When to use**: For mapping HTTP requests to controller methods.

---

### 2. `@PathVariable`
**Purpose**: Extracts values from the URL path.

**How it works**: Spring extracts path segments and converts them to method parameters.

```java
@RestController
@RequestMapping("/api")
public class BookController {
    
    // URL: GET /api/books/123
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }
    
    // URL: GET /api/books/123/authors/456
    @GetMapping("/books/{bookId}/authors/{authorId}")
    public Author getBookAuthor(
        @PathVariable Long bookId,
        @PathVariable Long authorId) {
        return bookService.findAuthor(bookId, authorId);
    }
    
    // Custom path variable name
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") Long id) {
        return userService.findById(id);
    }
}
```

**Key Points**:
- Extracts dynamic parts of the URL
- Automatic type conversion (String to Long, etc.)
- Variable name must match path placeholder by default
- Can specify custom name with `@PathVariable("name")`

**When to use**: For RESTful URLs where parts of the path are dynamic values.

---

### 3. `@RequestParam`
**Purpose**: Extracts query parameters from the request.

**How it works**: Spring reads URL query parameters and converts them to method parameters.

```java
@RestController
public class SearchController {
    
    // URL: GET /search?q=spring&page=1&size=10
    @GetMapping("/search")
    public SearchResults search(
        @RequestParam String q,                    // Required
        @RequestParam(defaultValue = "0") int page, // Optional with default
        @RequestParam(defaultValue = "10") int size) {
        
        return searchService.search(q, page, size);
    }
    
    // Optional parameter
    @GetMapping("/products")
    public List<Product> getProducts(
        @RequestParam(required = false) String category) {
        
        if (category != null) {
            return productService.findByCategory(category);
        }
        return productService.findAll();
    }
}
```

**Key Points**:
- Extracts query parameters (?key=value)
- Can be required or optional
- Supports default values
- Automatic type conversion

**When to use**: For optional parameters, search filters, and pagination.

---

### 4. `@RequestBody`
**Purpose**: Converts the HTTP request body to a Java object.

**How it works**: Spring uses message converters (usually Jackson for JSON) to deserialize the request body.

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        // customer object is automatically created from JSON
        return customerService.save(customer);
    }
    
    @PutMapping("/{id}")
    public Customer updateCustomer(
        @PathVariable Long id,
        @RequestBody Customer customer) {
        
        customer.setId(id);
        return customerService.update(customer);
    }
}
```

**Example JSON Request**:
```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30
}
```

**Key Points**:
- Automatic JSON/XML to object conversion
- Works with Jackson (JSON) or JAXB (XML)
- Validates data with Bean Validation annotations
- Single use per method (request body can only be read once)

**When to use**: For REST APIs that accept JSON/XML data.

---

### 5. `@ResponseBody`
**Purpose**: Converts the return value to HTTP response body.

**How it works**: Spring serializes the returned object to JSON/XML.

```java
@Controller
public class DataController {
    
    @GetMapping("/api/data")
    @ResponseBody  // Without this, Spring looks for a view named "data"
    public List<Data> getData() {
        return dataService.findAll(); // Converted to JSON
    }
    
    @PostMapping("/api/data")
    @ResponseBody
    public Data createData(@RequestBody Data data) {
        return dataService.save(data);
    }
}
```

**Note**: `@RestController` = `@Controller` + `@ResponseBody` on every method.

**Key Points**:
- Bypasses view resolution
- Automatic object to JSON/XML conversion
- `@RestController` includes this automatically
- Use with `@Controller` for mixed endpoints

**When to use**: With `@Controller` when some methods return views and others return data.

---

## Data Layer Annotations

### 1. `@Entity`
**Purpose**: Marks a class as a JPA entity (database table).

**How it works**: JPA providers (Hibernate) map the class to a database table.

```java
@Entity
@Table(name = "customers") // Optional: specify table name
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    // Constructors, getters, setters
}
```

**Key Points**:
- Each entity represents a database table
- Requires an `@Id` field
- Column names default to field names (snake_case conversion)
- Supports relationships with other entities

**When to use**: For classes that represent database tables in JPA applications.

---

### 2. `@Id` and `@GeneratedValue`
**Purpose**: Marks the primary key field and specifies how it's generated.

```java
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;
    
    // Other fields...
}

// Alternative strategies
@GeneratedValue(strategy = GenerationType.AUTO)      // Let JPA decide
@GeneratedValue(strategy = GenerationType.SEQUENCE)  // Use database sequence
@GeneratedValue(strategy = GenerationType.TABLE)     // Use table for IDs
@GeneratedValue(strategy = GenerationType.UUID)      // Generate UUIDs
```

**Key Points**:
- Every entity needs exactly one `@Id` field
- `GenerationType.IDENTITY` works with auto-increment columns
- `GenerationType.AUTO` lets JPA choose the best strategy
- Can use composite keys with `@EmbeddedId` or `@IdClass`

---

### 3. `@Column`
**Purpose**: Specifies column mappings and constraints.

```java
@Entity
public class User {
    
    @Column(name = "user_name", nullable = false, length = 50, unique = true)
    private String username;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(precision = 10, scale = 2) // For BigDecimal
    private BigDecimal salary;
    
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
```

**Key Points**:
- Optional for simple mappings
- Controls column name, constraints, and SQL types
- `nullable = false` creates NOT NULL constraint
- `unique = true` creates unique constraint

---

### 4. Relationship Annotations

#### `@OneToMany`
```java
@Entity
public class Customer {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}

@Entity
public class Order {
    @Id
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

#### `@ManyToMany`
```java
@Entity
public class Student {
    @Id
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
```

**Key Points**:
- `mappedBy` indicates the owning side
- `cascade` controls related entity operations
- `fetch` controls loading strategy (LAZY vs EAGER)
- Use `@JoinColumn` and `@JoinTable` for custom mapping

---

### 5. `@Transactional`
**Purpose**: Manages database transactions declaratively.

**How it works**: Spring creates a transaction around the method execution.

```java
@Service
@Transactional(readOnly = true) // Default for all methods
public class CustomerService {
    
    @Transactional // Read-write transaction
    public Customer createCustomer(Customer customer) {
        // If any exception occurs, transaction rolls back
        Customer saved = customerRepository.save(customer);
        emailService.sendWelcomeEmail(saved.getEmail());
        return saved;
    }
    
    @Transactional(
        propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.SERIALIZABLE,
        timeout = 30,
        rollbackFor = Exception.class
    )
    public void complexOperation() {
        // Advanced transaction configuration
    }
    
    // This method uses readOnly=true from class level
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
```

**Key Points**:
- Automatic rollback on runtime exceptions
- Can specify rollback conditions
- Supports different propagation levels
- Read-only transactions can optimize performance
- Can be applied to classes or methods

**When to use**: For methods that modify data or need transaction guarantees.

---

## Testing Annotations

### 1. `@SpringBootTest`
**Purpose**: Loads the complete Spring application context for integration tests.

```java
@SpringBootTest
class CustomerServiceIntegrationTest {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Test
    void shouldCreateCustomer() {
        Customer customer = new Customer("John", "john@example.com");
        Customer saved = customerService.createCustomer(customer);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(customerRepository.findById(saved.getId())).isPresent();
    }
}
```

### 2. `@DataJpaTest`
**Purpose**: Tests JPA repositories with an in-memory database.

```java
@DataJpaTest
class CustomerRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Test
    void shouldFindByEmail() {
        Customer customer = new Customer("John", "john@example.com");
        entityManager.persistAndFlush(customer);
        
        Optional<Customer> found = customerRepository.findByEmail("john@example.com");
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John");
    }
}
```

### 3. `@MockBean`
**Purpose**: Replaces beans in the application context with mocks.

```java
@SpringBootTest
class CustomerServiceTest {
    
    @Autowired
    private CustomerService customerService;
    
    @MockBean
    private CustomerRepository customerRepository;
    
    @MockBean
    private EmailService emailService;
    
    @Test
    void shouldCreateCustomer() {
        Customer customer = new Customer("John", "john@example.com");
        Customer saved = new Customer(1L, "John", "john@example.com");
        
        given(customerRepository.save(customer)).willReturn(saved);
        
        Customer result = customerService.createCustomer(customer);
        
        assertThat(result.getId()).isEqualTo(1L);
        verify(emailService).sendWelcomeEmail("john@example.com");
    }
}
```

---

## Advanced Annotations

### 1. `@Profile`
**Purpose**: Conditionally includes beans based on active profiles.

```java
@Configuration
@Profile("production")
public class ProductionConfig {
    
    @Bean
    public DataSource dataSource() {
        // Production database configuration
        return new HikariDataSource();
    }
}

@Configuration
@Profile("test")
public class TestConfig {
    
    @Bean
    public DataSource dataSource() {
        // In-memory test database
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
}

@Service
@Profile("!production") // Active when production profile is NOT active
public class DevelopmentEmailService implements EmailService {
    // Development implementation
}
```

### 2. `@Conditional`
**Purpose**: Conditionally includes beans based on custom conditions.

```java
@Configuration
public class ConditionalConfig {
    
    @Bean
    @ConditionalOnProperty(name = "feature.email.enabled", havingValue = "true")
    public EmailService emailService() {
        return new SmtpEmailService();
    }
    
    @Bean
    @ConditionalOnMissingBean(EmailService.class)
    public EmailService noOpEmailService() {
        return new NoOpEmailService();
    }
    
    @Bean
    @ConditionalOnClass(RedisTemplate.class)
    public CacheManager redisCacheManager() {
        return new RedisCacheManager();
    }
}
```

### 3. `@EventListener`
**Purpose**: Handles application events.

```java
@Component
public class CustomerEventListener {
    
    @EventListener
    public void handleCustomerCreated(CustomerCreatedEvent event) {
        System.out.println("Customer created: " + event.getCustomer().getName());
        // Send welcome email, update statistics, etc.
    }
    
    @EventListener
    @Async
    public void handleCustomerUpdated(CustomerUpdatedEvent event) {
        // Asynchronous event handling
        analyticsService.trackCustomerUpdate(event.getCustomer());
    }
}
```

---

## Best Practices

### 1. Dependency Injection
✅ **DO**: Use constructor injection
```java
@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final EmailService emailService;
    
    public CustomerService(CustomerRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }
}
```

❌ **DON'T**: Use field injection
```java
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository; // Hard to test, hidden dependencies
}
```

### 2. Configuration Properties
✅ **DO**: Use `@ConfigurationProperties` for complex configuration
```java
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    private String jwtSecret;
    private int jwtExpirationMinutes;
    // getters and setters
}
```

❌ **DON'T**: Use multiple `@Value` annotations
```java
@Component
public class SecurityConfig {
    @Value("${app.security.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.security.jwt-expiration-minutes}")
    private int jwtExpirationMinutes;
    // ... many more @Value annotations
}
```

### 3. Exception Handling
✅ **DO**: Use `@ControllerAdvice` for global exception handling
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
```

### 4. Validation
✅ **DO**: Use Bean Validation annotations
```java
@Entity
public class Customer {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
```

---

## Common Pitfalls

### 1. Circular Dependencies
❌ **Problem**:
```java
@Service
public class ServiceA {
    @Autowired
    private ServiceB serviceB; // ServiceB also depends on ServiceA
}
```

✅ **Solution**: Refactor to remove circular dependency or use `@Lazy`

### 2. Missing `@Transactional`
❌ **Problem**:
```java
@Service
public class CustomerService {
    public void updateCustomer(Customer customer) {
        customer.setName("Updated");
        // Changes might not be saved without @Transactional
    }
}
```

✅ **Solution**: Add `@Transactional` to methods that modify data

### 3. Wrong Stereotype Annotations
❌ **Problem**:
```java
@Repository // Wrong! This is a service class
public class CustomerService {
    // Business logic here
}
```

✅ **Solution**: Use correct annotations (`@Service` for business logic)

---

## Real-World Examples

### Complete Customer Management Example

```java
// Entity
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;
    
    @Column(unique = true)
    @Email(message = "Invalid email")
    private String email;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    
    // Constructors, getters, setters
}

// Repository
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findByEmail(String email);
    
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %?1%")
    List<Customer> findByNameContaining(String name);
}

// Service
@Service
@Transactional(readOnly = true)
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    
    public CustomerService(CustomerRepository customerRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }
    
    @Transactional
    public Customer createCustomer(Customer customer) {
        validateUniqueEmail(customer.getEmail());
        Customer saved = customerRepository.save(customer);
        emailService.sendWelcomeEmail(saved);
        return saved;
    }
    
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    
    @Transactional
    public Customer updateCustomer(Long id, Customer customerData) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
        
        customer.setName(customerData.getName());
        customer.setEmail(customerData.getEmail());
        
        return customerRepository.save(customer);
    }
    
    private void validateUniqueEmail(String email) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}

// REST Controller
@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.findById(id)
            .map(customer -> ResponseEntity.ok(customer))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody Customer customer) {
        
        try {
            Customer updated = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updated);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

// Exception Handler
@ControllerAdvice
public class CustomerExceptionHandler {
    
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("CUSTOMER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse("EMAIL_EXISTS", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}

// Configuration
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {
    
    @Bean
    @ConditionalOnProperty(name = "app.email.enabled", havingValue = "true")
    public EmailService smtpEmailService(ApplicationProperties properties) {
        return new SmtpEmailService(properties.getEmail());
    }
    
    @Bean
    @ConditionalOnMissingBean(EmailService.class)
    public EmailService noOpEmailService() {
        return new NoOpEmailService();
    }
}

// Configuration Properties
@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationProperties {
    
    private Email email = new Email();
    private Security security = new Security();
    
    @Data
    public static class Email {
        private boolean enabled = false;
        private String host;
        private int port = 587;
        private String username;
        private String password;
    }
    
    @Data
    public static class Security {
        private String jwtSecret;
        private int jwtExpirationMinutes = 60;
    }
}
```

This comprehensive guide covers all the essential Spring Boot annotations with practical examples and best practices. Each annotation is explained with its purpose, how it works, and when to use it. The real-world example shows how these annotations work together in a complete application.
