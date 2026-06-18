# Bank Account Concurrency Demo
A Java console application demonstrating race conditions, synchronization, 
and thread-safe transaction handling on a shared bank account.

## Features
- Sequential transaction processing
- Concurrent transaction processing using ExecutorService
- Race condition simulation (unsynchronized access)
- Synchronized thread-safe transaction handling
- Automatic service charge deduction after each transaction batch

## Project Structure
├── BankAccount.java              # Core account logic (deposit, withdraw, service charge)
├── SequentialDemo.java           # Baseline — transactions run one at a time
├── SynchronizationDemo.java      # Thread-safe concurrent transactions
├── RaceConditionDemo.java        # Demonstrates race conditions without synchronization
└── BankAccountTransaction.java   # Main entry point

## How to Run
1. Clone the repository
2. Open in your preferred IDE (Eclipse, IntelliJ, VS Code)
3. Run 'BankAccountTransaction.java'
4. Follow the on-screen menu to select a demo

## Requirements
- Java 17 or higher
- No external dependencies

## Key Concepts
- **Race Conditions**: occurs when multiple threads read-modify-write 
  shared state without synchronization, causing lost updates
- **Synchronization**: using 'synchronized' blocks to ensure thread-safe
  access to shared resources
- **Thread Pooling**: using 'ExecutorService' for managed concurrent execution

## Authors
- TAN LI KANG
- DARRYL CHAN WEI QIAN
- CHARISSA SANTOS CRUZ
- TIFFANY SIM LING LIN



### Authors' Note:
You just need to work on your responsible processing technique and calls it from bankaccount.java. Something something like dis!

private static void processTransaction(BankAccount account, int transactionType, double amount) {
        switch (transactionType) {
            case 1:
                account.deposit(amount);
                break;
            case 2:
                account.withdraw(amount);
                break;
            case 3:
                account.checkBalance();
                break;
            case 4:
                account.serviceCharge();
                break;
        }
    }  // This is just a basic example


**u do not need to write anymore transaction logic in the processing file** since it is handle by the time you call the function from bankaccount.java.
