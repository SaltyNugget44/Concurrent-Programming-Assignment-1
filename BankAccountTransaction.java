package Assignment_1;

import java.util.Scanner;

/**
 * BankAccountTransaction.java
 * MAIN FILE — run this to start the program.
 *
 * Handles:
 *  - Login
 *  - Transaction menu (deposit / withdraw / check balance)
 *  - Processing mode menu (sequential / concurrent / race condition / synchronization)
 *  - For sequential: collects 8 transactions from user, then passes to SequentialDemo
 *  - For others: passes single transaction + amount to the chosen demo
 */
public class BankAccountTransaction {

    // Login credentials (hardcoded for demo purposes)
    private static final String VALID_USERNAME = "alice";
    private static final String VALID_PASSWORD = "1234";

    // Shared BankAccount object — lives here, passed to demos
    // Balance is remembered across all transactions as long as program is running
    private static BankAccount account;

    private static Scanner scanner = new Scanner(System.in);

    // Program starts here
    public static void main(String[] args) {

        printWelcome();

        // Step 1: Login
        if (!login()) {
            System.out.println("\n  [!] Too many failed attempts. Exiting...");
            return;
        }

        // Step 2: Create the shared BankAccount (initial balance RM1000)
        account = new BankAccount("ACC-001", VALID_USERNAME.toUpperCase(), 1000.00);
        System.out.println("\n  Login successful! Welcome, " + account.getOwner() + ".");
        System.out.println("  Your account has been loaded with RM1000.00");

        // Step 3: Main transaction loop
        int choice = -1;
        while (choice != 0) {
            printTransactionMenu();
            choice = getIntInput("  Enter your choice: ");

            if (choice == 0) {
                System.out.println("\n  Logging out... Goodbye, " + account.getOwner() + "!");
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("  [!] Invalid choice. Please enter 0-3.");
                continue;
            }

            // Step 4: Pick processing mode first
            printProcessingMenu();
            int mode = getIntInput("  Enter your choice: ");

            if (mode < 1 || mode > 4) {
                System.out.println("  [!] Invalid choice. Returning to menu.");
                continue;
            }

            System.out.println();

            // Step 5: Sequential needs 8 transactions — collect all from user here in main
            if (mode == 1) {
                System.out.println("  You selected Sequential Processing.");
                System.out.println("  Please enter 8 transactions.\n");

                int[]    txTypes  = new int[8];
                double[] amounts  = new double[8];

                for (int i = 0; i < 8; i++) {
                    System.out.println("  -- Transaction " + (i + 1) + " --");

                    // Ask transaction type
                    System.out.println("  Type: 1. Deposit   2. Withdraw   3. Check Balance");
                    txTypes[i] = getIntInput("  Enter type: ");
                    while (txTypes[i] < 1 || txTypes[i] > 3) {
                        System.out.println("  [!] Invalid. Please enter 1, 2, or 3.");
                        txTypes[i] = getIntInput("  Enter type: ");
                    }

                    // Ask amount if deposit or withdraw
                    if (txTypes[i] == 1 || txTypes[i] == 2) {
                        amounts[i] = getDoubleInput("  Enter amount (RM): ");
                    } else {
                        amounts[i] = 0; // no amount needed for check balance
                    }

                    System.out.println();
                }

                // Pass all 8 transactions to SequentialDemo
                SequentialDemo.run(account, txTypes, amounts);

            // Step 6: All other modes — single transaction as usual
            } else {
                // Get amount if needed
                double amount = 0;
                if (choice == 1 || choice == 2) {
                    amount = getDoubleInput("  Enter amount (RM): ");
                }

                if (mode == 2) {
                    ConcurrentDemo.run(account, choice, amount);
                } else if (mode == 3) {
                    RaceConditionDemo.run(account, choice, amount);
                } else if (mode == 4) {
                    SynchronizationDemo.run(account, choice, amount);
                }
            }

            // Pause before going back to menu
            System.out.println("\n  Press ENTER to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

    // Login — user has only 3 attempts
    private static boolean login() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║              LOGIN                   ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("  Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("  Password: ");
            String password = scanner.nextLine().trim();

            if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                return true;
            }

            attempts++;
            System.out.println("  [!] Incorrect username or password. Attempts left: " + (3 - attempts));
        }

        return false;
    }

    // Menus
    private static void printWelcome() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     BANK TRANSACTION SYSTEM          ║");
        System.out.println("║     CSC2044 Group Assignment         ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private static void printTransactionMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       SELECT TRANSACTION             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf ("║  Account: %-27s║%n", account.getOwner());
        System.out.printf ("║  Balance: RM%-25s║%n", String.format("%.2f", account.getBalance()));
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Deposit                          ║");
        System.out.println("║  2. Withdraw                         ║");
        System.out.println("║  3. Check Balance                    ║");
        System.out.println("║  0. Logout                           ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private static void printProcessingMenu() {
        System.out.println("\n  How would you like to process this?");
        System.out.println("  1. Sequential Processing");
        System.out.println("  2. Concurrent Processing");
        System.out.println("  3. Race Condition Processing");
        System.out.println("  4. Synchronization Processing");
    }

    // Input helpers

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("  [!] Amount must be greater than RM0.00");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid amount.");
            }
        }
    }
}
