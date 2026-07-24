package com.academy.bank;

import java.util.*;
import java.lang.*;


public class Customer implements Printable{

    private int customerID;
    private String name;
    private String email;
    private String phone;

    public Customer(){
        //Do Nothing?
        this.customerID = null;
        this.name = null;
        this.email = null;
        this.phone = null;
    }

    public Customer(int customerID, String name, String email, String phone){
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    //Are there enums/structs to define custom error codes? A whole class for each feels like too much overhead.

    //customerID must be positive integer.
    public boolean setCustomerID(int customerID) {
        if (customerID < 0){
            return false;
        }
        this.customerID = customerID;
        return true;
    }

    //Email must follow the pattern of some amount of alpha-numeric characters followed by @, and a domain.
    //domain is gmail.com, yahoo.com, custom. length of either is not constant.
    public boolean setEmail(String email) {
        //I wasn't taught to do regexes.
        //I found this code snippet at Geeks4Geeks
        //written by pranav gupta on 11/28/2024
        //https://www.geeksforgeeks.org/java/check-email-address-valid-not-java/
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex
        Pattern p = Pattern.compile(emailRegex);

        // Check if email matches the pattern
        if(email != null && p.matcher(email).matches()){
            this.email = email;
            return true;
        }

    }

    //Name cannot be blank.
    public boolean setName(String name) {
        if(name == ""){
            return false;
        }
        this.name = name;
    }

    //Phones should have 10 digits (assuming no country code. Or adding +1)
    /*Allows
    - (xxx) xxx-xxxx
    - xxxxxxxxx
    - xxx-xxx-xxxx

    These are the only phone number formats I've seen regularly.
     */
    public boolean setPhone(String phone) {
        //for each character in the string, check to see if it's a digit.
        this.phone = phone;
        return true;
        //TODO: validate phone number formats.
    }

    public int getCustomerID(){
        return customerID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}