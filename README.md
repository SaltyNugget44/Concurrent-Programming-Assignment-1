# Concurrent Bank Account Transaction System
> CSC2044 — Concurrent Programming Assignment
A Java console application demonstrating sequential, concurrent, race conditions, and synchronization transaction processings on a shared bank account.

## Features
- Sequential transaction processing
- Concurrent transaction processing
- Race condition demonstration
- Synchronized thread-safe processing
- Automatic service charge deduction after each batch of transactions

## Project Structure
├── BankAccount.java              # Core account logic (deposit, withdraw, service charge)  
├── SequentialDemo.java           # Baseline — transactions run one at a time  
├── ConcurrentDemo.java           # Concurrent — transactions run one at a time  
├── SynchronizationDemo.java      # Thread-safe concurrent transactions  
├── RaceConditionDemo.java        # Demonstrates race conditions without synchronization  
└── BankAccountTransaction.java   # Main entry point

## How to Run
1. Clone the repository
2. Open in your preferred IDE (Eclipse, IntelliJ, VS Code)
3. Run 'BankAccountTransaction.java'
4. Log in using the test credentials below  -username='alice'  -password='1234'
5. Follow the on-screen menu to select a demo

## Requirements
- Java 17 or higher
- No external dependencies

## Key Concepts
- **Sequential**: instructions are executed in a strict, step-by-step order
- **Concurrency**: the ability of a program to manage multiple tasks at the same time
- **Race Conditions**: occurs when multiple threads read-modify-write 
  shared state without synchronization, causing lost updates
- **Synchronization**: using 'synchronized' blocks to ensure thread-safe
  access to shared resources

## Sample Output
╔══════════════════════════════════════╗
║         Race Condition Demo          ║
╚══════════════════════════════════════╝

Total iterations: 10000
Initial Balance : RM1000.00
Expected Balance: RM11000.00
Actual Balance  : RM9449.00
Race condition detected. Lost RM1551.00

## Limitations
- Single-run use case (no persistent data storage)
- SequentialDemo and ConcurrentDemo operate independently from the main transaction 
  menu selection.
- serviceCharge() bug in SynchronizationDemo when (balance <= 0)
- Credentials stored in plaintext for demonstration purposes only

## Authors
TAN LI KANG  
DARRYL CHAN WEI QIAN  
CHARISSA SANTOS CRUZ  
TIFFANY SIM LING LIN
