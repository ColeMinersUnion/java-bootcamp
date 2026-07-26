import java.util.List;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        long sequentialStart = System.nanoTime();
        // TODO: employees.stream() + filter salary > 60_000 + count()
        long sequentialCount = employees.stream().filter(e -> e.salary() > 60_000).count();
        long sequentialNanos = System.nanoTime() - sequentialStart;

        long parallelStart = System.nanoTime();
        // TODO: employees.parallelStream() + same filter + count()
        long parallelCount = employees.parallelStream().filter(e -> e.salary() > 60_000).count();
        long parallelNanos = System.nanoTime() - parallelStart;

        System.out.println("Sequential count: " + sequentialCount);
        System.out.println("Parallel count: " + parallelCount);
        System.out.println("Available processors: "
                + Runtime.getRuntime().availableProcessors());
        System.out.println("Sequential ns: " + sequentialNanos);
        System.out.println("Parallel ns: " + parallelNanos);
        System.out.println("Timing conclusion: none from one tiny run");
    }
}