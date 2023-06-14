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
    private static final String FILENAME = "employeedatabase.csv";

    public EmployeeService() throws FileNotFoundException {
        this.loadEmployeesFromCSV(FILENAME);
    }

    public void loadEmployeesFromCSV(String file) throws
            FileNotFoundException {
        this.EmployeeList = CSV.loadCSV(file, Employee.class, CSVEmployeeBeanConverter.class);
    }

    public void saveEmployeesToCSV() throws
            CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        CSV.saveCSV(FILENAME, EmployeeList);
    }

    public Employee createDefaultEmployee(String lastName, String firstName) {
        return Employee.builder()
                .employeeID(this.generateNewEmployeeID())
                .employeeLastName(lastName)
                .employeeFirstName(firstName)
                .build();
    }

    public Employee getEmployeeFromList(int employeeID) {
        Optional<Employee> person = EmployeeList.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .findFirst();

        return person.orElse(null);
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

    public boolean containsEmployeeID(int employeeID) {
        return this.EmployeeList.stream()
                .anyMatch(employee -> employee.getEmployeeID() == employeeID);
    }

    public boolean containsEmployeeName(String name) {
        return this.EmployeeList.stream()
                .anyMatch(employee -> employee.getEmployeeLastName()
                        .concat(employee.getEmployeeFirstName())
                        .equalsIgnoreCase(name));
    }

    public void searchEmployee(int employeeID) {
        this.EmployeeList.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .forEach(System.out::println);
    }

    public void searchEmployee(String name) {
        this.EmployeeList.stream()
                .filter(employee -> employee.getEmployeeLastName().equalsIgnoreCase(name))
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        EmployeeList.forEach(System.out::println);
        return "\n";
    }

}
