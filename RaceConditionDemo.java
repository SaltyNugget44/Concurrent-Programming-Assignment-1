package com.example.demo;

public class RaceConditionDemo {
    public static void run(BankAccount account, int choice, double amount) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         Race Condition Demo          ║");
        System.out.println("╚══════════════════════════════════════╝");

        double amountPerThread = amount / 5;

        switch (choice) {
            case 1:
                runDeposit(account, amountPerThread);
                break;
            case 2:
                runWithdraw(account, amountPerThread);
                break;
            default:
                System.out.println("Error");
        }

    }


    private static void runDeposit(BankAccount account, double amountPerThread) {

        Runnable task = () -> depositUnsafe(account, amountPerThread);

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }

        System.out.println("\nFinal Balance: RM" + String.format("%.2f", account.getBalance()));
    }

    private static void runWithdraw(BankAccount account, double amountPerThread) {
        Runnable task = () -> withdrawUnsafe(account, amountPerThread);

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFinal Balance: RM" + String.format("%.2f", account.getBalance()));
    }


    private static void depositUnsafe(BankAccount account, double depositAmount) {
        double current = account.getBalance();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }

        account.setBalanceRC(current + depositAmount);
        System.out.println("[" + Thread.currentThread().getName() + "] " +
                "Deposited " + depositAmount + " → Balance: " + account.getBalance());
    }

    private static void withdrawUnsafe(BankAccount account, double withdrawAmount) {
        double current = account.getBalance();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }

        account.setBalanceRC(current - withdrawAmount);
        System.out.println("[" + Thread.currentThread().getName() + "] " +
                "Withdrew " + withdrawAmount + " → Balance: " + account.getBalance());
    }
}
