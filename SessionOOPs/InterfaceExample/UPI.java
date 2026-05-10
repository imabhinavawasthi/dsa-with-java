package SessionOOPs.InterfaceExample;

public class UPI implements PaymentGateway {
    @Override
    public void processPayment() {
        System.out.println("Process UPI Payment");
    }

    @Override
    public void refundProcess() {

    }

    @Override
    public void statusUpdate() {

    }
}
