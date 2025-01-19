package adeo.leroymerlin.cdp.exceptions;

/**
 * Not Found Exception for Event requests
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String eventId) {
        super(String.format("No Event found in the database with ID %s.", eventId));
    }
}
