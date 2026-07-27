package com.academy.bank;

import java.time.LocalDate;
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

    public void createCustomer() {


        String name, email, phone, customerID;

        System.out.print("Please enter your customerID.");
        customerID = scanner.nextLine().trim();

        if (customerCount != 0){
            for(int i = 0; i < customerCount; i++){
                if(customers[i].getCustomerId().equals(customerID)){
                    System.out.println("That ID has already been registered.");
                    return;
                }
            }
        }

        System.out.print("What is your name? ");
        name = scanner.nextLine().trim();
        while (name.equals("")){
            System.out.println("You must enter a name. Please try again.");
            System.out.print("What is your name? ");
            name = scanner.nextLine().trim();
        }

        //I don't know how to validate an email using regex or other pattern matching methods
        System.out.print("What is your email? ");
        email = scanner.nextLine().trim();
        while(email.equals("")){
            System.out.println("You must enter an email address. Please try again.");
            System.out.print("What is your email? ");
            email = scanner.nextLine().trim();
        }

        System.out.print("What is your phone number? ");
        phone = scanner.nextLine().trim();
        while(phone.equals("")){
            System.out.println("You must enter an phone number. Please try again.");
            System.out.print("What is your phone number? ");
            phone = scanner.nextLine().trim();
        }

        customers[customerCount] = new Customer(customerID, name, email, phone);
        customerCount++;

        System.out.println("Customer created successfully");
    }

    public void createSavingsAccount() {

        System.out.print("Enter your customerID: ");
        String customerID = scanner.nextLine();
        Customer customer = null;
        if (customerCount != 0){
            for(int i = 0; i < customerCount; i++){
                if(customers[i].getCustomerId().equals(customerID)){
                    customer = customers[i];
                    break;
                }
            }
        } else {
            System.out.println("Please register as a customer before creating an account");
            return;
        }
        if(customer == null){
            System.out.println("Please register as a customer before creating an account");
            return;
        }

        System.out.print("Please enter your initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        //I suppose you can start in debt?

        System.out.println("Please enter the interest rate of the account: ");
        double interestRate = Double.parseDouble(scanner.nextLine());

        Account saving = new SavingsAccount(Integer.toString(nextAccountNumber), initialBalance, customer, interestRate);
        accounts[accountCount] = saving;
        accountCount++;
        nextAccountNumber++;

        System.out.println("You have successfully created a savings account.");
    }

    public void createCurrentAccount() {
        System.out.print("Enter your customerID: ");
        String customerID = scanner.nextLine();
        Customer customer = null;
        if (customerCount != 0){
            for(int i = 0; i < customerCount; i++){
                if(customers[i].getCustomerId().equals(customerID)){
                    customer = customers[i];
                    break;
                }
            }
        } else {
            System.out.println("Please register as a customer before creating an account");
            return;
        }
        if(customer == null){
            System.out.println("Please register as a customer before creating an account");
            return;
        }

        System.out.print("Please enter your initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        //I suppose you can start in debt?

        System.out.println("Please enter the transaction fee for the account: ");
        double transactionFee = Double.parseDouble(scanner.nextLine());

        Account current = new CurrentAccount(Integer.toString(nextAccountNumber), initialBalance, customer, transactionFee);
        accounts[accountCount] = current;
        accountCount++;
        nextAccountNumber++;

        System.out.println("You have successfully created a current account.");
    }

    public void deposit() {
        // TODO: read existing account + amount; account.deposit; recordTransaction DEPOSIT
        // TODO: print updated balance
        System.out.print("Enter your accountID: ");
        String accountID = scanner.nextLine();
        boolean account_found = false;
        Account transaction_account = null;
        if (accountCount != 0){
            for(int i = 0; i < accountCount; i++){
                if(accounts[i].getAccountNumber().equals(accountID)){
                    account_found = true;
                    transaction_account = accounts[i];
                    break;
                }
            }
        } else {
            System.out.println("Please create an acccount before making a deposit.");
            return;
        }
        if(!account_found){
            System.out.println("Please create an acccount before making a deposit.");
            return;
        }

        System.out.print("Please enter you deposit: ");
        double deposit = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(Integer.toString(nextTransactionNumber),
                                                    deposit, "Deposit",
                                                    LocalDate.now().toString(), accountID);

        transactions[transactionCount] = transaction;
        transactionCount++;

        transaction_account.deposit(deposit);

        System.out.println("You have successfully made a deposit. ");
    }

    public void withdraw() {
        System.out.print("Enter your accountID: ");
        String accountID = scanner.nextLine();
        boolean account_found = false;
        Account transaction_account = null;
        if (accountCount != 0){
            for(int i = 0; i < accountCount; i++){
                if(accounts[i].getAccountNumber().equals(accountID)){
                    account_found = true;
                    transaction_account = accounts[i];
                    break;
                }
            }
        } else {
            System.out.println("Please create an acccount before making a withdraw.");
            return;
        }
        if(!account_found){
            System.out.println("Please create an acccount before making a withdraw.");
            return;
        }

        System.out.print("Please enter you withdraw: ");
        double withdraw = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(Integer.toString(nextTransactionNumber),
                withdraw, "Withdraw",
                LocalDate.now().toString(), accountID);

        transactions[transactionCount] = transaction;
        transactionCount++;

        transaction_account.withdraw(withdraw);

        System.out.println("You have successfully made a deposit. ");
    }

    public void displayAccounts() {
        if(accountCount == 0){
            System.out.println("There are no accounts to display");
            return;
        }

        for(int i = 0; i < accountCount; i++){
            accounts[i].displayAccount();
        }
    }

    public void displayCustomers() {
        if (customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("----------------------------------");
        for (int i = 0; i < customerCount; i++) {
            customers[i].display();
            System.out.println("----------------------------------");
        }
    }

    public void transferMoney() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayTransactionHistory() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayAccountsSortedByBalance() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayHighestBalanceCustomer() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void generateAccountSummaryReport() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    private Customer readExistingCustomer() {
        if (customerCount == 0) {
            System.out.println("Create a customer first.");
            return null;
        }

        System.out.print("Customer ID : ");
        String customerId = scanner.nextLine().trim();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        }

        return customer;
    }

    private Account readExistingAccount() {
        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return null;
        }

        System.out.print("Account Number : ");
        String accountNumber = scanner.nextLine().trim();
        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    private Customer findCustomer(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equalsIgnoreCase(customerId)) {
                return customers[i];
            }
        }
        return null;
    }

    private Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    private void recordTransaction(String accountNumber, double amount, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            return;
        }

        String transactionId = "T" + nextTransactionNumber++;
        String date = LocalDate.now().toString();
        transactions[transactionCount++] = new Transaction(transactionId, amount, type, date, accountNumber);
    }

    private double readPositiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Amount must not be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }
}
