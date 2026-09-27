package wildliferescueoperations;

/**
 * Abstract parent class containing the information shared
 * by every wildlife rescue case.
 *
 * OOP demonstrated here:
 * - abstraction
 * - encapsulation
 * - interface implementation
 */
public abstract class RescueCase implements RescueOperations {

    private final String rescueCaseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfRescueDays;
    private double dailyCareCost;
    private String currentStatus;

    public RescueCase(String rescueCaseId,
                      String animalName,
                      String species,
                      String rescueLocation,
                      String assignedRanger,
                      int numberOfRescueDays,
                      double dailyCareCost) {

        this.rescueCaseId = validateText(rescueCaseId, "Rescue Case ID");
        this.animalName = validateText(animalName, "Animal name");
        this.species = validateText(species, "Species");
        this.rescueLocation = validateText(rescueLocation, "Rescue location");
        this.assignedRanger = validateText(assignedRanger, "Assigned ranger");

        if (numberOfRescueDays <= 0) {
            throw new IllegalArgumentException(
                    "Number of rescue days must be greater than zero.");
        }

        if (dailyCareCost <= 0) {
            throw new IllegalArgumentException(
                    "Daily care cost must be greater than zero.");
        }

        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentStatus = "Pending";
    }

    /**
     * Each child class provides its own calculation.
     */
    public abstract double calculateTotalRescueCost();

    /**
     * Each child class provides an appropriate priority.
     */
    public abstract String determineRescuePriority();

    /**
     * Returns a readable rescue type.
     */
    public abstract String getRescueType();

    public double calculateBasicCareCost() {
        return numberOfRescueDays * dailyCareCost;
    }

    @Override
    public void startRescue() {
        currentStatus = "Rescue in Progress";
    }

    @Override
    public void completeRescue() {
        currentStatus = "Rescue Completed";
    }

    @Override
    public String generateRescueSummary() {

        return "========================================\n"
                + "RESCUE SUMMARY\n"
                + "========================================\n"
                + "Rescue Case ID : " + rescueCaseId + "\n"
                + "Rescue Type    : " + getRescueType() + "\n"
                + "Species        : " + species + "\n"
                + "Assigned Ranger: " + assignedRanger + "\n"
                + "Rescue Priority: " + determineRescuePriority() + "\n"
                + "Current Status : " + currentStatus + "\n"
                + "Total Cost     : " + formatMoney(calculateTotalRescueCost()) + "\n";
    }

    /**
     * Shared display method. Child classes override this method
     * and then add their rescue-specific information.
     */
    public void displayDetails() {

        System.out.println("----------------------------------------");
        System.out.println("Case ID          : " + rescueCaseId);
        System.out.println("Type             : " + getRescueType());
        System.out.println("Animal Name      : " + animalName);
        System.out.println("Species          : " + species);
        System.out.println("Rescue Location  : " + rescueLocation);
        System.out.println("Assigned Ranger  : " + assignedRanger);
        System.out.println("Rescue Days      : " + numberOfRescueDays);
        System.out.println("Daily Care Cost  : " + formatMoney(dailyCareCost));
        System.out.println("Priority         : " + determineRescuePriority());
        System.out.println("Status           : " + currentStatus);
        System.out.println("Total Cost       : " + formatMoney(calculateTotalRescueCost()));
    }

    public static String formatMoney(double amount) {
        return "R" + String.format("%,.2f", amount).replace(',', ' ');
    }

    private static String validateText(String value, String fieldName) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }

        return value.trim();
    }

    public String getRescueCaseId() {
        return rescueCaseId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getSpecies() {
        return species;
    }

    public String getRescueLocation() {
        return rescueLocation;
    }

    public String getAssignedRanger() {
        return assignedRanger;
    }

    public int getNumberOfRescueDays() {
        return numberOfRescueDays;
    }

    public double getDailyCareCost() {
        return dailyCareCost;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setAnimalName(String animalName) {
        this.animalName = validateText(animalName, "Animal name");
    }

    public void setSpecies(String species) {
        this.species = validateText(species, "Species");
    }

    public void setRescueLocation(String rescueLocation) {
        this.rescueLocation = validateText(rescueLocation, "Rescue location");
    }

    public void setAssignedRanger(String assignedRanger) {
        this.assignedRanger = validateText(assignedRanger, "Assigned ranger");
    }

    public void setNumberOfRescueDays(int numberOfRescueDays) {

        if (numberOfRescueDays <= 0) {
            throw new IllegalArgumentException(
                    "Number of rescue days must be greater than zero.");
        }

        this.numberOfRescueDays = numberOfRescueDays;
    }

    public void setDailyCareCost(double dailyCareCost) {

        if (dailyCareCost <= 0) {
            throw new IllegalArgumentException(
                    "Daily care cost must be greater than zero.");
        }

        this.dailyCareCost = dailyCareCost;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = validateText(currentStatus, "Current status");
    }
}
