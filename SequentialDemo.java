// TODO: fix so that it asks user for withdraw and deposit amounts
import java.util.ArrayList;
import java.util.List;

class SequentialDemo {

    public static void processTransactions(BankAccount account) throws Exception {

        List<Thread> workers    = new ArrayList<>();

        double[] withdrawAmount = {10.0, 20.0, 30.0, 40.0, 50.0};
        double[] depositAmount  = {100.0, 200.0, 500.0, 600.0, 700.0};


        // withdrawing workers to simulate a withdrawing action
        for (int i = 0; i < withdrawAmount.length; i++) {
            final int index = i;
            workers.add(new Thread(() -> {
                System.out.println("Withdrawing RM" + withdrawAmount[index] + "...");

                // Thread.sleep to simulate processing time
                try { 
                    Thread.sleep(4000); 
                } catch (Exception e) { 
                    System.err.println(Thread.currentThread().getName() + " interrupted"); 
                }

                account.withdraw(withdrawAmount[index]);
                account.checkBalance();
            }, "Withdraw_Transaction_Worker-" + index));
        }

        // 5 threads to simullate deposit workers
        for (int i = 0; i < depositAmount.length; i++) {
            final int index = i;
            workers.add(new Thread(() -> {
                System.out.println("Depositing RM" + depositAmount[index] + "...");
                // Thread.sleep to simulate processing time
                try { 
                    Thread.sleep(4000); 
                } catch (Exception e) { 
                    System.err.println(Thread.currentThread().getName() + " interrupted"); 
                }
                account.deposit(depositAmount[index]);
                account.checkBalance();
            }, "Deposit_Transaction_Worker-" + index));
        }

        // Run sequentially
        for (Thread worker : workers) {
            System.out.println("==== " + worker.getName() + ": Starting! ====");
            worker.start();
            worker.join();
            System.out.println("==== " + worker.getName() + ": Done! ====\n");
        }
    }
}
