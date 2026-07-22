public class InterfaceDemo {
    public static void main(String[] args) {
        // TODO: Printable reference to new Customer("C101", "Aman Singh")
        Printable printable = new Customer("C101", "Aman Singh");
        Printable next_print = new Customer("C102", "Cole Hansen");
        printable.printDetails();
        next_print.printDetails();
    }
}