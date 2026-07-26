import java.util.List;

public class FilterSalaryDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        // TODO: stream pipeline — filter salary > 60_000, collect to List<Employee>
        List<Employee> highEarners = employees.stream().filter(e -> e.salary() > 60_000.00).toList();
                // TODO: .filter(...)
                // TODO: .toList()


        System.out.println("Employees above 60000:");
        highEarners.forEach(employee ->
                System.out.printf("%s - %.0f%n",
                        employee.name(), employee.salary()));

        System.out.println("Source size: " + employees.size());
        System.out.println("Filtered size: " + highEarners.size());
    }
}