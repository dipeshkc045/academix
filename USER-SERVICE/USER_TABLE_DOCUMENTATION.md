# User Table Structure and Relationships

## Overview

This document describes the user management system with a centralized `users` table that has relationships with `students`, `teachers`, and `staffs` tables.

## Database Schema

### Users Table
The `users` table serves as the central entity for all user types in the system.

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    role VARCHAR(20) NOT NULL, -- STUDENT, TEACHER, STAFF, ADMIN
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', -- ACTIVE, INACTIVE, SUSPENDED, DELETED
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);
```

### Students Table
Contains student-specific information with a one-to-one relationship to the users table.

```sql
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    address TEXT,
    student_id VARCHAR(50) UNIQUE,
    grade VARCHAR(20),
    section VARCHAR(20),
    parent_name VARCHAR(100),
    parent_phone VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

### Teachers Table
Contains teacher-specific information with a one-to-one relationship to the users table.

```sql
CREATE TABLE teachers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    department VARCHAR(100),
    qualification VARCHAR(100),
    address TEXT,
    employee_id VARCHAR(50) UNIQUE,
    subject VARCHAR(100),
    experience VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

### Staffs Table
Contains staff-specific information with a one-to-one relationship to the users table.

```sql
CREATE TABLE staffs (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    department VARCHAR(100),
    qualification VARCHAR(100),
    designation VARCHAR(100),
    employee_id VARCHAR(50) UNIQUE,
    work_schedule VARCHAR(100),
    address_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

## Entity Relationships

### One-to-One Relationships
- **User ↔ Student**: One user can have one student profile
- **User ↔ Teacher**: One user can have one teacher profile  
- **User ↔ Staff**: One user can have one staff profile

### Relationship Mapping
```java
// In User entity
@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Student student;

@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Teacher teacher;

@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Staff staff;

// In Student entity
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

// In Teacher entity
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

// In Staff entity
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;
```

## User Roles and Status

### User Roles (UserRole enum)
- `STUDENT`: Student users
- `TEACHER`: Teacher users
- `STAFF`: Staff users
- `ADMIN`: Administrative users

### User Status (UserStatus enum)
- `ACTIVE`: User is active and can access the system
- `INACTIVE`: User account is temporarily disabled
- `SUSPENDED`: User account is suspended
- `DELETED`: User account is soft deleted

## API Endpoints

### User Management
- `POST /api/v1/users` - Create a new user
- `GET /api/v1/users/{id}` - Get user by ID
- `GET /api/v1/users/username/{username}` - Get user by username
- `GET /api/v1/users` - Get all users
- `GET /api/v1/users/role/{role}` - Get users by role
- `GET /api/v1/users/status/{status}` - Get users by status
- `GET /api/v1/users/search?keyword={keyword}` - Search users
- `PUT /api/v1/users/{id}` - Update user
- `DELETE /api/v1/users/{id}` - Delete user (soft delete)
- `GET /api/v1/users/check-username?username={username}` - Check username availability
- `GET /api/v1/users/check-email?email={email}` - Check email availability

## Data Flow

### Creating a User
1. Create user record in `users` table with basic information
2. Create corresponding profile in `students`, `teachers`, or `staffs` table
3. Link the profile to the user via `user_id` foreign key

### User Authentication
1. User provides username/email and password
2. System validates credentials against `users` table
3. System checks user status (must be ACTIVE)
4. System updates `last_login` timestamp

### User Profile Access
1. Retrieve user information from `users` table
2. Based on user role, fetch additional information from corresponding profile table
3. Return combined user and profile information

## Security Considerations

1. **Password Hashing**: All passwords are encrypted using BCrypt
2. **Unique Constraints**: Username and email must be unique across all users
3. **Soft Deletes**: Users are soft deleted (status = DELETED) rather than hard deleted
4. **Status Validation**: Only ACTIVE users can access the system
5. **Cascade Operations**: Deleting a user cascades to delete associated profile records

## Performance Optimizations

1. **Indexes**: Created indexes on frequently queried columns
2. **Lazy Loading**: Profile relationships are loaded lazily to improve performance
3. **Search Optimization**: Full-text search capabilities for user lookup

## Migration

The database schema is managed through SQL migration scripts located in:
`src/main/resources/db/migration/V1__Create_User_Tables.sql`

## Example Usage

### Creating a Student User
```json
POST /api/v1/users
{
    "username": "john.doe",
    "password": "password123",
    "email": "john.doe@school.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "+1234567890",
    "role": "STUDENT"
}
```

### Creating a Teacher User
```json
POST /api/v1/users
{
    "username": "jane.smith",
    "password": "password123",
    "email": "jane.smith@school.com",
    "firstName": "Jane",
    "lastName": "Smith",
    "phoneNumber": "+1234567891",
    "role": "TEACHER"
}
```

### Creating a Staff User
```json
POST /api/v1/users
{
    "username": "mike.johnson",
    "password": "password123",
    "email": "mike.johnson@school.com",
    "firstName": "Mike",
    "lastName": "Johnson",
    "phoneNumber": "+1234567892",
    "role": "STAFF"
}
``` 