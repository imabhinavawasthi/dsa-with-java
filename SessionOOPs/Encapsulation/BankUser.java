package SessionOOPs.Encapsulation;

public class BankUser {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Abhinav");
        bankAccount.accountHolderName = "Abhinav Awasthi";
        System.out.println(bankAccount.accountHolderName);
        System.out.println(bankAccount.getId());

        bankAccount.deposit(10000000000L);
        bankAccount.deposit(-100);
        bankAccount.deposit(500);

        bankAccount.withdraw(1000);

        bankAccount.setPassword("2345675325");

        System.out.println(bankAccount.getBalance());

        System.out.println(Math.max(2,3));
    }
}
