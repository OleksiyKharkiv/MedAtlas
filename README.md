
# MedAtlas

   MedAtlas is an interactive Medical Atlas designed for doctors, researchers, and medical students. It allows for the use
and extension of a core library of data that contains a cataloged database of anatomical structures, which are tied to
research subjects. Researches can be divided into series of studies, allowing for the exploration of anatomical
structures over time and tracking changes in their history. The `InstanceData` feature enables users to mark study
points and store their genesis, facilitating in-depth analysis of anatomical structures in dynamic contexts.

## Features
```markdown
- **Anatomical Structure Catalog**: A comprehensive and cataloged database of anatomical structures.
- **Research Subjects**: Research subjects are linked to anatomical structures, enabling detailed study.
- **Study Series**: Researches can be split into series of studies, allowing for tracking of changes and dynamics in
  anatomical structures over time.
- **Instance Data**: Users can mark research points and store the history (genesis) of their findings, enhancing the
  study process.
- **Interactive Interface**: Designed for researchers and medical professionals to easily navigate and use the data for
  their work.
```
## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/medatlas.git
   ```

2. Navigate to the project directory:
   ```bash
   cd medatlas
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   .mvn spring-boot:run
   ```

## API Endpoints

### 1. Get All Anatomical Structures

**GET** `/api/anatomical-structures`

Returns a list of all anatomical structures in the database.

**Response**:

```json
[
  {
    "id": 1,
    "name": "Brain",
    "category": "Organ",
    "description": "The brain is the central organ of the nervous system."
  },
  {
    "id": 2,
    "name": "Heart",
    "category": "Organ",
    "description": "The heart is a muscular organ that pumps blood through the circulatory system."
  }
]
```

### 2. Get All Research Subjects

**GET** `/api/research-subjects`

Returns a list of all research subjects tied to anatomical structures.

**Response**:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "age": 30,
    "sex": "Male",
    "anatomicalStructureId": 1
  }
]
```

### 3. Create a New Research Record

**POST** `/api/research`

Creates a new research record for a specific anatomical structure.

**Request**:

```json
{
  "researchSubjectId": 1,
  "studySeries": "Neuroplasticity Study",
  "date": "2024-12-01",
  "findings": "Detailed findings of the study"
}
```

**Response**:

```json
{
  "id": 1,
  "researchSubjectId": 1,
  "studySeries": "Neuroplasticity Study",
  "date": "2024-12-01",
  "findings": "Detailed findings of the study"
}
```

### 4. Get Research by ID

**GET** `/api/research/{id}`

Returns detailed information about a specific research study.

**Response**:

```json
{
  "id": 1,
  "researchSubjectId": 1,
  "studySeries": "Neuroplasticity Study",
  "date": "2024-12-01",
  "findings": "Detailed findings of the study"
}
```

### 5. Update Research Record

**PATCH** `/api/research/{id}`

Updates an existing research record with new findings or changes.

**Request**:

```json
{
  "findings": "Updated findings with new information."
}
```

**Response**:

```json
{
  "id": 1,
  "researchSubjectId": 1,
  "studySeries": "Neuroplasticity Study",
  "date": "2024-12-01",
  "findings": "Updated findings with new information."
}
```

### 6. Create or Mark Instance Data

**POST** `/api/instance-data`

Marks specific research points in an anatomical structure and stores their genesis.

**Request**:

```json
{
  "researchId": 1,
  "point": "Point A",
  "genesis": "Initial observations of the structure in its current state."
}
```

**Response**:

```json
{
  "id": 1,
  "researchId": 1,
  "point": "Point A",
  "genesis": "Initial observations of the structure in its current state."
}
```

### 7. Get All Instance Data Points

**GET** `/api/instance-data`

Returns a list of all instance data points in the system.

**Response**:

```json
[
  {
    "id": 1,
    "researchId": 1,
    "point": "Point A",
    "genesis": "Initial observations of the structure in its current state."
  }
]
```

### 8. Delete Research Record

**DELETE** `/api/research/{id}`

Deletes a specific research record from the database.

**Response**:

```json
{
  "message": "Research record deleted successfully"
}
```

## Technologies

- **Spring Boot** — for backend development.
- **Java** — main programming language.
- **PostgreSQL** — for data storage.
- **JPA** — for database interaction.
- **Maven** — for dependency management.
