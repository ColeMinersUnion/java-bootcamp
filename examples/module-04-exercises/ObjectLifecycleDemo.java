public class ObjectLifecycleDemo {
    static class Person {
        final String name;

        Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person first = new Person("Aman"); // create + reference
        Person alias = first;
        // TODO: Person alias = first;  (second reference, same object)

        System.out.println(
                "Same object: " + (first == alias));

        // TODO: first = null;  (object remains reachable through alias)
        first = null;
        System.out.println(
                "Still reachable through alias: " + alias.name);

        // TODO: alias = null;  (no strong references remain)
        alias = null;
        System.out.println(
                "No strong references remain; object is GC-eligible.");

        // TODO: System.gc();  request only; JVM may ignore or delay it
        System.gc();
        System.out.println("GC requested, not guaranteed.");
    }
}