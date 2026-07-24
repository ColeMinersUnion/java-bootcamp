package com.academy.bank;

public abstract class Account {
    protected double balance;
    protected final String accountNumber; //cannot be changed
    protected final Customer customer;    //cannot be changed

    public Account(String accountNumber, double initialBalance, Customer customer) {

        this.balance = initialBalance;
        this.customer = customer;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    protected void setBalance(double newBalance) {balance = newBalance};

    public String getAccountNumber(){
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    // TODO: abstract method — no body; every concrete subclass must implement
    public String getAccountType(){ return "Account"; }

    public boolean deposit(double amount){
        if(amount < 0){
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount){
        if (amount < 0 || balance - amount < 0){
            return false;
        }
        balance -= amount;
        return true;
    }

    public abstract void displayAccount();
    public double calculateInterest(){ return 0.0; }

}