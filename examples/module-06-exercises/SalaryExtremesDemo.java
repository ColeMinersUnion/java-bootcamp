import java.util.Comparator;
import java.util.List;

public class SalaryExtremesDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        // TODO: Comparator ascending by salary (hint: Comparator.comparingDouble(Employee::salary))
        Comparator<Employee> bySalary = Comparator.comparingDouble(Employee::salary);

        // TODO: stream + max(bySalary) + orElseThrow()
        Employee highest = employees.stream().max(bySalary).orElseThrow();
                // TODO: .max(bySalary)
                // TODO: .orElseThrow()


        // TODO: stream + min(bySalary) + orElseThrow()
        Employee lowest = employees.stream().min(bySalary).orElseThrow();
                // TODO: .min(bySalary)
                // TODO: .orElseThrow()


        System.out.printf("Highest: %s - %.0f%n",
                highest.name(), highest.salary());
        System.out.printf("Lowest: %s - %.0f%n",
                lowest.name(), lowest.salary());
    }
}