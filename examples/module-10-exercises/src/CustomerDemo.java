/**
 * Demo showcasing both traditional Customer class and CustomerRecord
 * with the example: CUS-1001, Amina Khan, ACTIVE
 */
public class CustomerDemo {
    public static void main(String[] args) {
        // Using traditional Customer class
        System.out.println("=== Traditional Customer Class ===");
        Customer classCustomer = new Customer("CUS-1001", "Amina Khan", "ACTIVE");
        System.out.println("Customer class instance: " + classCustomer);

        // Demonstrating mutability
        classCustomer.setStatus("INACTIVE");
        System.out.println("After status update: " + classCustomer);
        classCustomer.setStatus("ACTIVE");

        System.out.println();

        // Using Java 21 CustomerRecord
        System.out.println("=== Java 21 CustomerRecord ===");
        CustomerRecord recordCustomer = new CustomerRecord("CUS-1001", "Amina Khan", "ACTIVE");
        System.out.println("CustomerRecord instance: " + recordCustomer);
        System.out.println("  id: " + recordCustomer.id());
        System.out.println("  fullName: " + recordCustomer.fullName());
        System.out.println("  status: " + recordCustomer.status());

        System.out.println();

        // Demonstrating immutability
        System.out.println("=== Immutability Demo ===");
        CustomerRecord updatedRecord = new CustomerRecord("CUS-1001", "Amina Khan", "INACTIVE");
        System.out.println("Original record: " + recordCustomer);
        System.out.println("New record: " + updatedRecord);
        System.out.println("Are they equal? " + recordCustomer.equals(updatedRecord));

        System.out.println();

        // Demonstrating record equality and hashCode
        System.out.println("=== Equality and HashCode Demo ===");
        CustomerRecord record1 = new CustomerRecord("CUS-1001", "Amina Khan", "ACTIVE");
        CustomerRecord record2 = new CustomerRecord("CUS-1001", "Amina Khan", "ACTIVE");
        System.out.println("record1: " + record1);
        System.out.println("record2: " + record2);
        System.out.println("record1.equals(record2): " + record1.equals(record2));
        System.out.println("record1.hashCode() == record2.hashCode(): " + (record1.hashCode() == record2.hashCode()));
    }
}

