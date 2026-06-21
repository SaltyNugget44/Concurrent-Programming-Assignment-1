package Assignment_1;

public class RaceConditionDemo {

    private static final double amountPerIteration = 1.0;

    public static void run(BankAccount account, int choice, double amount) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         Race Condition Demo          ║");
        System.out.println("╚══════════════════════════════════════╝");

        int iterations = (int) amount;

        switch (choice) {
            case 1:
                runDeposit(account, iterations);
                break;
            case 2:
                runWithdraw(account, iterations);
                break;
            default:
                System.out.println("Error");
        }
    }

    private static void runDeposit(BankAccount account, int iterations) {
        double initialBalance = account.getBalance();

        int halved = iterations / 2;
        int remainder = iterations % 2;

        final int iterationsThread1 = halved + remainder;
        final int iterationsThread2 = halved;

        Runnable depositTask1 = () -> {
            for (int i = 0; i < iterationsThread1; i++) {
                account.depositRCS(amountPerIteration);
            }
        };

        Runnable depositTask2 = () -> {
            for (int i = 0; i < iterationsThread2; i++) {
                account.depositRCS(amountPerIteration);
            }
        };

        Thread t1 = new Thread(depositTask1, "Thread-1");
        Thread t2 = new Thread(depositTask2, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        account.serviceCharge(); // steals money from customer for giving them service charge

        double expectedBalance = initialBalance + (iterations * amountPerIteration) - BankAccount.SERVICE_CHARGE_AMOUNT;

        if  (expectedBalance < 0) {
            expectedBalance = 0;
        }

        printResult(initialBalance, expectedBalance, account.getBalance(), iterations);
    }

    private static void runWithdraw(BankAccount account, int iterations) {
        double initialBalance = account.getBalance();

        int half = iterations / 2;
        int remainder = iterations % 2;

        final int iterationsThread1 = half + remainder;
        final int iterationsThread2 = half;

        if (initialBalance < iterations) {
            System.out.println("  [!] Insufficient funds for this batch of withdrawals.");
            return;
        }

        Runnable withdrawTask1 = () -> {
            for (int i = 0; i < iterationsThread1; i++) {
                account.withdrawRCS(amountPerIteration);
            }
        };

        Runnable withdrawTask2 = () -> {
            for (int i = 0; i < iterationsThread2; i++) {
                account.withdrawRCS(amountPerIteration);
            }
        };

        Thread t1 = new Thread(withdrawTask1, "Thread-1");
        Thread t2 = new Thread(withdrawTask2, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        account.serviceCharge(); // steals money from customer for giving them service charge

        double expectedBalance = initialBalance - (iterations * amountPerIteration) - BankAccount.SERVICE_CHARGE_AMOUNT;

        if  (expectedBalance < 0) {
            expectedBalance = 0;
        }

        printResult(initialBalance, expectedBalance, account.getBalance(), iterations);
    }

    private static void printResult(double initialBalance, double expectedBalance, double actualBalance, int iterations) {
        System.out.println("\nTotal iterations: " + iterations);
        System.out.println("Initial Balance : RM" + String.format("%.2f", initialBalance));
        System.out.println("Expected Balance: RM" + String.format("%.2f", expectedBalance));
        System.out.println("Actual Balance  : RM" + String.format("%.2f", actualBalance));

        if (expectedBalance == actualBalance) {
            System.out.println("No race condition detected in this transaction");
        }
        else if (expectedBalance > actualBalance){
            System.out.println("Race condition detected. Lost RM" +
                    String.format("%.2f", (expectedBalance - actualBalance)));
        }
        else {
            System.out.println("Race condition detected. Gained extra RM" +
                    String.format("%.2f", (actualBalance - expectedBalance)));
        }
    }
}
