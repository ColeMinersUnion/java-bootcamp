package com.academy.bank;

import java.util.Scanner;

public class BankService {

    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    /*Menu Options
        1 Create Customer
        2 Create Savings Account
        3 Create Current Account
        4 Deposit
        5 Withdraw
        6 Display Accounts
        7 Display Customers
     */

    public void addCustomer(){
        // Need to ask for name, email & phone.
        // assign ID
        Customer pendingCustomer = new Customer();

        System.out.print("What is your name? ");
        String name = scanner.nextLine();
        while(!pendingCustomer.setName(name)){
            System.out.println("Your name cannot be empty. Please try again");
            System.out.print("What is your name? ");
            name = scanner.nextLine();
        }

        System.out.print("What is your phone number? ");
        String phone = scanner.nextLine();
        while(!pendingCustomer.setPhone(phone)){
            System.out.println("Invalid Phone number. Please try again.");
            System.out.print("What is your phone number? ");
            phone = scanner.nextLine();
        }

        System.out.print("What is your email? ");
        String email = scanner.nextLine();
        while(!pendingCustomer.setEmail(email)){
            System.out.println("Invalid Email address. Please try again.");
            System.out.print("What is your email? ");
            email = scanner.nextLine();
        }

        //not a secure ID
        pendingCustomer.setCustomerID(customerCount);
        customers[customerCount] = pendingCustomer;

        customerCount++;

        //display customer here or in main? Probably here
        pendingCustomer.printDetails();

    }
    //String accountNumber, double initialBalance, Customer customer, double interestRate
    public void createSavingsAccount(){

        if (accountCount >= MAX_ACCOUNTS){
            System.out.println("We\'ve exceeded out maximum number of accounts. ");
            return;
        }

        System.out.print("What is your customerID? ");
        int customerID = Integer.parseInt(scanner.nextLine());

        System.out.print("What is your initial balance? ");
        double balance = Double.parseDouble(scanner.nextLine());

        System.out.print("What is the interest rate? ");
        double interestRate = Double.parseDouble(scanner.nextLine());

        String accountID = "S" + nextAccountNumber;

        SavingsAccount acct = new SavingsAccount(accountID, balance, customers[customerID], interestRate);

        accounts[accountCount] = acct;

        nextAccountNumber++;
        accountCount++;

    }

    public void createCurrentAccount(){
        if (accountCount >= MAX_ACCOUNTS){
            System.out.println("We\'ve exceeded out maximum number of accounts. ");
            return;
        }

        System.out.print("What is your customerID? ");
        int customerID = Integer.parseInt(scanner.nextLine());

        System.out.print("What is your initial balance? ");
        double balance = Double.parseDouble(scanner.nextLine());

        String accountID = "C" + nextAccountNumber;

        CurrentAccount acct = new CurrentAccount(accountID, balance, customers[customerID]);

        accounts[accountCount] = acct;

        nextAccountNumber++;
        accountCount++;

    }

    public void deposit(){
        System.out.print("What is your account?");
        String accountID = scanner.nextLine();
        int accountIDX = Integer.parseInt(accountID.substring(accountID.length() - 3));

        while (accountIDX < 0 || accountIDX > accountCount){
            System.out.println("The requested account is unavailable. Please try a different account number.");
            System.out.print("What is your account?");
            accountID = scanner.nextLine();
            accountIDX = Integer.parseInt(accountID.substring(accountID.length() - 3));
        }

        System.out.print("How much are you depositing? ");
        double amount = Double.parseDouble(scanner.nextLine());
        while(!accounts[accountIDX].deposit(amount)){
            System.out.println("Your deposit must be a positive number.");
            System.out.print("How much are you depositing? ");
            amount = Double.parseDouble(scanner.nextLine());
        }

        //TODO: get date
        String date = "Today";


        //Record Transaction
        transactions[transactionCount] = new Transaction(nextTransactionNumber,
                amount,
                accounts[accountIDX].getAccountType(),
                "Deposit",
                date,
                accountID);


    }

    public void withdraw(){
        System.out.print("What is your account?");
        String accountID = scanner.nextLine();
        int accountIDX = Integer.parseInt(accountID.substring(accountID.length() - 3));

        while (accountIDX < 0 || accountIDX > accountCount){
            System.out.println("The requested account is unavailable. Please try a different account number.");
            System.out.print("What is your account?");
            accountID = scanner.nextLine();
            accountIDX = Integer.parseInt(accountID.substring(accountID.length() - 3));
        }

        System.out.print("How much are you withdrawing? ");
        double amount = Double.parseDouble(scanner.nextLine());
        while(!accounts[accountIDX].withdraw(amount)){
            System.out.println("Your withdraw was invalid.");
            System.out.print("How much are you withdrawing? ");
            amount = Double.parseDouble(scanner.nextLine());
        }

        //TODO: get date
        String date = "Today";


        //Record Transaction
        transactions[transactionCount] = new Transaction(nextTransactionNumber,
                amount,
                accounts[accountIDX].getAccountType(),
                "Withdraw",
                date,
                accountID);

    }

    public void displayAccounts(){
        for(int i = 0; i < accountCount; i++){
            accounts[i].displayAccount();
        }
    }

    public void displayCustomers(){
        for(int i = 0; i < customerCount; i++){
            customers[i].printDetails();
        }
    }



}