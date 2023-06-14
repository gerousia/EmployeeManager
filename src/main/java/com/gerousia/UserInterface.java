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

        this.viewCommands();
        while (true) {
            System.out.println();
            System.out.print("Command: ");
            String command = in.nextLine();
            System.out.println();

            switch (command) {
                case "0" -> this.viewCommands();
                case "1" -> this.viewEmployeeList();
                case "2" -> this.searchEmployeeListID();
                case "3" -> this.createNewEmployee();
                case "4" -> this.deleteEmployee();
                case "x" -> this.exit();
                default -> System.out.println("Try again.");
            }
        }
    }

    private void exit() throws CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        this.employeeService.saveEmployeesToCSV();
        System.exit(0);
    }

    public void viewCommands() {
        System.out.println();
        System.out.println("0: List Commands");
        System.out.println("1: List All Employees");
        System.out.println("2: Search Employee");
        System.out.println("3: Add Employees");
        System.out.println("4: Remove Employees");
        System.out.println("x: Save and Quit");
        System.out.println();
    }
     public void viewEmployeeList() {
        System.out.printf("%-10s %-15s %-15s%n", "ID", "LAST NAME", "FIRST NAME");
        //this.printDash(40);
        this.employeeService.toString();
    }

    private void searchEmployeeListID() {
        int employee = getIntegerInput("Search Employee ID:");

        if (this.employeeService.containsEmployeeID(Integer.valueOf(employee))) {
            this.employeeService.searchEmployee(Integer.valueOf(employee));
        } else {
            printError("No such entry.");
        }
    }

    private void searchEmployeeListName() {
        String employee = getStringInput("Search Employee Name: ");

        if (this.employeeService.containsEmployeeName(employee)) {
            this.employeeService.searchEmployee(employee);
        } else {
            printError("No such entry.");
        }
    }

    private void createNewEmployee() {
        this.employeeService.addEmployeeToList(this.employeeService.createDefaultEmployee(
                getStringInput("Enter Last Name: "),
                getStringInput("Enter First Name: ")
        ));
    }


    private void deleteEmployee() {
        this.employeeService.deleteEmployeeFromList(
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
        } while (!isValid("Invalid input. Please enter an integer.", input));
        return Integer.parseInt(input);
    }

    public boolean isValid(String message, String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println();
            return false;
        }
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }
}



