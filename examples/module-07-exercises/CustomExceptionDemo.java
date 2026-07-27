public class CustomExceptionDemo {
    public static void main(String[] args) {
        Account account = new Account(100.00);

        try {
            account.withdraw(150.00);
        } catch (InsufficientFundsException ex) { // TODO: catch InsufficientFundsException
            // TODO: print ex.getMessage()
            // TODO: print shortfall with printf — ex.getRequested() - ex.getBalance()
            System.out.println(ex.getMessage());
            System.out.printf(ex.getRequested() + "-" + ex.getBalance());
        }

        // Failed withdrawal must leave the original balance unchanged.
        System.out.printf("Balance unchanged: %.2f%n",
                account.getBalance());
    }
}