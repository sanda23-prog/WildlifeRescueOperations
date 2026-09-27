package wildliferescueoperations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests covering the core functionality required by the rubric.
 */
public class RescueSystemTest {

    private RescueManager manager;

    @BeforeEach
    public void setUp() {
        manager = new RescueManager();
    }

    // -------------------------------------------------
    // 1. RESCUE COST CALCULATIONS
    // -------------------------------------------------

    @Test
    public void testInjuredAnimalCostWithSurgery() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR101",
                        "Duma",
                        "African Elephant",
                        "Kruger National Park",
                        "Ranger Molefe",
                        10,
                        1000,
                        "Leg injury",
                        5500,
                        true
                );

        // 10 x 1000 + 5500 + 5000 = 20500
        assertEquals(
                20500.00,
                rescue.calculateTotalRescueCost(),
                0.01
        );
    }

    @Test
    public void testOrphanedAnimalCostWithFosterCare() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "WR102",
                        "Nandi",
                        "White Rhino",
                        "Hluhluwe",
                        "Ranger Dlamini",
                        10,
                        800,
                        12,
                        1000,
                        true
                );

        // 10 x 800 + 1000 + 2500 = 11500
        assertEquals(
                11500.00,
                rescue.calculateTotalRescueCost(),
                0.01
        );
    }

    @Test
    public void testEndangeredSpeciesCostWithSpecialistTeam() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "WR103",
                        "Khosi",
                        "African Wild Dog",
                        "Kruger National Park",
                        "Ranger Nkosi",
                        5,
                        1000,
                        "Endangered",
                        2000,
                        true
                );

        // 5 x 1000 + 2000 + 8000 = 15000
        assertEquals(
                15000.00,
                rescue.calculateTotalRescueCost(),
                0.01
        );
    }

    // -------------------------------------------------
    // 2. RESCUE PRIORITY CALCULATIONS
    // -------------------------------------------------

    @Test
    public void testInjuredAnimalPriority() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR104",
                        "Leo",
                        "Lion",
                        "Kruger",
                        "Ranger John",
                        5,
                        500,
                        "Broken leg",
                        6000,
                        true
                );

        assertEquals(
                "Critical",
                rescue.determineRescuePriority()
        );
    }

    @Test
    public void testOrphanedAnimalPriority() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "WR105",
                        "Baby Rhino",
                        "White Rhino",
                        "Hluhluwe",
                        "Ranger Thabo",
                        4,
                        500,
                        8,
                        1500,
                        true
                );

        assertEquals(
                "High",
                rescue.determineRescuePriority()
        );
    }

    @Test
    public void testEndangeredSpeciesPriority() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "WR106",
                        "Wild Dog",
                        "African Wild Dog",
                        "Kruger",
                        "Ranger James",
                        6,
                        700,
                        "Endangered",
                        3000,
                        true
                );

        assertEquals(
                "Critical",
                rescue.determineRescuePriority()
        );
    }

    // -------------------------------------------------
    // 3. RESCUE STATUS UPDATES
    // -------------------------------------------------

    @Test
    public void testRescueStatusUpdates() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR107",
                        "Elephant",
                        "African Elephant",
                        "Kruger",
                        "Ranger David",
                        7,
                        1000,
                        "Foot injury",
                        3000,
                        false
                );

        assertEquals(
                "Pending",
                rescue.getCurrentStatus()
        );

        rescue.startRescue();

        assertEquals(
                "Rescue in Progress",
                rescue.getCurrentStatus()
        );

        rescue.completeRescue();

        assertEquals(
                "Rescue Completed",
                rescue.getCurrentStatus()
        );
    }

    // -------------------------------------------------
    // 4. SEARCH EXISTING RESCUE CASE
    // -------------------------------------------------

    @Test
    public void testSearchExistingRescueCase() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR108",
                        "Lion",
                        "African Lion",
                        "Kruger",
                        "Ranger Peter",
                        4,
                        500,
                        "Minor injury",
                        2000,
                        false
                );

        manager.addRescueCase(rescue);

        RescueCase result =
                manager.searchRescueCase("WR108");

        assertNotNull(result);
        assertEquals(
                "WR108",
                result.getRescueCaseId()
        );
    }

    @Test
    public void testSearchMissingRescueCaseReturnsNull() {

        assertNull(
                manager.searchRescueCase("WR999")
        );
    }

    // -------------------------------------------------
    // 5. PREVENT DUPLICATE RESCUE CASE IDs
    // -------------------------------------------------

    @Test
    public void testDuplicateRescueCaseIdsArePrevented() {

        InjuredAnimalRescue rescue1 =
                new InjuredAnimalRescue(
                        "WR109",
                        "Lion One",
                        "Lion",
                        "Kruger",
                        "Ranger A",
                        2,
                        500,
                        "Foot injury",
                        1000,
                        false
                );

        OrphanedAnimalRescue rescue2 =
                new OrphanedAnimalRescue(
                        "WR109",
                        "Rhino One",
                        "Rhino",
                        "Hluhluwe",
                        "Ranger B",
                        3,
                        600,
                        10,
                        1000,
                        true
                );

        assertTrue(manager.addRescueCase(rescue1));
        assertFalse(manager.addRescueCase(rescue2));
        assertEquals(1, manager.getTotalRescueCases());
    }

    // -------------------------------------------------
    // ADDITIONAL VALIDATION TESTS
    // -------------------------------------------------

    @Test
    public void testBlankRescueCaseIdRejected() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "",
                        "Lion",
                        "Lion",
                        "Kruger",
                        "Ranger A",
                        5,
                        500,
                        "Leg injury",
                        1000,
                        false
                )
        );
    }

    @Test
    public void testZeroRescueDaysRejected() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "WR110",
                        "Lion",
                        "Lion",
                        "Kruger",
                        "Ranger A",
                        0,
                        500,
                        "Leg injury",
                        1000,
                        false
                )
        );
    }

    @Test
    public void testReportTotals() {

        InjuredAnimalRescue injured =
                new InjuredAnimalRescue(
                        "WR111",
                        "Duma",
                        "African Elephant",
                        "Kruger",
                        "Ranger A",
                        10,
                        1000,
                        "Leg injury",
                        5500,
                        true
                );

        OrphanedAnimalRescue orphaned =
                new OrphanedAnimalRescue(
                        "WR112",
                        "Nandi",
                        "White Rhino",
                        "Hluhluwe",
                        "Ranger B",
                        10,
                        800,
                        12,
                        1000,
                        true
                );

        manager.addRescueCase(injured);
        manager.addRescueCase(orphaned);

        assertEquals(
                2,
                manager.getTotalRescueCases()
        );

        assertEquals(
                32000.00,
                manager.getTotalEstimatedRescueCost(),
                0.01
        );
    }
}
