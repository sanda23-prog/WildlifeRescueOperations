package wildliferescueoperations;

/**
 * All rescue case types must support these operations.
 */
public interface RescueOperations {

    void startRescue();

    void completeRescue();

    String generateRescueSummary();
}
