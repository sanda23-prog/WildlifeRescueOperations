package wildliferescueoperations;

/**
 * Rescue case for an endangered species.
 */
public class EndangeredSpeciesRescue extends RescueCase {

    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String rescueCaseId,
                                   String animalName,
                                   String species,
                                   String rescueLocation,
                                   String assignedRanger,
                                   int numberOfRescueDays,
                                   double dailyCareCost,
                                   String conservationClassification,
                                   double securityCost,
                                   boolean specialistTeamRequired) {

        super(rescueCaseId,
                animalName,
                species,
                rescueLocation,
                assignedRanger,
                numberOfRescueDays,
                dailyCareCost);

        if (conservationClassification == null
                || conservationClassification.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Conservation classification cannot be blank.");
        }

        if (securityCost <= 0) {
            throw new IllegalArgumentException(
                    "Security cost must be greater than zero.");
        }

        this.conservationClassification =
                conservationClassification.trim();

        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    /**
     * Basic care + security cost + R8 000 if specialist team is required.
     */
    @Override
    public double calculateTotalRescueCost() {

        double total = calculateBasicCareCost() + securityCost;

        if (specialistTeamRequired) {
            total += 8000.00;
        }

        return total;
    }

    @Override
    public String determineRescuePriority() {

        if (specialistTeamRequired) {
            return "Critical";
        }

        return "High";
    }

    @Override
    public String getRescueType() {
        return "Endangered Species Rescue";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Classification     : "
                + conservationClassification);
        System.out.println("Security Cost      : "
                + formatMoney(securityCost));
        System.out.println("Specialist Team    : "
                + (specialistTeamRequired ? "Required" : "Not Required"));
    }

    public String getConservationClassification() {
        return conservationClassification;
    }

    public double getSecurityCost() {
        return securityCost;
    }

    public boolean isSpecialistTeamRequired() {
        return specialistTeamRequired;
    }
}
