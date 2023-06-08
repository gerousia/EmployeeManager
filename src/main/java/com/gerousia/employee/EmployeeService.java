package com.gerousia.employee;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public void add(String lastName, String firstName) {
        employees.add(new Employee(employees.stream()
                .max(Comparator.comparing(Employee::getEmployeeID))
                .map(Employee::getEmployeeID)
                .get() + 1
                , lastName
                , firstName
                , LocalDate.parse("2000-01-01")
                , " "
                , " "
                , " "
                , " "
                , " "
                , " "
                , " "
                , " "
                , " "
                , 0
                , 0
                , 0
                , 0
                , 0
                , 0 )
        );
    }
}
