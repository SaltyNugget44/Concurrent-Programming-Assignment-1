class Sequential {

    public void run(BankAccount account, int[] txTypes, double[] amount){
        // 1 = deposit
        // 2 = withdraw
        System.out.println("=====Starting Transaction=====");
        for (int i = 0; i < amount.length; i++){
            if (txTypes[i] == 1){
                processDeposit(account, amount[i]);
            } else {
                processWithdrawal(account, amount[i]);
            }
        }
        System.out.println("=============Done!============");
    }

    public void processDeposit(BankAccount account, double amount){

        System.out.println("Depositing RM" + amount + "...");
        
        try {
            // sleep to simulate transaction process
            Thread.sleep(4000);                    
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " interrupted"); 
        }

        account.deposit(amount);
        account.checkBalance();    
    }  

    public void processWithdrawal(BankAccount account, double amount){
        System.out.println("Depositing RM" + amount + "...");
        
        try {
            // sleep to simulate transaction process
            Thread.sleep(4000);                    
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " interrupted"); 
        }

        account.deposit(amount);
        account.checkBalance();   
    }
}