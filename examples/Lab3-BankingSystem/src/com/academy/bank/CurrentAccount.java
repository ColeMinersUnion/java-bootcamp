package com.academy.bank;

public class CurrentAccount extends Account implements Printable {

    private static final double FEE = 3.99;


    public CurrentAccount(String accountNumber, double initialBalance, Customer customer){
        super(AccountNumber, initialBalance, customer);
    }

    private double calculateCharges(){
        return FEE;
    }

    //I'm still using super.setBalance, so there's probably a cleaner implementation out there
    @Override
    public boolean withdraw(double amount){

        if(amount < 0 || amount + calculateCharges() > super.getBalance()){
            return false;
        }
        //I don't like how many functions this adds to the stack
        super.setBalance(super.getBalance() - (amount + calculateCharges()))
        return true;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }

    @Override
    public void displayAccount(){
        System.out.printf("%s Account Number: %s. %s. Balance %.2f.\n",
                getAccountType(),
                super.getAccountNumber(),
                super.getCustomer().getName(),
                super.getBalance());
    }

    @Override
    public void printDetails(){
        displayAccount();
    }


}