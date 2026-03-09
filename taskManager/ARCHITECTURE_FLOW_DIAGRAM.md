# Task Manager Application - Complete Architecture Flow Diagram

This document provides a comprehensive overview of the Task Manager application's architecture and data flow using Mermaid diagrams.

## 1. Complete Application Flow Overview

```mermaid
flowchart TB
    Client[Client/API Consumer]
    
    subgraph SpringBootApp[Spring Boot Application]
        Controller[TaskController]
        GlobalHandler[GlobalExceptionHandler]
        Mapper[TaskMapper]
        Service[TaskService]
        Repository[TaskRepository]
        
        subgraph DTOs[DTOs]
            RequestDTO[CreateTaskRequestDTO]
            TaskDTO[TaskDTO]
            ErrorDTO[ErrorDTO]
        end
        
        subgraph Domain[Domain]
            Request[CreateTaskRequest]
        end
        
        subgraph Entity[Entity]
            Task[Task Entity]
        end
    end
    
    Database[(PostgreSQL Database)]
    
    Client -->|POST /api/v1/tasks| Controller
    Controller -->|Validate| RequestDTO
    RequestDTO -->|Validation Success| Mapper
    RequestDTO -->|Validation Failed| GlobalHandler
    GlobalHandler -->|Error Response| Client
    Mapper -->|fromDto| Request
    Controller -->|createTask| Service
    Service -->|save| Repository
    Repository -->|JPA Operations| Database
    Database -->|Task Entity| Repository
    Repository -->|Task| Service
    Service -->|Task| Controller
    Controller -->|toDto| Mapper
    Mapper -->|TaskDTO| Controller
    Controller -->|201 Created| Client
    
    style Client fill:#e1f5ff
    style Controller fill:#fff4e6
    style Service fill:#e8f5e9
    style Repository fill:#f3e5f5
    style Database fill:#ffebee
```

## 2. Detailed Request-Response Flow with Examples

```mermaid
sequenceDiagram
    participant Client
    participant TaskController
    participant Validator
    participant GlobalExceptionHandler
    participant TaskMapper
    participant TaskService
    participant TaskRepository
    participant Database

    Note over Client: POST /api/v1/tasks
    Note over Client: Body: JSON with title, description, dueDate, priority
    
    Client->>TaskController: HTTP POST Request
    TaskController->>Validator: Validate CreateTaskRequestDTO
    
    alt Validation Success
        Validator-->>TaskController: Validation Passed
        TaskController->>TaskMapper: fromDto(CreateTaskRequestDTO)
        Note over TaskMapper: Convert DTO to Domain Object
        TaskMapper-->>TaskController: CreateTaskRequest
        
        TaskController->>TaskService: createTask(CreateTaskRequest)
        Note over TaskService: Process task data with title, description, dueDate, priority
        
        TaskService->>TaskService: Create Task Entity and Set status OPEN
        Note over TaskService: Auto-generate id, set timestamps
        
        TaskService->>TaskRepository: save(Task)
        TaskRepository->>Database: INSERT INTO tasks
        Database-->>TaskRepository: Task with UUID
        Note over Database: Generated UUID: 550e8400-e29b-41d4...
        
        TaskRepository-->>TaskService: Persisted Task
        TaskService-->>TaskController: Task Entity
        
        TaskController->>TaskMapper: toDto(Task)
        TaskMapper-->>TaskController: TaskDTO
        Note over TaskMapper: Convert to response DTO with all fields
        
        TaskController-->>Client: 201 CREATED + TaskDTO
        
    else Validation Failed
        Validator-->>TaskController: MethodArgumentNotValidException
        TaskController->>GlobalExceptionHandler: handleValidationExceptions()
        Note over GlobalExceptionHandler: Extract first error message
        GlobalExceptionHandler-->>Client: 400 BAD REQUEST with ErrorDTO
    end
```

## 3. Layer Architecture Diagram

```mermaid
graph TB
    subgraph PresentationLayer[Presentation Layer]
        A1[TaskController]
        A2[GlobalExceptionHandler]
    end
    
    subgraph ApplicationLayer[Application Layer]
        B1[TaskService Interface]
        B2[TaskServiceImpl]
    end
    
    subgraph DomainLayer[Domain Layer]
        C1[Task Entity]
        C2[TaskStatus Enum]
        C3[TaskPriority Enum]
        C4[CreateTaskRequest]
        C5[CreateTaskRequestDTO]
        C6[TaskDTO]
        C7[ErrorDTO]
    end
    
    subgraph DataAccessLayer[Data Access Layer]
        D1[TaskRepository]
        D2[JpaRepository]
    end
    
    subgraph InfrastructureLayer[Infrastructure Layer]
        E1[TaskMapper]
        E2[TaskMapperImpl]
    end
    
    subgraph PersistenceLayer[Persistence Layer]
        F1[(PostgreSQL Database)]
    end
    
    A1 --> B1
    A1 --> E1
    B2 -.implements.-> B1
    B2 --> D1
    D1 -.extends.-> D2
    D1 --> F1
    A1 --> C5
    A1 --> C6
    A2 --> C7
    B2 --> C1
    B2 --> C4
    E2 -.implements.-> E1
    E1 --> C4
    E1 --> C5
    E1 --> C6
    C1 --> C2
    C1 --> C3
    
    style A1 fill:#ffccbc
    style A2 fill:#ffccbc
    style B1 fill:#c5cae9
    style B2 fill:#c5cae9
    style D1 fill:#b2dfdb
    style E1 fill:#fff9c4
    style E2 fill:#fff9c4
    style F1 fill:#f8bbd0
```

## 4. Component Interaction Diagram

```mermaid
graph LR
    subgraph ClientLayer[Client Layer]
        HTTP[HTTP Client]
    end
    
    subgraph ControllerLayer[Controller Layer]
        TC["TaskController<br/>POST /api/v1/tasks"]
        GEH["GlobalExceptionHandler<br/>ControllerAdvice"]
    end
    
    subgraph MapperLayer[Mapper Layer]
        TM["TaskMapper<br/>DTO to Domain"]
    end
    
    subgraph ServiceLayer[Service Layer]
        TS["TaskServiceImpl<br/>Business Logic"]
    end
    
    subgraph RepositoryLayer[Repository Layer]
        TR["TaskRepository<br/>extends JpaRepository"]
    end
    
    subgraph DatabaseLayer[Database Layer]
        DB[("PostgreSQL<br/>tasks table")]
    end
    
    HTTP -->|JSON Request| TC
    TC -->|Validation Error| GEH
    GEH -->|ErrorDTO| HTTP
    TC <-->|Convert| TM
    TC -->|Domain Object| TS
    TS -->|Entity| TR
    TR <-->|SQL| DB
    TR -->|Entity| TS
    TS -->|Entity| TC
    TC -->|JSON Response| HTTP
    
    style HTTP fill:#e3f2fd
    style TC fill:#fff3e0
    style GEH fill:#ffebee
    style TM fill:#f3e5f5
    style TS fill:#e8f5e9
    style TR fill:#fce4ec
    style DB fill:#ffcdd2
```

## 5. Data Transformation Flow

```mermaid
graph TD
    subgraph Input[Input: HTTP Request]
        A["JSON Request Body:<br/>title, description, dueDate, priority"]
    end
    
    subgraph Step1[Step 1: Deserialization and Validation]
        B["CreateTaskRequestDTO<br/>Validated with annotations:<br/>NotBlank, Length, FutureOrPresent, NotNull"]
    end
    
    subgraph Step2[Step 2: DTO to Domain Mapping]
        C["CreateTaskRequest<br/>Domain Object with:<br/>title, description, dueDate, priority"]
    end
    
    subgraph Step3[Step 3: Business Logic]
        D["Task Entity Created<br/>id: null<br/>status: OPEN (auto-set)<br/>timestamps set"]
    end
    
    subgraph Step4[Step 4: Persistence]
        E["Database Record<br/>INSERT INTO tasks<br/>UUID generated<br/>All fields persisted"]
    end
    
    subgraph Step5[Step 5: Retrieval]
        F["Task Entity (Persisted)<br/>id: 550e8400-e29b-41d4-...<br/>with all fields"]
    end
    
    subgraph Step6[Step 6: Entity to DTO Mapping]
        G["TaskDTO<br/>Response object with:<br/>id, title, description, dueDate,<br/>priority, status"]
    end
    
    subgraph Output[Output: HTTP Response]
        H["JSON Response (201 Created)<br/>Complete task data with<br/>generated id and status"]
    end
    
    A --> B
    B --> C
    C --> D
    D --> E
    E --> F
    F --> G
    G --> H
    
    style A fill:#e3f2fd
    style B fill:#fff3e0
    style C fill:#f3e5f5
    style D fill:#e8f5e9
    style E fill:#ffebee
    style F fill:#fce4ec
    style G fill:#fff9c4
    style H fill:#c8e6c9
```

## 6. Error Handling Flow

```mermaid
flowchart TD
    Start[Client Request] --> Validation{Validation Success?}
    
    Validation -->|Yes| MapToDto[TaskMapper.fromDto]
    Validation -->|No| Exception[MethodArgumentNotValidException]
    
    Exception --> Handler[GlobalExceptionHandler]
    Handler --> ExtractError[Extract First Error Message]
    ExtractError --> ErrorDTO[Create ErrorDTO]
    ErrorDTO --> Return400[Return 400 BAD REQUEST]
    Return400 --> End1[Client Receives Error]
    
    MapToDto --> Service[TaskService.createTask]
    Service --> CreateEntity[Create Task Entity]
    CreateEntity --> SetDefaults["Set Default Values:<br/>status OPEN, timestamps"]
    SetDefaults --> SaveDB[Repository.save]
    SaveDB --> DBSuccess{Database Operation Success?}
    
    DBSuccess -->|Yes| ReturnEntity[Return Persisted Task]
    DBSuccess -->|No| DBError[Database Exception]
    DBError --> Handler
    
    ReturnEntity --> MapToResponseDto[TaskMapper.toDto]
    MapToResponseDto --> Return201[Return 201 CREATED]
    Return201 --> End2[Client Receives TaskDTO]
    
    style Start fill:#e3f2fd
    style Validation fill:#fff3e0
    style Exception fill:#ffcdd2
    style Handler fill:#ffebee
    style ErrorDTO fill:#ffcdd2
    style Return400 fill:#ef5350
    style Return201 fill:#66bb6a
    style End1 fill:#ffcdd2
    style End2 fill:#c8e6c9
```

## 7. Class Diagram with Relationships

```mermaid
classDiagram
    class TaskController {
        -TaskService taskService
        -TaskMapper taskMapper
        +createTask(CreateTaskRequestDTO) ResponseEntity~TaskDTO~
    }
    
    class GlobalExceptionHandler {
        +handleValidationExceptions(MethodArgumentNotValidException) ResponseEntity~ErrorDTO~
    }
    
    class TaskMapper {
        <<interface>>
        +fromDto(CreateTaskRequestDTO) CreateTaskRequest
        +toDto(Task) TaskDTO
    }
    
    class TaskMapperImpl {
        +fromDto(CreateTaskRequestDTO) CreateTaskRequest
        +toDto(Task) TaskDTO
    }
    
    class TaskService {
        <<interface>>
        +createTask(CreateTaskRequest) Task
    }
    
    class TaskServiceImpl {
        -TaskRepository taskRepository
        +createTask(CreateTaskRequest) Task
    }
    
    class TaskRepository {
        <<interface>>
        extends JpaRepository
    }
    
    class CreateTaskRequestDTO {
        <<record>>
        +String title
        +String description
        +LocalDate dueDate
        +TaskPriority priority
    }
    
    class CreateTaskRequest {
        <<record>>
        +String title
        +String description
        +LocalDate dueDate
        +TaskPriority priority
    }
    
    class TaskDTO {
        <<record>>
        +UUID id
        +String title
        +String description
        +LocalDate dueDate
        +TaskPriority priority
        +TaskStatus status
    }
    
    class ErrorDTO {
        <<record>>
        +String message
    }
    
    class Task {
        <<entity>>
        -UUID id
        -String title
        -String description
        -LocalDate dueDate
        -TaskStatus status
        -TaskPriority priority
        -Instant created
        -Instant updated
        +getters()
        +setters()
    }
    
    class TaskStatus {
        <<enumeration>>
        OPEN
        IN_PROGRESS
        COMPLETED
        CANCELLED
    }
    
    class TaskPriority {
        <<enumeration>>
        HIGH
        MEDIUM
        LOW
    }
    
    TaskController --> TaskService
    TaskController --> TaskMapper
    TaskController ..> CreateTaskRequestDTO
    TaskController ..> TaskDTO
    GlobalExceptionHandler ..> ErrorDTO
    TaskMapperImpl ..|> TaskMapper
    TaskServiceImpl ..|> TaskService
    TaskServiceImpl --> TaskRepository
    TaskMapper ..> CreateTaskRequestDTO
    TaskMapper ..> CreateTaskRequest
    TaskMapper ..> TaskDTO
    TaskMapper ..> Task
    TaskService ..> CreateTaskRequest
    TaskService ..> Task
    Task --> TaskStatus
    Task --> TaskPriority
    CreateTaskRequest --> TaskPriority
    CreateTaskRequestDTO --> TaskPriority
    TaskDTO --> TaskPriority
    TaskDTO --> TaskStatus
```

## 8. Validation Flow Example

```mermaid
graph TD
    A[Client sends POST request] --> B{Request Body Valid?}
    
    subgraph ValidationRules[Validation Rules]
        V1["Title: NotBlank, Length max 255"]
        V2["Description: Length max 1000, Nullable"]
        V3["DueDate: FutureOrPresent, Nullable"]
        V4["Priority: NotNull"]
    end
    
    B -->|Valid| C[All validations pass]
    B -->|Invalid| D[MethodArgumentNotValidException]
    
    C --> E[Continue to Service Layer]
    
    D --> F[GlobalExceptionHandler catches]
    F --> G[Extract error message]
    G --> H{Which validation failed?}
    
    H -->|Title empty| I1["ErrorDTO:<br/>Title must be between 1-255 Character"]
    H -->|Title too long| I1
    H -->|Description too long| I2["ErrorDTO:<br/>Description must be less than 1000 Character"]
    H -->|DueDate in past| I3["ErrorDTO:<br/>Due date must be future"]
    H -->|Priority null| I4["ErrorDTO:<br/>Task must have a priority"]
    
    I1 --> J[Return 400 BAD REQUEST]
    I2 --> J
    I3 --> J
    I4 --> J
    
    J --> K[Client receives error]
    E --> L[Task created successfully]
    
    style B fill:#fff3e0
    style C fill:#c8e6c9
    style D fill:#ffcdd2
    style F fill:#ffebee
    style J fill:#ef5350
    style L fill:#66bb6a
```

## 9. Database Entity Mapping

```mermaid
erDiagram
    TASKS {
        UUID id PK "Primary Key, auto-generated"
        VARCHAR title "NOT NULL, max 255 chars"
        VARCHAR description "max 1000 chars, nullable"
        DATE due_date "nullable"
        VARCHAR status "NOT NULL, enum: OPEN, IN_PROGRESS, COMPLETED, CANCELLED"
        VARCHAR priority "NOT NULL, enum: HIGH, MEDIUM, LOW"
        TIMESTAMP created "NOT NULL, auto-set"
        TIMESTAMP updated "NOT NULL, auto-set"
    }
```

## 10. Complete End-to-End Example

### Example Request:
```http
POST http://localhost:8080/api/v1/tasks
Content-Type: application/json

{
  "title": "Complete Spring Boot Tutorial",
  "description": "Learn Spring Boot basics including REST APIs, JPA, and validation",
  "dueDate": "2026-02-15",
  "priority": "HIGH"
}
```

### Processing Steps:

1. **Controller Layer** (TaskController)
   - Receives HTTP POST request
   - Spring deserializes JSON to CreateTaskRequestDTO
   - Validation annotations are checked

2. **Validation Layer**
   - `@NotBlank` - title is not blank ✓
   - `@Length(max=255)` - title length is 28 ✓
   - `@Length(max=1000)` - description length is 70 ✓
   - `@FutureOrPresent` - dueDate is 2026-02-15 (future) ✓
   - `@NotNull` - priority is HIGH ✓

3. **Mapping Layer** (TaskMapper)
   - Converts CreateTaskRequestDTO → CreateTaskRequest
   ```
   CreateTaskRequest(
     title = "Complete Spring Boot Tutorial",
     description = "Learn Spring Boot basics including REST APIs, JPA, and validation",
     dueDate = 2026-02-15,
     priority = HIGH
   )
   ```

4. **Service Layer** (TaskServiceImpl)
   - Creates Task entity with additional fields:
   ```
   Task(
     id = null,  // will be auto-generated
     title = "Complete Spring Boot Tutorial",
     description = "Learn Spring Boot basics including REST APIs, JPA, and validation",
     dueDate = 2026-02-15,
     status = OPEN,  // auto-set by service
     priority = HIGH,
     created = 2026-01-21T10:30:00Z,  // current timestamp
     updated = 2026-01-21T10:30:00Z   // current timestamp
   )
   ```

5. **Repository Layer** (TaskRepository)
   - JPA executes SQL:
   ```sql
   INSERT INTO tasks (id, title, description, due_date, status, priority, created, updated)
   VALUES (
     '550e8400-e29b-41d4-a716-446655440000',  -- UUID generated
     'Complete Spring Boot Tutorial',
     'Learn Spring Boot basics including REST APIs, JPA, and validation',
     '2026-02-15',
     'OPEN',
     'HIGH',
     '2026-01-21 10:30:00',
     '2026-01-21 10:30:00'
   );
   ```

6. **Response Mapping** (TaskMapper)
   - Converts Task entity → TaskDTO
   ```
   TaskDTO(
     id = 550e8400-e29b-41d4-a716-446655440000,
     title = "Complete Spring Boot Tutorial",
     description = "Learn Spring Boot basics including REST APIs, JPA, and validation",
     dueDate = 2026-02-15,
     priority = HIGH,
     status = OPEN
   )
   ```

7. **Controller Response**
   - Returns HTTP 201 Created with TaskDTO as JSON body

### Example Success Response:
```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Complete Spring Boot Tutorial",
  "description": "Learn Spring Boot basics including REST APIs, JPA, and validation",
  "dueDate": "2026-02-15",
  "priority": "HIGH",
  "status": "OPEN"
}
```

### Example Validation Error Response:
```http
POST http://localhost:8080/api/v1/tasks
Content-Type: application/json

{
  "title": "",
  "priority": "HIGH"
}
```

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "message": "Title must be between 1 - 255 Character"
}
```

## Technology Stack

- **Framework**: Spring Boot
- **ORM**: JPA (Java Persistence API)
- **Database**: PostgreSQL
- **Validation**: Jakarta Validation (Bean Validation)
- **Architecture Pattern**: Layered Architecture (Controller → Service → Repository)
- **Design Pattern**: Data Transfer Object (DTO), Mapper Pattern

## Summary

This Task Manager application follows a clean layered architecture with clear separation of concerns:

1. **Controller Layer**: Handles HTTP requests/responses
2. **Service Layer**: Contains business logic
3. **Repository Layer**: Manages data persistence
4. **Mapper Layer**: Transforms between DTOs and domain objects
5. **Exception Handling**: Centralized error management

Each layer has a specific responsibility, making the application maintainable, testable, and scalable.

---

## Diagram Rendering Notes

All Mermaid diagrams in this document have been optimized for rendering:
- ✅ Subgraphs use explicit IDs to avoid parsing issues
- ✅ Special characters like `@` symbols removed from link labels and node text (they cause LINK_ID parse errors)
- ✅ HTML breaks (`<br/>`) simplified in complex notes
- ✅ Long text in nodes has been simplified for better rendering
- ✅ All diagram types are tested: flowchart, sequenceDiagram, graph, classDiagram, erDiagram

**Common Mermaid Issues Fixed:**
- `@Valid` → `Validate` (@ symbol causes parse errors in edge labels)
- `@ControllerAdvice` → `ControllerAdvice` (@ symbol in node text)
- `@NotBlank, @Length` → `NotBlank, Length` (@ symbols in annotations)

**Tested with:** GitHub, GitLab, VS Code (Markdown Preview Mermaid Support), JetBrains IDEs (Mermaid plugin)

