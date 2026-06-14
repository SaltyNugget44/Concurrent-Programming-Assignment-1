package Assignment_1;

public class SynchronizationDemo {
        public static void run(BankAccount account, int choice, double amount) {
                System.out.println("  [Synchronization Demo]");

                switch (choice) {
                        case 1 -> {
                                int threadCount = 5;
                                double depositPerThread = amount/threadCount;
                                Thread[] threads = new Thread[threadCount];

                                for (int i = 0; i < threadCount; i++) {
                                        int threadNum = i + 1;
                                        threads[i] = new Thread(() -> {
                                                synchronized (account) {
                                                        account.deposit(depositPerThread);
                                                        System.out.println("[" + Thread.currentThread().getName() + "] " +
                                                                "Deposited " + depositPerThread + " → Balance: " + account.getBalance());
                                                }
                                        });
                                }

                                for (Thread t : threads) {
                                        t.start();
                                        try {
                                                Thread.sleep(10);
                                        } catch (InterruptedException e) {
                                                Thread.currentThread().interrupt();
                                        }
                                }

                                for (Thread t : threads) {
                                        try {
                                                t.join();
                                        } catch (InterruptedException e) {
                                                Thread.currentThread().interrupt();
                                        }
                                }
                        }

                        case 2 -> {
                                int threadCount = 5;
                                double withdrawPerThread = amount/threadCount;
                                Thread[] threads = new Thread[threadCount];

                                for (int i = 0; i < threadCount; i++) {
                                        int threadNum = i + 1;
                                        threads[i] = new Thread(() -> {
                                                synchronized (account) {
                                                        if (account.getBalance() < withdrawPerThread) {
                                                                System.out.println("  [" + Thread.currentThread().getName() + "] " +
                                                                        "Insufficient balance. Withdrawal of RM" +
                                                                        String.format("%.2f", withdrawPerThread) + " cancelled.");
                                                        } else {
                                                                account.withdraw(withdrawPerThread);
                                                                System.out.println("  [" + Thread.currentThread().getName() + "] " +
                                                                        "Withdrew RM" + String.format("%.2f", withdrawPerThread) +
                                                                        " → Balance: RM" + String.format("%.2f", account.getBalance()));
                                                        }
                                                }
                                        });
                                }

                                for (Thread t : threads) {
                                        t.start();
                                        try{
                                                Thread.sleep(10);
                                        } catch (InterruptedException e) {
                                                Thread.currentThread().interrupt();
                                        }
                                }

                                for (Thread t : threads) {
                                        try {
                                                t.join();
                                        } catch (InterruptedException e) {
                                                Thread.currentThread().interrupt();
                                        }
                                }
                        }

                }
                System.out.println("\nFinal Balance: RM" + String.format("%.2f", account.getBalance()));
        }
}