You just need to work on your responsible processing technique and calls it from bankaccount.java. Something something like dis!

private static void processTransaction(BankAccount account, int transactionType, double amount) {
        switch (transactionType) {
            case 1:
                account.deposit(amount);
                break;
            case 2:
                account.withdraw(amount);
                break;
            case 3:
                account.checkBalance();
                break;
            case 4:
                account.serviceCharge();
                break;
        }
    }  // This is just a basic example


**u do not need to write anymore transaction logic in the processing file** since it is handle by the time you call the function from bankaccount.java.
