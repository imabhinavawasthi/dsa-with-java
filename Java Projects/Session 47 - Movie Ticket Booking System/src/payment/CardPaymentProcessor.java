package payment;

public class CardPaymentProcessor implements PaymentProcessor {
    private final String cardNumber;

    public CardPaymentProcessor(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        String masked = "XXXX-XXXX-XXXX-" + (cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : "0000");
        System.out.printf("  [Card Gateway] Authenticating card %s... Debiting ₹%.2f... SUCCESS!\n", masked, amount);
        return true;
    }

    @Override
    public String getMethodName() {
        String masked = "XXXX-" + (cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : "0000");
        return "Card (" + masked + ")";
    }
}
