public class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new Error("Deposit amount cannot be less than zero.");
        }
        this.balance += amount;
    }

    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new Error("Deposit amount cannot be less than zero.");
        }
        if (amount > this.balance) {
            throw new Error("Insufficient Balance");
        }
        this.balance -= amount;
        return amount;
    }

    public String getAccountHolder() {
        return this.accountHolder;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

}