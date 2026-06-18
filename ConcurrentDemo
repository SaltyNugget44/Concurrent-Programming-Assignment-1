package Assignment_1;

public class ConcurrentDemo {

    public static void run(BankAccount account, double depositAmount, double withdrawAmount) {

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     CONCURRENT TRANSACTION MODE      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Starting balance: RM" + account.getBalance());
        System.out.println();

        Thread t1 = createThread("Thread-1", " Deposit", () -> account.deposit(depositAmount));
        Thread t2 = createThread("Thread-2", " Withdraw", () -> account.withdraw(withdrawAmount));
        Thread t3 = createThread("Thread-3", " Balance Check", account::checkBalance);
        Thread t4 = createThread("Thread-4", " Service Charge", account::serviceCharge);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println("Final Balance: RM" + account.getBalance());
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         END CONCURRENT MODE          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // Create clean thread wrapper
    private static Thread createThread(String name, String action, Runnable task) {
        return new Thread(() -> {

            System.out.println(">>> " + name + " START: " + action);

            sleepRandom();

            task.run();

            System.out.println("<<< " + name + " END: " + action);
            System.out.println();

        }, name);
    }

    // Simulate unpredictable processing time
    private static void sleepRandom() {
    	 try {
             Thread.sleep(2000);                    
         } catch (InterruptedException e) {
             System.err.println(Thread.currentThread().getName() + " interrupted"); 
         }
    }
}
