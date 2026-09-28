package payment;

public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("  [Counter Cash] Cash payment of ₹%.2f collected at box office.\n", amount);
        return true;
    }

    @Override
    public String getMethodName() {
        return "Counter Cash";
    }
}
