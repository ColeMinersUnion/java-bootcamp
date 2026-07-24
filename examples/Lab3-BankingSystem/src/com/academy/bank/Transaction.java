package com.academy.bank;

public class Transaction implements Printable {
    //transaction details should be immutable after initialization
    public final int transactionID;
    public final double amount;
    public final String accountType;
    public final String transactionType;
    public final String date; //Is there a datetime object or equivalent?
    public final String accountNumber;

    public Transaction(int transactionID, double amount, String accountType, String transactionType, String date, String accountNumber){
        this.transactionID = transactionID;
        this.amount = amount;
        this.accountType = accountType;
        this.transactionType = transactionType;
        this.date = date;
        this.accountNumber = accountNumber;
    }

    public int getTransactionID(){
        return transactionID;
    }

    public double getAmount(){
        return amount;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public String getDate(){
        return date;
    }

    public String getAccountType(){
        return accountType;
    }

    public String getAccoutNumber(){
        return accountNumber;
    }

    public void display(){
        System.out.printf("Transaction: %d. A %s of %.2f occured from a %s account numbered: %s on %s",
                transactionID,
                transactionType,
                amount,
                accountType,
                accountNumber,
                date
                );
    }

    @Override
    public void printDetails(){
        display();
    }
}