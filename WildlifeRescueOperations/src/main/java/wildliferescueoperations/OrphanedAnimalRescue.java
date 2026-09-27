package wildliferescueoperations;

/**
 * Rescue case for an orphaned animal.
 */
public class OrphanedAnimalRescue extends RescueCase {

    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String rescueCaseId,
                                String animalName,
                                String species,
                                String rescueLocation,
                                String assignedRanger,
                                int numberOfRescueDays,
                                double dailyCareCost,
                                int estimatedAgeMonths,
                                double feedingCost,
                                boolean fosterCareRequired) {

        super(rescueCaseId,
                animalName,
                species,
                rescueLocation,
                assignedRanger,
                numberOfRescueDays,
                dailyCareCost);

        if (estimatedAgeMonths <= 0) {
            throw new IllegalArgumentException(
                    "Estimated age must be greater than zero.");
        }

        if (feedingCost <= 0) {
            throw new IllegalArgumentException(
                    "Feeding cost must be greater than zero.");
        }

        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    /**
     * Basic care + feeding cost + R2 500 if foster care is required.
     */
    @Override
    public double calculateTotalRescueCost() {

        double total = calculateBasicCareCost() + feedingCost;

        if (fosterCareRequired) {
            total += 2500.00;
        }

        return total;
    }

    @Override
    public String determineRescuePriority() {

        if (estimatedAgeMonths <= 3 && fosterCareRequired) {
            return "Critical";
        }

        if (estimatedAgeMonths <= 12 || fosterCareRequired) {
            return "High";
        }

        return "Medium";
    }

    @Override
    public String getRescueType() {
        return "Orphaned Animal Rescue";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Estimated Age      : "
                + estimatedAgeMonths + " months");
        System.out.println("Feeding Cost       : " + formatMoney(feedingCost));
        System.out.println("Foster Care        : "
                + (fosterCareRequired ? "Required" : "Not Required"));
    }

    public int getEstimatedAgeMonths() {
        return estimatedAgeMonths;
    }

    public double getFeedingCost() {
        return feedingCost;
    }

    public boolean isFosterCareRequired() {
        return fosterCareRequired;
    }
}
