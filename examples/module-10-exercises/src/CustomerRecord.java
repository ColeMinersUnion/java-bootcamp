/**
 * Immutable Customer record for Northstar CRM (Java 21).
 * Demonstrates concise immutable representation using records.
 */
public record CustomerRecord(String id, String fullName, String status) {
    /**
     * Compact constructor for validation.
     */
    public CustomerRecord {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName cannot be null or blank");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status cannot be null or blank");
        }
    }
}

