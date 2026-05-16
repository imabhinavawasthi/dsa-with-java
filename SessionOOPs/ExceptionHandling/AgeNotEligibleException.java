package SessionOOPs.ExceptionHandling;

public class AgeNotEligibleException extends RuntimeException {
    public AgeNotEligibleException(String message) {
        super(message);
    }
}
