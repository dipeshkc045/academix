# UUID Implementation in User Service

## Overview

The User Service has been updated to use UUID (Universally Unique Identifier) for all primary keys and foreign keys instead of auto-incrementing integers. This provides better security, scalability, and avoids potential conflicts in distributed systems.

## Changes Made

### 1. Database Schema Updates

**Migration File**: `src/main/resources/db/migration/V1__Create_User_Tables.sql`

- **Users Table**: Changed `id` from `BIGSERIAL` to `UUID PRIMARY KEY DEFAULT gen_random_uuid()`
- **Students Table**: Changed `id` to `UUID` and `user_id` to `UUID`
- **Teachers Table**: Changed `id` to `UUID` and `user_id` to `UUID`
- **Staffs Table**: Changed `id` to `UUID` and `user_id` to `UUID`

### 2. Entity Updates

All entities now use `UUID` for their primary keys:

- **User.java**: `private UUID id;` with `@GeneratedValue(strategy = GenerationType.UUID)`
- **Student.java**: `private UUID id;` and `private UUID userId;`
- **Teacher.java**: `private UUID id;` and `private UUID userId;`
- **Staff.java**: `private UUID id;` and `private UUID userId;`

### 3. Repository Updates

JPA repositories updated to use `UUID`:

- **UserRepository**: `extends JpaRepository<User, UUID>`
- **StudentRepository**: `extends JpaRepository<Student, UUID>`
- **TeacherRepository**: `extends JpaRepository<Teacher, UUID>`
- **StaffRepository**: `extends JpaRepository<Staff, UUID>`

### 4. MyBatis Mapper Updates

All MyBatis mappers updated to use `UUID` for read operations:

- **UserMapper**: All methods now use `UUID` parameters
- **StudentMapper**: All methods now use `UUID` parameters
- **TeacherMapper**: All methods now use `UUID` parameters
- **StaffMapper**: All methods now use `UUID` parameters

### 5. Service Layer Updates

**UserService.java**:
- Updated all method signatures to use `UUID`
- Uses JPA repositories for write operations (INSERT, UPDATE, DELETE)
- Uses MyBatis mappers for read operations (SELECT)

### 6. Controller Updates

**UserController.java**:
- Updated all endpoint methods to use `UUID` path variables
- All `@PathVariable` parameters now use `UUID` type

### 7. Response DTO Updates

**UserResponse.java**:
- Changed `id` field from `Long` to `UUID`

## Benefits of UUID Implementation

### 1. Security
- UUIDs are not predictable or sequential
- Reduces risk of enumeration attacks
- Better for public APIs

### 2. Scalability
- No conflicts in distributed systems
- Better for horizontal scaling
- No need for centralized ID generation

### 3. Database Independence
- Works across different database systems
- No dependency on auto-increment sequences
- Better for microservices architecture

### 4. Data Migration
- Easier to merge data from different sources
- No ID conflicts during data consolidation

## API Usage Examples

### Create User
```bash
POST /api/users
Content-Type: application/json

{
  "username": "john.doe",
  "password": "password123",
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "role": "STUDENT"
}
```

### Get User by ID
```bash
GET /api/users/550e8400-e29b-41d4-a716-446655440000
```

### Update User
```bash
PUT /api/users/550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json

{
  "firstName": "Johnny",
  "lastName": "Smith",
  "email": "johnny.smith@example.com"
}
```

### Delete User
```bash
DELETE /api/users/550e8400-e29b-41d4-a716-446655440000
```

## Database Considerations

### PostgreSQL UUID Extension
The migration uses `gen_random_uuid()` which requires the `uuid-ossp` extension. Ensure it's enabled:

```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

### Index Performance
UUIDs are larger than integers, but with proper indexing, performance impact is minimal. The migration includes appropriate indexes:

- Primary key indexes (automatic)
- Foreign key indexes
- Business key indexes (username, email, etc.)

### Storage Impact
- UUIDs use 16 bytes vs 8 bytes for BIGINT
- Minimal storage overhead for better security and scalability

## Testing

When testing the API, use valid UUID format:
- Valid: `550e8400-e29b-41d4-a716-446655440000`
- Invalid: `123` or `abc`

## Migration Notes

1. **Existing Data**: If migrating from auto-increment IDs, you'll need a data migration script
2. **Foreign Keys**: All foreign key relationships use UUID
3. **Indexes**: Maintained all existing indexes with UUID columns
4. **Triggers**: Updated timestamp triggers work with UUID columns

## Future Considerations

1. **Caching**: Consider UUID-specific caching strategies
2. **Logging**: UUIDs in logs are longer but more secure
3. **Monitoring**: Monitor UUID generation performance
4. **Documentation**: Update API documentation to reflect UUID usage 