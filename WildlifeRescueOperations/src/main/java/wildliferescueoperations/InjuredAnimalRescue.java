package wildliferescueoperations;

/**
 * Rescue case for an injured animal.
 */
public class InjuredAnimalRescue extends RescueCase {

    private String injuryDescription;
    private double veterinaryTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String rescueCaseId,
                               String animalName,
                               String species,
                               String rescueLocation,
                               String assignedRanger,
                               int numberOfRescueDays,
                               double dailyCareCost,
                               String injuryDescription,
                               double veterinaryTreatmentCost,
                               boolean surgeryRequired) {

        super(rescueCaseId,
                animalName,
                species,
                rescueLocation,
                assignedRanger,
                numberOfRescueDays,
                dailyCareCost);

        if (injuryDescription == null || injuryDescription.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Injury description cannot be blank.");
        }

        if (veterinaryTreatmentCost <= 0) {
            throw new IllegalArgumentException(
                    "Veterinary treatment cost must be greater than zero.");
        }

        this.injuryDescription = injuryDescription.trim();
        this.veterinaryTreatmentCost = veterinaryTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    /**
     * Basic care + veterinary cost + R5 000 if surgery is required.
     */
    @Override
    public double calculateTotalRescueCost() {

        double total = calculateBasicCareCost() + veterinaryTreatmentCost;

        if (surgeryRequired) {
            total += 5000.00;
        }

        return total;
    }

    /**
     * The brief requires each rescue type to determine a priority
     * but does not provide a fixed formula in the supplied pages.
     * This rule uses the rescue-specific surgery requirement.
     */
    @Override
    public String determineRescuePriority() {

        if (surgeryRequired) {
            return "Critical";
        }

        return "High";
    }

    @Override
    public String getRescueType() {
        return "Injured Animal Rescue";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Injury Description: " + injuryDescription);
        System.out.println("Veterinary Cost    : "
                + formatMoney(veterinaryTreatmentCost));
        System.out.println("Surgery Required   : "
                + (surgeryRequired ? "Yes" : "No"));
    }

    public String getInjuryDescription() {
        return injuryDescription;
    }

    public double getVeterinaryTreatmentCost() {
        return veterinaryTreatmentCost;
    }

    public boolean isSurgeryRequired() {
        return surgeryRequired;
    }
}
