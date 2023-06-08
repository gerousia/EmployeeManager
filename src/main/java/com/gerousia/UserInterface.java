package com.gerousia;

import com.gerousia.employee.EmployeeList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {
    private Scanner in;
    private EmployeeList employeeList;

    public UserInterface(Scanner scanner) throws FileNotFoundException {
        this.in = scanner;
        this.employeeList = new EmployeeList();
    }

    public void start() {
        while (true) {
            System.out.print("Command: ");
            String command = in.nextLine();

            switch (command) {
                case "1" -> {
                    System.out.printf("%-10s %-15s %-15s%n", "ID", "LAST NAME", "FIRST NAME");
                    this.printDash(40);
                    employeeList.toString();
                } case "2" -> {
                    System.out.print("Last Name: ");
                    String lastName = in.nextLine();

                    System.out.print("First Name: ");
                    String firstName = in.nextLine();

                    employeeList.addEmployee(lastName, firstName);
                } case "3" -> {
                    System.out.print("Last Name: ");
                    String lastName = in.nextLine();

                    System.out.print("First Name: ");
                    String firstName = in.nextLine();

                    employeeList.addEmployee(lastName, firstName);
                }
                default -> {}
            }
        }
    }

    public void printDash(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
