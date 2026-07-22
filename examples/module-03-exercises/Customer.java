public class Customer implements Printable {
    private final String id;
    private final String name;

    public Customer(String my_id, String name) {
        this.id = my_id;
        this.name = name;
    }

    @Override
    public void printDetails() {
        // TODO: printf "Customer %s: %s%n" with id and name
        System.out.printf("Customer %s: %s%n", id, name);
    }
}