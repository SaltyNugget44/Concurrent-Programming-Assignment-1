package Assignment_1;

/*
 * BankAccount.java
 * Stores all account data and the 4 transaction methods. This object lives in BankTransactionSystem and keeps 
 * the balance in memory as long as the program is running.
 */
public class BankAccount {

    private String accountID;
    private String owner;
    private double balance;

    // Setters
    public BankAccount(String accountId, String owner, double initialBalance) {
        this.accountID = accountId;
        this.owner = owner;
        this.balance = initialBalance;
    }
    
    // Getters
    public double getBalance()   { return balance; }
    public String getAccountId() { return accountID; }
    public String getOwner()     { return owner; }
    
    public String toString() {
        return "Account ID : " + accountID
             + "\nOwner      : " + owner
             + "\nBalance    : RM" + String.format("%.2f", balance);
    }
    
    // Service charge fixed amount
    public static final double SERVICE_CHARGE_AMOUNT = 5.00;

    // Transaction 1: Deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [!] Deposit amount must be greater than RM0.00");
            return;
        }
        balance = balance + amount;
        System.out.printf("  [+] Deposited RM%.2f successfully.%n", amount);
        serviceCharge(); // steals money from customer for giving them service charge 
    }
    

    // Transaction 2: Withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [!] Withdrawal amount must be greater than RM0.00");
            return;
        }
        if (amount > balance) {
            System.out.println("  [!] Insufficient funds. Current balance: RM" + String.format("%.2f", balance));
            return;
        }
        balance = balance - amount;
        System.out.printf("  [-] Withdrawn RM%.2f successfully.%n", amount);
        serviceCharge(); // steals money from customer for giving them service charge 
    }

    // Transaction 3: Check Balance
    public void checkBalance() {
        System.out.println("  [=] Current Balance: RM" + String.format("%.2f", balance));
    }

    // Transaction 4: Service Charge
    public void serviceCharge() {
        if (balance < SERVICE_CHARGE_AMOUNT) {
            System.out.println("  [!] Insufficient funds to deduct service charge of RM"
                    + String.format("%.2f", SERVICE_CHARGE_AMOUNT));
            return;
        }
        balance = balance - SERVICE_CHARGE_AMOUNT;
        System.out.printf("  [*] Service charge of RM%.2f deducted.%n", SERVICE_CHARGE_AMOUNT);
    }

    //For Race Condition
    public void setBalanceRC(double balance) {
        this.balance = balance;
    }

    
}
