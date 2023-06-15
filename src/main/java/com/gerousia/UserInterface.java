package com.gerousia;

import com.gerousia.employee.EmployeeService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    private final Scanner in;
    private final EmployeeService employeeService;

    public UserInterface(Scanner scanner) throws FileNotFoundException {
        this.in = scanner;
        this.employeeService = new EmployeeService();
    }

    public void start() throws CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {

        viewCommands();
        while (true) {
            System.out.println();
            System.out.print("Command: ");
            String command = in.nextLine();
            System.out.println();

            switch (command) {
                case "0" -> viewCommands();
                case "1" -> viewEmployeeList();
                case "2" -> searchEmployeeListByID();
                case "3" -> searchEmployeeListByName();
                case "4" -> createNewEmployee();
                case "5" -> deleteEmployee();
                case "x" -> exit();
                default -> System.out.println("Try again.");
            }
        }
    }

    private void exit() throws CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        employeeService.saveEmployeesToCSV();
        System.exit(0);
    }

    public void viewCommands() {
        System.out.println();
        System.out.println("0: List Commands");
        System.out.println("1: List All Employees");
        System.out.println("2: Search Employee By ID");
        System.out.println("3: Search Employee By Name");
        System.out.println("4: Add Employees");
        System.out.println("5: Remove Employees");
        System.out.println("x: Save and Quit");
        System.out.println();
    }
     public void viewEmployeeList() {
        System.out.printf("%-10s %-15s %-15s%n", "ID", "LAST NAME", "FIRST NAME");
        printDivider(40);
        employeeService.getEmployeeList();
    }

    private void printDivider(int n) {
        StringBuilder dash = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            dash.append("-");
        }
        System.out.println(dash);
    }

    private void searchEmployeeListByID() {
        int employee = getIntegerInput("Search Employee ID:");

        if (employeeService.containsEmployeeByID(Integer.valueOf(employee))) {
            employeeService.searchEmployeeByID(Integer.valueOf(employee));
        } else {
            printInputError("No such entry.");
        }
    }

    private void searchEmployeeListByName() {
        String employee = getStringInput("Search Employee Name: ");

        if (employeeService.containsEmployeeByName(employee)) {
            employeeService.searchEmployeeByName(employee);
        } else {
            printInputError("No such entry.");
        }
    }

    private void createNewEmployee() {
        employeeService.addEmployeeToList(employeeService.createDefaultEmployee(
                getStringInput("Enter Last Name: "),
                getStringInput("Enter First Name: ")
        ));
    }


    private void deleteEmployee() {
        employeeService.deleteEmployeeFromList(
                getIntegerInput("Enter Employee ID: ")
        );
    }

    public String getStringInput(String message) {
        System.out.print(message);
        return in.nextLine();
    }

    public int getIntegerInput(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = in.nextLine();
        } while (!isInputValid("Invalid input. Please enter an integer.", input));
        return Integer.parseInt(input);
    }

    public boolean isInputValid(String message, String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println();
            return false;
        }
    }

    public void printInputError(String message) {
        System.out.println("Error: " + message);
    }
}



