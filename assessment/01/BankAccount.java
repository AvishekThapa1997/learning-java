class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double balance) {
        if (isValidAmount(balance)) {
            this.accountHolder = accountHolder;
            this.accountNumber = accountNumber;
            this.balance = balance;
        }
        throw new IllegalArgumentException("Deposit amount cannot be less than zero");
    }

    private static boolean isValidAmount(double amount) {
        return amount >= 0;
    }

    private boolean isValidWithdrawalAmount(double amount) {
        return isValidAmount(amount) && this.balance > amount;
    }

    public void deposit(double amount) {
        if (isValidAmount(amount)) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount cannot be less than zero");
        }
    }

    public double withdraw(double amount) {
        if (!isValidAmount(amount)) {
            throw new IllegalArgumentException("Deposit amount cannot be less than zero");
        }
        if (!isValidWithdrawalAmount(amount)) {
            throw new IllegalArgumentException("Insufficient balance");
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

class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Avishek", "12345", -500);
        System.out.println(bankAccount);
    }
}
