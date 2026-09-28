package payment;

public class UPIPaymentProcessor implements PaymentProcessor {
    private final String upiId;

    public UPIPaymentProcessor(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.printf("  [UPI Gateway] Connecting to VPA: %s... Requesting ₹%.2f... APPROVED!\n", upiId, amount);
        return true;
    }

    @Override
    public String getMethodName() {
        return "UPI (" + upiId + ")";
    }
}
