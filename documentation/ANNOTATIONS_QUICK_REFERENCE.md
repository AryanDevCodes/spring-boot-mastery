# Spring Boot Annotations - Quick Reference Card

## 🔥 Most Used Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Component` | Generic Spring bean | `@Component public class GameService` |
| `@Service` | Business logic layer | `@Service public class CustomerService` |
| `@Repository` | Data access layer | `@Repository public class CustomerRepo` |
| `@Controller` | Web layer (returns views) | `@Controller public class WebController` |
| `@RestController` | REST API layer | `@RestController public class ApiController` |
| `@Autowired` | Dependency injection | `@Autowired private CustomerService service;` |
| `@RequestMapping` | Map HTTP requests | `@RequestMapping("/api/users")` |
| `@GetMapping` | Handle GET requests | `@GetMapping("/users/{id}")` |
| `@PostMapping` | Handle POST requests | `@PostMapping("/users")` |
| `@PathVariable` | Extract URL parameters | `public User getUser(@PathVariable Long id)` |
| `@RequestParam` | Extract query parameters | `public List<User> search(@RequestParam String q)` |
| `@RequestBody` | Parse JSON request body | `public User create(@RequestBody User user)` |

## 💾 Data Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Entity` | JPA entity | `@Entity public class Customer` |
| `@Id` | Primary key | `@Id private Long id;` |
| `@GeneratedValue` | Auto-generated values | `@GeneratedValue(strategy = GenerationType.IDENTITY)` |
| `@Column` | Column mapping | `@Column(nullable = false, unique = true)` |
| `@OneToMany` | One-to-many relationship | `@OneToMany(mappedBy = "customer")` |
| `@ManyToOne` | Many-to-one relationship | `@ManyToOne private Customer customer;` |
| `@Transactional` | Transaction management | `@Transactional public void updateData()` |

## ⚙️ Configuration Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Configuration` | Configuration class | `@Configuration public class AppConfig` |
| `@Bean` | Bean definition | `@Bean public DataSource dataSource()` |
| `@Value` | Property injection | `@Value("${app.name}") private String name;` |
| `@ConfigurationProperties` | Bulk property binding | `@ConfigurationProperties(prefix = "app")` |
| `@Primary` | Primary bean choice | `@Primary @Service public class EmailService` |
| `@Qualifier` | Specific bean selection | `@Qualifier("emailService") NotificationService` |
| `@Profile` | Environment-specific beans | `@Profile("production") @Configuration` |

## 🧪 Testing Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@SpringBootTest` | Integration test | `@SpringBootTest class MyServiceTest` |
| `@DataJpaTest` | JPA repository test | `@DataJpaTest class CustomerRepoTest` |
| `@WebMvcTest` | Web layer test | `@WebMvcTest(CustomerController.class)` |
| `@MockBean` | Mock Spring beans | `@MockBean private CustomerService service;` |
| `@Test` | Test method | `@Test void shouldCreateCustomer()` |

## 🔄 Common Patterns

### Constructor Injection (Recommended)
```java
@Service
public class CustomerService {
    private final CustomerRepository repository;
    
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
}
```

### REST Controller Pattern
```java
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @GetMapping
    public List<Customer> getAll() { /* ... */ }
    
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) { /* ... */ }
    
    @PostMapping
    public Customer create(@RequestBody Customer customer) { /* ... */ }
    
    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) { /* ... */ }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { /* ... */ }
}
```

### JPA Entity Pattern
```java
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    
    // Constructors, getters, setters
}
```

### Configuration Properties Pattern
```java
@ConfigurationProperties(prefix = "app.database")
@Component
public class DatabaseProperties {
    private String url;
    private String username;
    private String password;
    private int maxConnections;
    
    // Getters and setters
}
```

## 🚨 Common Mistakes to Avoid

1. **Field Injection**: ❌ Use `@Autowired` on fields → ✅ Use constructor injection
2. **Missing @Transactional**: ❌ Forget on data-modifying methods → ✅ Add @Transactional
3. **Wrong Stereotype**: ❌ @Repository on service classes → ✅ Use correct annotations
4. **Circular Dependencies**: ❌ ServiceA depends on ServiceB depends on ServiceA → ✅ Refactor design
5. **Missing @RequestBody**: ❌ JSON not parsed → ✅ Add @RequestBody for JSON requests

## 📖 For Detailed Explanations
See `SPRING_ANNOTATIONS_GUIDE.md` for comprehensive documentation with examples and best practices.
