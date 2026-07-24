package com.academy.bank;

public class SavingsAccount extends Account implements Printable {

    private final double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, Customer customer, double interestRate) {
        super(accountNumber, initialBalance, customer);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double calculateInterest(){
        return super.getBalance() * (interestRate / 100.0);
    }

    @Override
    public void displayAccount(){
        System.out.printf("%s Account Number: %s. %s. Balance %.2f. Rate %.2f. Interest %.2f\n",
                getAccountType(),
                super.getAccountNumber(),
                super.getCustomer().getName(),
                super.getBalance(),
                interestRate,
                calculateInterest());
    }

    @Override
    public void printDetails(){
        displayAccount();
    }




}