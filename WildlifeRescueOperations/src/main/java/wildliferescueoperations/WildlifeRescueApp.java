package wildliferescueoperations;

import java.util.Scanner;

/**
 * Main console application.
 */
public class WildlifeRescueApp {

    private final Scanner scanner;
    private final RescueManager manager;

    public WildlifeRescueApp() {
        scanner = new Scanner(System.in);
        manager = new RescueManager();
    }

    public static void main(String[] args) {
        WildlifeRescueApp app = new WildlifeRescueApp();
        app.run();
    }

    public void run() {

        int option;

        do {

            displayMenu();
            option = readIntInRange("Select an option: ", 1, 6);

            switch (option) {

                case 1:
                    createRescueCase();
                    break;

                case 2:
                    searchRescueCase();
                    break;

                case 3:
                    updateRescueStatus();
                    break;

                case 4:
                    displayAllRescueCases();
                    break;

                case 5:
                    displayRescueReport();
                    break;

                case 6:
                    System.out.println("Exiting Wildlife Rescue Operations System.");
                    break;

                default:
                    break;
            }

        } while (option != 6);
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Create Rescue Case");
        System.out.println("2. Search Rescue Case");
        System.out.println("3. Update Rescue Status");
        System.out.println("4. Display All Rescue Cases");
        System.out.println("5. Rescue Report");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }

    private void createRescueCase() {

        System.out.println();
        System.out.println("--- CREATE RESCUE CASE ---");
        System.out.println("1. Injured Animal Rescue");
        System.out.println("2. Orphaned Animal Rescue");
        System.out.println("3. Endangered Species Rescue");

        int rescueType =
                readIntInRange("Select rescue type: ", 1, 3);

        String caseId;

        while (true) {

            caseId = readNonBlankString("Rescue Case ID: ");

            if (!manager.rescueCaseIdExists(caseId)) {
                break;
            }

            System.out.println(
                    "Error: Rescue Case ID already exists. Enter a unique ID.");
        }

        String animalName =
                readNonBlankString("Animal Name: ");

        String species =
                readNonBlankString("Species: ");

        String rescueLocation =
                readNonBlankString("Rescue Location: ");

        String assignedRanger =
                readNonBlankString("Assigned Ranger: ");

        int rescueDays =
                readPositiveInt("Number of Rescue Days: ");

        double dailyCareCost =
                readPositiveDouble("Daily Care Cost: R");

        RescueCase rescueCase;

        if (rescueType == 1) {

            String injuryDescription =
                    readNonBlankString("Injury Description: ");

            double veterinaryCost =
                    readPositiveDouble("Veterinary Treatment Cost: R");

            boolean surgeryRequired =
                    readYesNo("Surgery Required? (Y/N): ");

            rescueCase = new InjuredAnimalRescue(
                    caseId,
                    animalName,
                    species,
                    rescueLocation,
                    assignedRanger,
                    rescueDays,
                    dailyCareCost,
                    injuryDescription,
                    veterinaryCost,
                    surgeryRequired
            );

        } else if (rescueType == 2) {

            int estimatedAgeMonths =
                    readPositiveInt("Estimated Age (Months): ");

            double feedingCost =
                    readPositiveDouble("Feeding Cost: R");

            boolean fosterCareRequired =
                    readYesNo("Foster Care Required? (Y/N): ");

            rescueCase = new OrphanedAnimalRescue(
                    caseId,
                    animalName,
                    species,
                    rescueLocation,
                    assignedRanger,
                    rescueDays,
                    dailyCareCost,
                    estimatedAgeMonths,
                    feedingCost,
                    fosterCareRequired
            );

        } else {

            String classification =
                    readNonBlankString(
                            "Conservation Classification: ");

            double securityCost =
                    readPositiveDouble("Security Cost: R");

            boolean specialistTeamRequired =
                    readYesNo("Specialist Team Required? (Y/N): ");

            rescueCase = new EndangeredSpeciesRescue(
                    caseId,
                    animalName,
                    species,
                    rescueLocation,
                    assignedRanger,
                    rescueDays,
                    dailyCareCost,
                    classification,
                    securityCost,
                    specialistTeamRequired
            );
        }

        if (manager.addRescueCase(rescueCase)) {

            System.out.println();
            System.out.println("Rescue case created successfully.");
            System.out.println(rescueCase.generateRescueSummary());

        } else {

            System.out.println("Rescue case could not be created.");
        }
    }

    private void searchRescueCase() {

        System.out.println();
        System.out.println("--- SEARCH RESCUE CASE ---");

        String caseId =
                readNonBlankString("Enter Rescue Case ID: ");

        RescueCase rescueCase =
                manager.searchRescueCase(caseId);

        if (rescueCase == null) {

            System.out.println("Rescue case not found.");

        } else {

            System.out.println("Rescue case found:");
            rescueCase.displayDetails();
        }
    }

    private void updateRescueStatus() {

        System.out.println();
        System.out.println("--- UPDATE RESCUE STATUS ---");

        String caseId =
                readNonBlankString("Enter Rescue Case ID: ");

        RescueCase rescueCase =
                manager.searchRescueCase(caseId);

        if (rescueCase == null) {

            System.out.println("Rescue case not found.");
            return;
        }

        System.out.println("Current Status: "
                + rescueCase.getCurrentStatus());

        System.out.println("1. Start Rescue Operation");
        System.out.println("2. Place Under Observation");
        System.out.println("3. Complete Rescue Operation");
        System.out.println("4. Set to Pending");

        int choice =
                readIntInRange("Select new status: ", 1, 4);

        switch (choice) {

            case 1:
                rescueCase.startRescue();
                break;

            case 2:
                manager.updateRescueStatus(
                        caseId,
                        "Under Observation");
                break;

            case 3:
                rescueCase.completeRescue();
                break;

            case 4:
                manager.updateRescueStatus(
                        caseId,
                        "Pending");
                break;

            default:
                break;
        }

        System.out.println("Rescue status updated successfully.");
        System.out.println(rescueCase.generateRescueSummary());
    }

    private void displayAllRescueCases() {

        System.out.println();
        System.out.println("--- ALL RESCUE CASES ---");

        if (manager.getAllRescueCases().isEmpty()) {

            System.out.println(
                    "No rescue cases are currently stored.");

            return;
        }

        /*
         * Polymorphism:
         * each object may be a different child class,
         * but all are handled as RescueCase objects.
         */
        for (RescueCase rescueCase
                : manager.getAllRescueCases()) {

            rescueCase.displayDetails();
            System.out.println();
        }
    }

    private void displayRescueReport() {
        System.out.println();
        System.out.println(manager.generateRescueReport());
    }

    private String readNonBlankString(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Invalid input. This field cannot be blank.");
        }
    }

    private int readPositiveInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

            } catch (NumberFormatException e) {
                // Keep running and show the validation message below.
            }

            System.out.println(
                    "Invalid input. Enter a whole number greater than zero.");
        }
    }

    private double readPositiveDouble(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine().trim();

            try {

                double value = Double.parseDouble(input);

                if (value > 0) {
                    return value;
                }

            } catch (NumberFormatException e) {
                // Keep running and show the validation message below.
            }

            System.out.println(
                    "Invalid input. Enter a number greater than zero.");
        }
    }

    private int readIntInRange(String prompt,
                               int minimum,
                               int maximum) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(input);

                if (value >= minimum && value <= maximum) {
                    return value;
                }

            } catch (NumberFormatException e) {
                // Keep running and show the validation message below.
            }

            System.out.println(
                    "Invalid selection. Enter a number from "
                            + minimum + " to " + maximum + ".");
        }
    }

    private boolean readYesNo(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")
                    || input.equalsIgnoreCase("YES")) {

                return true;
            }

            if (input.equalsIgnoreCase("N")
                    || input.equalsIgnoreCase("NO")) {

                return false;
            }

            System.out.println(
                    "Invalid input. Please enter Y or N.");
        }
    }
}
