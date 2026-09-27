# Wildlife Rescue Operations System

Complete Java console application for the Wildlife SA practical assignment.

## What is included

The project includes:

- Rescue case creation
- Search by Rescue Case ID
- Rescue status updates
- Display all rescue cases
- Rescue report
- `ArrayList<RescueCase>` storage
- Injured Animal Rescue
- Orphaned Animal Rescue
- Endangered Species Rescue
- Rescue cost calculations
- Rescue priority calculations
- Input validation
- Unique Rescue Case IDs
- JUnit 5 tests
- Inheritance
- Abstraction
- Interfaces
- Encapsulation
- Method overriding
- Polymorphism

## Easiest way to open in NetBeans

This project uses **Maven**, which makes JUnit much easier.

1. Extract the ZIP file.
2. Open Apache NetBeans.
3. Click **File > Open Project**.
4. Select the folder named `WildlifeRescueOperations`.
5. Click **Open Project**.
6. Wait for NetBeans/Maven to load the project.
7. Expand:
   `Source Packages > wildliferescueoperations`
8. Right-click `WildlifeRescueApp.java`.
9. Click **Run File**.

The menu should display:

    ========================================
    WILDLIFE RESCUE OPERATIONS SYSTEM
    ========================================
    1. Create Rescue Case
    2. Search Rescue Case
    3. Update Rescue Status
    4. Display All Rescue Cases
    5. Rescue Report
    6. Exit
    ========================================

## Run the JUnit tests

In NetBeans:

1. Expand **Test Packages**.
2. Open `wildliferescueoperations`.
3. Right-click `RescueSystemTest.java`.
4. Select **Test File**.

You can also right-click the whole project and choose **Test**.

The test class verifies the exact core areas requested by the assignment:

1. Rescue cost calculations
2. Rescue priority calculations
3. Rescue status updates
4. Searching for an existing rescue case
5. Preventing duplicate Rescue Case IDs

Additional tests check validation and report totals.

## Suggested demonstration data

### Injured rescue

    Case ID: WR101
    Animal Name: Duma
    Species: African Elephant
    Location: Kruger National Park
    Ranger: Ranger Molefe
    Rescue Days: 10
    Daily Care Cost: 1000
    Injury: Leg injury
    Veterinary Cost: 5500
    Surgery Required: Y

Expected total:

    R20 500.00

Calculation:

    10 x R1 000 = R10 000
    Veterinary treatment = R5 500
    Surgery = R5 000
    Total = R20 500

### Orphaned rescue

    Case ID: WR102
    Animal Name: Nandi
    Species: White Rhino
    Location: Hluhluwe
    Ranger: Ranger Dlamini
    Rescue Days: 10
    Daily Care Cost: 800
    Estimated Age: 12
    Feeding Cost: 1000
    Foster Care Required: Y

Expected total:

    R11 500.00

Combined report total for WR101 and WR102:

    Total Rescue Cases : 2
    Total Rescue Cost  : R32 000.00

## Rubric coverage

### Rescue Case Management — 20 marks

`RescueManager` and `WildlifeRescueApp` support:

- create
- search
- update rescue status
- display all rescue cases
- multiple cases stored in an `ArrayList`

### Inheritance and Rescue Case Types — 30 marks

`RescueCase` is the abstract superclass.

Subclasses:

- `InjuredAnimalRescue`
- `OrphanedAnimalRescue`
- `EndangeredSpeciesRescue`

Each subclass has its own additional data, cost calculation, priority calculation and display behaviour.

### Rescue Operations / Interface / Polymorphism — 15 marks

`RescueOperations` defines:

- `startRescue()`
- `completeRescue()`
- `generateRescueSummary()`

Polymorphism is demonstrated by storing all child objects as `RescueCase` objects inside:

    ArrayList<RescueCase>

The report and display loops call overridden methods through the parent type.

### Reports and Validation — 15 marks

Reports include:

- Rescue Case ID
- Rescue Type
- Species
- Rescue Location
- Assigned Ranger
- Rescue Priority
- Current Status
- Total Rescue Cost
- Total number of rescue cases
- Total estimated rescue cost

Validation checks:

- Rescue Case ID cannot be blank
- Rescue Case ID must be unique
- Species cannot be blank
- Rescue location cannot be blank
- Assigned ranger cannot be blank
- Numeric inputs must be greater than zero
- Menu choices must be valid
- Invalid console input does not crash the application

### User Interface and Program Execution — 10 marks

The application has a clear six-option console menu and continues running until Exit is selected.

### JUnit Testing — 10 marks

`RescueSystemTest.java` contains JUnit 5 tests for the required functionality and additional validation.

## Important priority note

The supplied assignment pages state that each rescue type must determine an appropriate rescue priority, but the supplied pages do not provide an exact priority formula.

This solution therefore uses these clear rules:

- Injured + surgery required = **Critical**
- Injured without surgery = **High**
- Orphaned, 3 months or younger and foster care required = **Critical**
- Orphaned, 12 months or younger OR foster care required = **High**
- Other orphaned cases = **Medium**
- Endangered + specialist team required = **Critical**
- Other endangered cases = **High**

If your lecturer provides a separate fixed priority formula, only the three `determineRescuePriority()` methods need to be changed.

## Final submission check

Before submitting:

- Run the application.
- Create all three rescue types.
- Search for a case.
- Update a status.
- Display all cases.
- Generate the report.
- Test invalid input.
- Run all JUnit tests.
- Make sure the NetBeans output shows no compilation errors.
