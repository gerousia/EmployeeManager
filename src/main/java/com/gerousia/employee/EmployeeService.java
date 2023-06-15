package com.gerousia.employee;

import com.gerousia.database.CSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private List<Employee> EmployeeList;
    private static final String filename = "src/main/employee-database.csv";

    public EmployeeService() throws FileNotFoundException {
        this.loadEmployeesFromCSV(filename);
    }

    public void loadEmployeesFromCSV(String file) throws
            FileNotFoundException {
        EmployeeList = CSV.loadCSV(file, Employee.class, CSVEmployeeBeanConverter.class);
    }

    public void saveEmployeesToCSV() throws
            CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        CSV.saveCSV(filename, EmployeeList);
    }

    public void getEmployeeList() {
        EmployeeList.forEach(employee -> System.out.printf(
                "%-10d %-15s %-15s\n",
                employee.getEmployeeID(),
                employee.getEmployeeLastName(),
                employee.getEmployeeFirstName()
        ));
    }

    public Employee getEmployeeFromList(int employeeID) {
        Optional<Employee> person = EmployeeList.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .findFirst();

        return person.orElse(null);
    }

    public Employee createDefaultEmployee(String lastName, String firstName) {
        return Employee.builder()
                .employeeID(generateNewEmployeeID())
                .employeeLastName(lastName)
                .employeeFirstName(firstName)
                .build();
    }

    public void addEmployeeToList(Employee employee) {
        EmployeeList.add(employee);
    }

    private int generateNewEmployeeID() {
        return EmployeeList.stream()
                .max(Comparator.comparing(Employee::getEmployeeID))
                .map(Employee::getEmployeeID)
                .get() + 1;
    }

    public void deleteEmployeeFromList(int employeeID) {
        this.EmployeeList.remove(getEmployeeFromList(employeeID));
    }

    public boolean containsEmployeeByID(int employeeID) {
        return EmployeeList.stream()
                .anyMatch(employee -> employee.getEmployeeID() == employeeID);
    }

    public boolean containsEmployeeByName(String name) {
        return EmployeeList.stream()
                .anyMatch(employee ->
                        employee.getEmployeeLastName().equalsIgnoreCase(name)
                                || employee.getEmployeeFirstName().equalsIgnoreCase(name));
    }

    public void searchEmployeeByID(int employeeID) {
        EmployeeList.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .forEach(System.out::println);
    }

    public void searchEmployeeByName(String name) {
        EmployeeList.stream()
                .filter(employee ->
                        employee.getEmployeeLastName().equalsIgnoreCase(name)
                        || employee.getEmployeeFirstName().equalsIgnoreCase(name))
                .forEach(System.out::println);
    }
}
