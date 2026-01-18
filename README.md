# Hotel Reservation System

A comprehensive, UML-compliant Hotel Reservation System implementation in Java, demonstrating clean code principles, defensive programming, and professional software architecture.

## Project Overview

This system implements a complete hotel chain management solution with support for:
- Multiple hotels within a hotel chain
- Various room types and pricing
- Guest reservations and check-in/check-out operations
- Comprehensive validation and error handling
- Full unit test coverage

## Architecture

### Domain Model

The system follows strict UML design with the following core classes:

#### Main Domain Classes
- **HotelChain**: Central coordinator for all hotel operations
- **Hotel**: Manages multiple rooms and reservations
- **Room**: Represents individual hotel rooms with occupancy tracking
- **Guest**: Represents hotel guests with personal information
- **Reservation**: Stores booking details (dates, guest count)
- **ReserverPayer**: Payment information for reservations

#### Value Objects
- **Name**: Guest and hotel names
- **Address**: Guest address information
- **Money**: Monetary values with currency
- **CreditCard**: Payment card details
- **Identity**: Identity document information
- **RoomType**: Room classification and pricing
- **HowMany**: Quantity value object
- **RoomKind**: Enum (SINGLE, DOUBLE, SUITE)

### Key Features

1. **Defensive Programming**
   - Null validation on all constructor parameters
   - State validation before operations
   - Clear, descriptive error messages
   - IllegalArgumentException for invalid input
   - IllegalStateException for invalid operations

2. **Clean Code**
   - Single responsibility per class
   - Meaningful, descriptive names
   - Small, focused methods
   - No code duplication
   - Clear separation of concerns

3. **Object-Oriented Design**
   - Proper use of encapsulation
   - Composition over inheritance
   - Value objects for data integrity
   - Collection management with maps

4. **Validation Pattern**
   - Each operation has a corresponding `canXxx()` validation method
   - Validation occurs before state modification
   - Clear error messages guide users

## Project Structure

```
src/
├── main/
│   └── java/
│       ├── Main.java                           # Demo application
│       └── domain/
│           ├── HotelChain.java                # Chain coordinator
│           ├── Hotel.java                     # Hotel manager
│           ├── Room.java                      # Room with occupancy
│           ├── Guest.java                     # Guest entity
│           ├── Reservation.java               # Reservation entity
│           ├── ReserverPayer.java             # Payer information
│           ├── RoomType.java                  # Room classification
│           ├── RoomKind.java                  # Enum: SINGLE, DOUBLE, SUITE
│           ├── Name.java                      # Value object
│           ├── Address.java                   # Value object
│           ├── Money.java                     # Value object
│           ├── CreditCard.java                # Value object
│           ├── Identity.java                  # Value object
│           └── HowMany.java                   # Value object
└── test/
    └── java/
        └── domain/
            ├── HotelChainTest.java
            ├── HotelTest.java
            ├── RoomTest.java
            ├── ReservationTest.java
            ├── NameTest.java
            ├── MoneyTest.java
            └── GuestTest.java
```

## Building the Project

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Build Command
```bash
mvn clean package
```

### Compile Without Tests
```bash
mvn clean compile
```

## Running the Application

### Execute Main Demonstration
```bash
mvn exec:java -Dexec.mainClass="Main"
```

Or compile and run:
```bash
javac -d target/classes src/main/java/Main.java src/main/java/domain/*.java
java -cp target/classes Main
```

## Running Tests

### Execute All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=HotelChainTest
```

### Generate Test Report
```bash
mvn surefire-report:report
```

## Test Coverage

The project includes comprehensive unit tests for all domain classes:

### Test Classes
- **HotelChainTest**: Tests chain operations, reservations, check-in/out
- **HotelTest**: Tests hotel management and room availability
- **RoomTest**: Tests room occupancy, reservations, check-in/out
- **ReservationTest**: Tests reservation validation and date logic
- **NameTest**: Tests name value object
- **MoneyTest**: Tests monetary value object
- **GuestTest**: Tests guest entity

### Test Approach

All tests follow the AAA (Arrange-Act-Assert) pattern:
1. **Arrange**: Set up test data and preconditions
2. **Act**: Execute the operation under test
3. **Assert**: Verify the expected outcome

### Test Categories

- **Normal Cases**: Standard, valid operations
- **Boundary Cases**: Edge cases and limit conditions
- **Invalid Cases**: Null inputs, invalid states, error conditions
- **Parameterized Tests**: Multiple input variations

## Use Cases Demonstrated

The `Main.java` class demonstrates all key operations:

1. **Create Hotel Chain** - Initialize the system
2. **Create Hotels** - Add multiple hotels to the chain
3. **Create Room Types** - Define SINGLE, DOUBLE, and SUITE room types
4. **Add Rooms** - Add rooms with types to hotels
5. **Create Guests** - Register guests with personal information
6. **Create Payers** - Set up payment information
7. **Make Reservations** - Book rooms for guests
8. **Check In Guests** - Register guest arrival
9. **Check Availability** - Verify room status
10. **Check Out Guests** - Process guest departure
11. **Cancel Reservations** - Remove bookings
12. **System Status** - Display final hotel occupancy

## Code Quality Standards

### Naming Conventions
- Classes: PascalCase (e.g., `HotelChain`)
- Methods: camelCase (e.g., `makeReservation`)
- Constants: UPPER_SNAKE_CASE (e.g., `SINGLE`)
- Variables: camelCase (e.g., `guestName`)

### Method Organization
1. Constructor(s)
2. Public accessor methods
3. Public business methods
4. Public utility methods
5. Private validation methods
6. Private helper methods

### Documentation
- Javadoc comments on all public classes and methods
- Clear, concise method descriptions
- Parameter and return value documentation
- Exception documentation

## Error Handling

The system uses exceptions strategically:

- **IllegalArgumentException**: Invalid constructor parameters
- **IllegalStateException**: Invalid object state or operation
- **Clear Error Messages**: Every exception includes a descriptive message

Example:
```java
if (name == null || name.trim().isEmpty()) {
    throw new IllegalArgumentException("HotelChain name cannot be null or empty");
}
```

## Git Usage

This project demonstrates professional git practices:
- Meaningful commit messages
- Logical, incremental commits
- Clear separation between features
- Commits on different dates showing progression

## Future Enhancements

While maintaining the UML design:
1. Add payment processing
2. Implement billing and invoicing
3. Add guest feedback/ratings
4. Implement room maintenance scheduling
5. Add reporting and analytics

## Important Notes

### UML Compliance
- All classes, attributes, and methods follow the provided UML diagram exactly
- Multiplicities (0..1, 1..*) are respected in implementation
- Access modifiers match the UML specification
- Associations and compositions are properly implemented

### Beginner-Friendly
- No advanced patterns (streams, lambdas, builders)
- Clear, understandable code structure
- Comprehensive comments and documentation
- Simple, direct control flow

### Testing Standards
- JUnit 5 framework
- Parameterized tests for multiple scenarios
- Clear test names describing what is tested
- AAA pattern for all test methods

## Author

**Course**: Software Construction (CCP)
**Institution**: FAST NUCES
**Program**: BS/BE Software Engineering

## License

This is an educational project created for learning purposes.

---

For questions or issues, refer to the inline code documentation and test cases for usage examples.
