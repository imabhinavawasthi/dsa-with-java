package SessionOOPs;

public class BankAccount {
    String accountHolderName; // changed
    private final double id; // never be updated
    private long balance; // deposit, withdraw, checkBalance
    private String password;

    public void setPassword(String password) {
        this.password = password;
        System.out.println("Password Saved");
    }

    public double getId() {
        return this.id;
    }

    // getter
    public double getBalance() {
        return this.balance;
    }

    public void deposit(long amount) {
        if(amount<=0) System.out.println("Amount should be more than 0");
        else this.balance+=amount;
    }

    public void withdraw(long amount) {
        if(amount>this.balance) System.out.println("Insufficient Balance");
        else this.balance-=amount;
    }

    BankAccount(String name) {
        this.balance = 0;
        this.id = Math.random();
        this.accountHolderName = name;
    }
}
