package com.gerousia.employee;

import com.gerousia.database.CSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EmployeeList {
    private List<Employee> employees;
    private EmployeeService employeeService;
    private static final String FILENAME = "employeedatabase.csv";

    public EmployeeList() throws FileNotFoundException {
        this.loadList();
        this.employeeService = new EmployeeService(this.employees);
    }

    public void loadList() throws FileNotFoundException {
        this.employees = CSV.loadCSV(FILENAME, Employee.class);
    }

    public void saveList() throws CsvRequiredFieldEmptyException
            , CsvDataTypeMismatchException
            , IOException {
        CSV.saveCSV(FILENAME, employees);
    }

    public void addEmployee(String lastName, String firstName) {
        this.employeeService.add(lastName, firstName);
    }

    public void searchEmployee(int employeeID) {
        this.employees.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        employees.forEach(System.out::println);
        return "\n";
    }

}
