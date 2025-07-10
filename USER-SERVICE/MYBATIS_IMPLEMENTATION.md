# MyBatis Implementation with Raw SQL Queries

## Overview

This document describes the MyBatis implementation for the user management system, replacing JPA with raw SQL queries for all database operations.

## Architecture Changes

### From JPA to MyBatis
- **Removed**: Spring Data JPA dependencies
- **Added**: MyBatis Spring Boot Starter
- **Replaced**: JPA entities with simple POJOs
- **Replaced**: JPA repositories with MyBatis mappers
- **Added**: XML mapper files with raw SQL queries

## Dependencies

### Updated build.gradle
```gradle
dependencies {
    // Removed JPA
    // implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    
    // Added MyBatis
    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'
    
    // Other dependencies remain the same
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    // ...
}
```

### Updated application.yml
```yaml
spring:
  mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.userservice.api.user.model.table
    configuration:
      map-underscore-to-camel-case: true
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

## Entity Structure

### User Entity (POJO)
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role;
    private UserStatus status = UserStatus.ACTIVE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    
    // Enums
    public enum UserRole { STUDENT, TEACHER, STAFF, ADMIN }
    public enum UserStatus { ACTIVE, INACTIVE, SUSPENDED, DELETED }
}
```

### Student Entity (POJO)
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private Long userId;
    private String address;
    private String studentId;
    private String grade;
    private String section;
    private String parentName;
    private String parentPhone;
}
```

### Teacher Entity (POJO)
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private Long userId;
    private String department;
    private String qualification;
    private String address;
    private String employeeId;
    private String subject;
    private String experience;
}
```

### Staff Entity (POJO)
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    private Long id;
    private Long userId;
    private String department;
    private String qualification;
    private String designation;
    private Long addressId;
    private String employeeId;
    private String workSchedule;
}
```

## MyBatis Mapper Interfaces

### UserMapper
```java
@Mapper
public interface UserMapper {
    // Insert operations
    int insertUser(User user);
    
    // Select operations
    User selectUserById(@Param("id") Long id);
    User selectUserByUsername(@Param("username") String username);
    User selectUserByEmail(@Param("email") String email);
    List<User> selectAllUsers();
    List<User> selectUsersByRole(@Param("role") User.UserRole role);
    List<User> selectUsersByStatus(@Param("status") User.UserStatus status);
    List<User> searchUsers(@Param("keyword") String keyword);
    
    // Update operations
    int updateUser(User user);
    int updateUserStatus(@Param("id") Long id, @Param("status") User.UserStatus status);
    int updateLastLogin(@Param("id") Long id);
    
    // Delete operations
    int deleteUserById(@Param("id") Long id);
    
    // Check existence
    boolean existsByUsername(@Param("username") String username);
    boolean existsByEmail(@Param("email") String email);
    
    // Count operations
    long countUsers();
    long countUsersByRole(@Param("role") User.UserRole role);
    long countUsersByStatus(@Param("status") User.UserStatus status);
}
```

## XML Mapper Files

### UserMapper.xml
Located at: `src/main/resources/mapper/UserMapper.xml`

Key SQL Queries:
```xml
<!-- Select User by ID -->
<select id="selectUserById" resultMap="UserResultMap">
    SELECT 
        id, username, password, email, first_name, last_name, phone_number, role, status, 
        created_at, updated_at, last_login
    FROM users 
    WHERE id = #{id}
</select>

<!-- Search Users -->
<select id="searchUsers" resultMap="UserResultMap">
    SELECT 
        id, username, password, email, first_name, last_name, phone_number, role, status, 
        created_at, updated_at, last_login
    FROM users 
    WHERE 
        username ILIKE CONCAT('%', #{keyword}, '%') OR
        email ILIKE CONCAT('%', #{keyword}, '%') OR
        first_name ILIKE CONCAT('%', #{keyword}, '%') OR
        last_name ILIKE CONCAT('%', #{keyword}, '%')
    ORDER BY created_at DESC
</select>

<!-- Insert User -->
<insert id="insertUser" parameterType="com.userservice.api.user.model.table.User" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO users (
        username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at
    ) VALUES (
        #{username}, #{password}, #{email}, #{firstName}, #{lastName}, #{phoneNumber}, #{role}, #{status}, 
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
</insert>
```

### StudentMapper.xml
Located at: `src/main/resources/mapper/StudentMapper.xml`

Key SQL Queries:
```xml
<!-- Select Student by User ID -->
<select id="selectStudentByUserId" resultMap="StudentResultMap">
    SELECT 
        id, user_id, address, student_id, grade, section, parent_name, parent_phone
    FROM students 
    WHERE user_id = #{userId}
</select>

<!-- Select Students by Grade -->
<select id="selectStudentsByGrade" resultMap="StudentResultMap">
    SELECT 
        id, user_id, address, student_id, grade, section, parent_name, parent_phone
    FROM students 
    WHERE grade = #{grade}
    ORDER BY id DESC
</select>
```

### TeacherMapper.xml
Located at: `src/main/resources/mapper/TeacherMapper.xml`

Key SQL Queries:
```xml
<!-- Select Teachers by Department -->
<select id="selectTeachersByDepartment" resultMap="TeacherResultMap">
    SELECT 
        id, user_id, department, qualification, address, employee_id, subject, experience
    FROM teachers 
    WHERE department = #{department}
    ORDER BY id DESC
</select>

<!-- Select Teachers by Subject -->
<select id="selectTeachersBySubject" resultMap="TeacherResultMap">
    SELECT 
        id, user_id, department, qualification, address, employee_id, subject, experience
    FROM teachers 
    WHERE subject = #{subject}
    ORDER BY id DESC
</select>
```

### StaffMapper.xml
Located at: `src/main/resources/mapper/StaffMapper.xml`

Key SQL Queries:
```xml
<!-- Select Staff by Department -->
<select id="selectStaffByDepartment" resultMap="StaffResultMap">
    SELECT 
        id, user_id, department, qualification, designation, employee_id, work_schedule, address_id
    FROM staffs 
    WHERE department = #{department}
    ORDER BY id DESC
</select>

<!-- Select Staff by Designation -->
<select id="selectStaffByDesignation" resultMap="StaffResultMap">
    SELECT 
        id, user_id, department, qualification, designation, employee_id, work_schedule, address_id
    FROM staffs 
    WHERE designation = #{designation}
    ORDER BY id DESC
</select>
```

## Service Layer Changes

### UserService Updates
```java
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final UserMapper userMapper;  // Changed from UserRepository
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUserById(Long id) {
        log.info("Fetching user with ID: {}", id);
        User user = userMapper.selectUserById(id);  // Direct mapper call
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        return UserResponse.fromUser(user);
    }

    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userMapper.selectAllUsers().stream()  // Direct mapper call
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    public UserResponse createUser(CreateUserRequest request) {
        // Validation
        if (userMapper.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userMapper.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .status(User.UserStatus.ACTIVE)
                .build();

        userMapper.insertUser(user);  // Direct mapper call
        return UserResponse.fromUser(user);
    }
}
```

## Key Features of MyBatis Implementation

### 1. Raw SQL Queries
- All database operations use explicit SQL queries
- Full control over query optimization
- Better performance for complex queries
- Native database features utilization

### 2. Result Mapping
- Explicit result maps for each entity
- Handles column name to property mapping
- Supports complex object relationships

### 3. Parameter Binding
- Type-safe parameter binding with `@Param` annotations
- Automatic parameter escaping
- Support for complex parameter objects

### 4. Dynamic SQL
- Conditional query building
- Reusable query fragments
- Complex WHERE clause handling

### 5. Performance Optimizations
- Direct SQL control for query optimization
- Efficient indexing strategies
- Minimal ORM overhead

## Database Operations

### Select Operations
```java
// Get user by ID
User user = userMapper.selectUserById(1L);

// Get users by role
List<User> teachers = userMapper.selectUsersByRole(User.UserRole.TEACHER);

// Search users
List<User> results = userMapper.searchUsers("john");

// Get all users
List<User> allUsers = userMapper.selectAllUsers();
```

### Insert Operations
```java
// Insert new user
User user = User.builder()
    .username("john.doe")
    .password("encoded_password")
    .email("john@example.com")
    .firstName("John")
    .lastName("Doe")
    .role(User.UserRole.STUDENT)
    .build();

userMapper.insertUser(user);
```

### Update Operations
```java
// Update user
user.setFirstName("Jane");
userMapper.updateUser(user);

// Update status only
userMapper.updateUserStatus(1L, User.UserStatus.INACTIVE);

// Update last login
userMapper.updateLastLogin(1L);
```

### Delete Operations
```java
// Delete user
userMapper.deleteUserById(1L);
```

### Existence Checks
```java
// Check if username exists
boolean exists = userMapper.existsByUsername("john.doe");

// Check if email exists
boolean exists = userMapper.existsByEmail("john@example.com");
```

## Advantages of MyBatis Implementation

1. **Performance**: Direct SQL control for optimal performance
2. **Flexibility**: Full control over query structure and optimization
3. **Transparency**: Clear visibility of all database operations
4. **Maintainability**: Explicit SQL queries are easier to debug and optimize
5. **Database Features**: Full access to database-specific features
6. **Complex Queries**: Better handling of complex joins and aggregations

## Migration from JPA

### Steps Completed
1. ✅ Replaced JPA dependencies with MyBatis
2. ✅ Converted entities to POJOs
3. ✅ Created mapper interfaces
4. ✅ Created XML mapper files with raw SQL
5. ✅ Updated service layer to use mappers
6. ✅ Updated configuration files
7. ✅ Removed JPA repositories

### Benefits Achieved
- **Better Performance**: Direct SQL control
- **Explicit Queries**: Clear visibility of database operations
- **Optimization**: Full control over query optimization
- **Flexibility**: Easy to modify and extend queries
- **Debugging**: Easier to debug database issues

## API Endpoints (Unchanged)
All REST API endpoints remain the same, only the underlying data access layer has changed from JPA to MyBatis.

- `POST /api/v1/users` - Create user
- `GET /api/v1/users/{id}` - Get user by ID
- `GET /api/v1/users/role/{role}` - Get users by role
- `GET /api/v1/users/search?keyword={keyword}` - Search users
- `PUT /api/v1/users/{id}` - Update user
- `DELETE /api/v1/users/{id}` - Delete user
- And more...

The MyBatis implementation provides the same functionality as the JPA version but with better performance and more explicit control over database operations. 