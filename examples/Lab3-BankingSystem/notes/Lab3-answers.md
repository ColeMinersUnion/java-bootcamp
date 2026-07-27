# Reflection Questions

### Why should Account be abstract rather than a concrete empty type?
Account is an abstract class because it acts as a template for its child classes, `SavingsAccount` and `CurrentAccount`. 
If Account were ever needed to be instantiated, then it should not be an abstract class. But since it defines behavior for its child classes, and is never instantiated, it should be an abstract class.

### Where does dynamic dispatch show up when you call displayAccount() on Account[]?
Since the `accounts[]` library is a list of the parent class, whenever I iterate through the list and call a function that's described in the abstract class. It determines during runtime, (dynamic dispatch), which method to run, based on the class of the object.

### How does Printable differ from extending a base class?
Printable is an interface, not a base class. The main difference is in how they are used. Interfaces are the example that something implements.

### What would break if Main owned all arrays instead of BankService?
Most of the service would break if `Main` owned all arrays. Most methods in bank service rely on the arrays to store and act upon the data. If all of the arrays were passed by reference to each method, this would be excessive and bad practice, but should still work. 

### How do today’s Customer/Account patterns prepare you for later CRM entity design without building Spring here?
In preparation for developing customer relationship managemnet (CRM), working with customer and account access patterns familiarizes myself with the typical access patterns.
It helps prepare me for the language I'm expected to see in my day to day career. It also builds the good habits to build strong and safe CRM applications. 