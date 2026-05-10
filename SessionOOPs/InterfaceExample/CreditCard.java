package SessionOOPs.InterfaceExample;

public class CreditCard implements PaymentGateway, Payment {
    @Override
    public void processPayment() {
        System.out.println("Process Credit Card Payment");
    }

    @Override
    public void refundProcess() {

    }

    @Override
    public void statusUpdate() {

    }
}
