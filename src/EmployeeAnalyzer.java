import employee.Employee;

import java.sql.Array;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeAnalyzer {

    public long countEmployees(List<Employee> employees, int years) {;
        long yearsWorkingInTheCompany = employees
                .stream()
                .filter(employee -> 2023 - employee.getStartingYear()>=years)
                .count();
        return yearsWorkingInTheCompany;
    }

    public List<String> findEmployeeBySalary(List<Employee> employees, int salary) {
        List <String> employeeBySalary = new ArrayList<>();
        employees.stream()
                .filter(employee -> employee.getSalary()>salary)
                .forEach(employee -> employeeBySalary.add(employee.getName().toString()));
        return employeeBySalary;
    }

    public List<Employee> findOldestEmployees(List<Employee> employees, int numberOfEmployees) {
        List <Employee> oldestEmployees = new ArrayList<>();
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge)
                        .reversed())
                .limit(numberOfEmployees)
                .forEach(oldestEmployees::add);
        return oldestEmployees;
    }
    public Optional<Employee> findFirstEmployeeByAge(List<Employee> employees, int age) {
        Optional <Employee> employeeOlderThan = employees
                .stream()
                .sorted(Comparator
                        .comparing(Employee::getAge)
                        .reversed())
                .findFirst();
        return employeeOlderThan;
    }

    public Double findAverageSalary(List<Employee> employees) {
        return Math.floor(employees.stream().mapToInt(employee -> employee.getSalary()).average().getAsDouble()*100) / 100;
    }

    public List<String> findCommonNames(List<Employee> firstDepartment, List<Employee> secondDepartment) {
        List<String> list1 = firstDepartment.stream().map(employee -> employee.getFirstName()).toList();
        List<String> list2 = secondDepartment.stream().map(employee -> employee.getFirstName()).toList();

        return list1.stream().filter(list2::contains).distinct().collect(Collectors.toList());
    }
}