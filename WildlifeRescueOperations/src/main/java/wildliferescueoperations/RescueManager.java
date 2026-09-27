package wildliferescueoperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all rescue cases.
 *
 * The assignment specifically requires rescue cases
 * to be stored in an ArrayList.
 */
public class RescueManager {

    private final ArrayList<RescueCase> rescueCases;

    public RescueManager() {
        rescueCases = new ArrayList<>();
    }

    /**
     * Adds a case only when its Rescue Case ID is unique.
     */
    public boolean addRescueCase(RescueCase rescueCase) {

        if (rescueCase == null) {
            return false;
        }

        if (rescueCaseIdExists(rescueCase.getRescueCaseId())) {
            return false;
        }

        rescueCases.add(rescueCase);
        return true;
    }

    /**
     * Searches by Rescue Case ID.
     */
    public RescueCase searchRescueCase(String rescueCaseId) {

        if (rescueCaseId == null) {
            return null;
        }

        for (RescueCase rescueCase : rescueCases) {

            if (rescueCase.getRescueCaseId()
                    .equalsIgnoreCase(rescueCaseId.trim())) {

                return rescueCase;
            }
        }

        return null;
    }

    public boolean rescueCaseIdExists(String rescueCaseId) {
        return searchRescueCase(rescueCaseId) != null;
    }

    /**
     * Updates a case status when the case exists.
     */
    public boolean updateRescueStatus(String rescueCaseId,
                                      String newStatus) {

        RescueCase rescueCase = searchRescueCase(rescueCaseId);

        if (rescueCase == null) {
            return false;
        }

        rescueCase.setCurrentStatus(newStatus);
        return true;
    }

    public List<RescueCase> getAllRescueCases() {
        return new ArrayList<>(rescueCases);
    }

    public int getTotalRescueCases() {
        return rescueCases.size();
    }

    /**
     * Polymorphism:
     * every object is referenced as RescueCase but Java runs
     * the correct overridden cost calculation for its real type.
     */
    public double getTotalEstimatedRescueCost() {

        double total = 0;

        for (RescueCase rescueCase : rescueCases) {
            total += rescueCase.calculateTotalRescueCost();
        }

        return total;
    }

    /**
     * Generates the full report required by the assignment.
     */
    public String generateRescueReport() {

        StringBuilder report = new StringBuilder();

        report.append("============================================================\n");
        report.append("WILDLIFE RESCUE REPORT\n");
        report.append("============================================================\n");

        if (rescueCases.isEmpty()) {
            report.append("No rescue cases are currently stored.\n");
        }

        for (RescueCase rescueCase : rescueCases) {

            report.append(String.format(
                    "Case ID          : %s%n",
                    rescueCase.getRescueCaseId()));

            report.append(String.format(
                    "Type             : %s%n",
                    rescueCase.getRescueType()));

            report.append(String.format(
                    "Species          : %s%n",
                    rescueCase.getSpecies()));

            report.append(String.format(
                    "Rescue Location  : %s%n",
                    rescueCase.getRescueLocation()));

            report.append(String.format(
                    "Assigned Ranger  : %s%n",
                    rescueCase.getAssignedRanger()));

            report.append(String.format(
                    "Priority         : %s%n",
                    rescueCase.determineRescuePriority()));

            report.append(String.format(
                    "Status           : %s%n",
                    rescueCase.getCurrentStatus()));

            report.append(String.format(
                    "Total Cost       : %s%n",
                    RescueCase.formatMoney(
                            rescueCase.calculateTotalRescueCost())));

            report.append(
                    "------------------------------------------------------------\n");
        }

        report.append("============================================================\n");

        report.append(String.format(
                "Total Rescue Cases : %d%n",
                getTotalRescueCases()));

        report.append(String.format(
                "Total Rescue Cost  : %s%n",
                RescueCase.formatMoney(
                        getTotalEstimatedRescueCost())));

        report.append("============================================================\n");

        return report.toString();
    }
}
