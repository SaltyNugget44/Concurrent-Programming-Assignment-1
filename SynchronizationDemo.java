package Assignment_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizationDemo {
    public static void run(BankAccount account, int choice, double amount) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         Synchronization Demo         ║");
        System.out.println("╚══════════════════════════════════════╝");

        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        switch (choice) {
            case 1:
                double depositPerThread = amount / threadCount;

                for (int i = 0; i < threadCount; i++) {
                    executor.submit(() -> {
                        synchronized (account) {
                            account.deposit(depositPerThread);
                            System.out.println("[" + Thread.currentThread().getName() + "] " +
                                    "Deposited " + depositPerThread + " → Balance: " + account.getBalance());
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
                break;


            case 2:
                double withdrawPerThread = amount / threadCount;

                for (int i = 0; i < threadCount; i++) {
                    executor.submit(() -> {
                        synchronized (account) {
                                account.withdraw(withdrawPerThread);
                                System.out.println("  [" + Thread.currentThread().getName() + "] " +
                                        "Withdrew RM" + String.format("%.2f", withdrawPerThread) +
                                        " → Balance: RM" + String.format("%.2f", account.getBalance()));
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
                break;


            default:
                System.out.println("Error");

            }


        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        account.serviceCharge(); // steals money from customer for giving them service charge
        System.out.println("\nFinal Balance: RM" + String.format("%.2f", account.getBalance()));
    }
}
