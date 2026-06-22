# Quantity Measurement App

## Application Purpose
The **Quantity Measurement Application** is a robust backend system designed to programmatically compare, convert, and manipulate quantities of various measurements—such as Length, Weight, Volume, and Temperature. It ensures accurate mathematical scaling between different unit scales, serving as a unified calculation engine. Over time, it evolved from a simple Java Library into a fully enterprise-grade **Spring Boot REST API** backed by an **H2 Database** and strict N-Tier architecture principles.

## Use Cases (UC) History

Below is the chronological journey of the project, detailing the purpose of every Use Case (UC) implemented:

### Core Calculations & Length
- **UC1 (Feet Equality):** Established the base architecture to check the equality of two numerical values measured in Feet.
- **UC2 (Inches Equality):** Extended the system to independently verify the equality of measurements in Inches.
- **UC3 (Generic Equality):** Introduced conversion metrics to accurately compare Feet against Inches (e.g., 1 Foot == 12 Inches).
- **UC4 (Extended Units):** Added Support for Yards and Centimeters (CM) to broaden the scope of Length measurements.
- **UC5 (Conversion):** Formalized conversion factors and base-unit conversion logic to unify math across all Length types.

### Arithmetic Operations
- **UC6 (Addition):** Implemented the logic to add two different Length units together (e.g., 2 Inches + 2 CM).
- **UC7 (Addition with Target):** Upgraded addition to explicitly return the final result in a user-requested Target Unit.

### Architecture Refactoring
- **UC8 (Standalone Enum):** Refactored the architecture by extracting measurement types into standalone enums for cleaner code management.

### Expanding Categories
- **UC9 (Weight):** Introduced a completely new category: Weight, supporting Kilograms, Grams, and Pounds.
- **UC10 (Generic Weights):** Upgraded the core logic so the system could generically handle both Length and Weight cleanly without logical collisions.
- **UC11 (Volume):** Introduced Volume units (Litres, Millilitres, Gallons) using the generic framework.
- **UC12 (Subtraction & Division):** Rounded out the arithmetic engine by adding Subtraction and Division capabilities.
- **UC13 (DRY Arithmetic):** Refactored the arithmetic logic to strictly adhere to the DRY (Don't Repeat Yourself) principle.
- **UC14 (Temperature):** Introduced Temperature metrics (Celsius, Fahrenheit) with specialized scaling equations since temperature is non-linear compared to other units.

### Enterprise Modernization
- **UC15 (N-Tier Architecture):** Transitioned the project from a flat structure into an Enterprise N-Tier Architecture, separating logic into `Controller`, `Service`, and `Repository` layers.
- **UC16 (Database Integration):** Integrated an H2 database utilizing manual JDBC Connection Pooling, storing all calculation histories persistently.
- **UC17 (Spring Boot & Mockito):** The final architectural leap. Migrated the entire application to **Spring Boot**, converting controllers to REST APIs (`@RestController`), swapping manual JDBC for **Spring Data JPA**, and implementing completely isolated testing layers using **Mockito**.

---

## Running the Application

**Build and Run Tests:**
```bash
mvn clean test
```

**Run the Spring Boot Server:**
```bash
mvn spring-boot:run
```

Once running, the in-memory H2 Database Console can be accessed locally in your browser at:
`http://localhost:8080/h2-console`
*(JDBC URL: `jdbc:h2:mem:quantity_db`)*
