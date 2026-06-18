package Assignment_1;

public class SequentialDemo {

    public static void run(BankAccount account, int[] txTypes, double[] amount){
        // 1 = deposit
        // 2 = withdraw
    	// 3 = check balance
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     SEQUENTIAL PROCESSING MODE       ║");
        System.out.println("╚══════════════════════════════════════╝");
        for (int i = 0; i < amount.length; i++){
            if (txTypes[i] == 1){
                processDeposit(account, amount[i]);
            } else if (txTypes[i] == 2) {
                processWithdrawal(account, amount[i]);
            } else if (txTypes[i] ==3) {
            	processBalance(account, amount[i]);
            }
        }

        account.serviceCharge(); // steals money from customer for giving them service charge
        System.out.println("\nFinal Balance: RM" + String.format("%.2f", account.getBalance()));
        
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         END SEQUENTIAL MODE          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    public static void processDeposit(BankAccount account, double amount){

        System.out.println("Depositing RM" + amount + "...");
        
        try {
            // sleep to simulate transaction process
            Thread.sleep(2000);                    
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " interrupted"); 
        }

        account.deposit(amount);
        account.checkBalance();    
    }  

    public static void processWithdrawal(BankAccount account, double amount){
        System.out.println("Withdrawing RM" + amount + "...");
        
        try {
            // sleep to simulate transaction process
            Thread.sleep(2000);                    
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " interrupted"); 
        }

        account.withdraw(amount);
        account.checkBalance();  
        
    }
        
    public static void processBalance(BankAccount account, double amount) {
    	System.out.println("Checking Balance");
    	
    	try {
    		Thread.sleep(2000);
    	} catch(InterruptedException e) {
    		System.err.println(Thread.currentThread().getName() + " Interrupted");
    	}
    	
    	account.checkBalance();
    
    }
}
