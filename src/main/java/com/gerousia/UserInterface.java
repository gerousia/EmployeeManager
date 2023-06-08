package com.gerousia;

import com.gerousia.employee.Employee;
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

        this.printCommand();
        while (true) {
            System.out.println();
            System.out.print("Command: ");
            String command = in.nextLine();
            System.out.println();

            switch (command) {
                case "0" -> this.printCommand();
                case "1" -> this.printList();
                case "2" -> this.search();
                case "3" -> this.view();
                case "4" -> this.add();
                case "5" -> this.edit();
                case "6" -> this.remove();
                case "x" -> this.exit();
                default -> System.out.println("Try again.");
            }
        }
    }

    private void search() {
        System.out.print("Search Employee: ");
        String employee = in.nextLine();

        if (this.employeeService.containsEmployee(employee)) {
            this.employeeService.searchEmployee(employee);
        } else if (this.employeeService.containsEmployee(Integer.valueOf(employee))) {
            this.employeeService.searchEmployee(Integer.valueOf(employee));
        } else {
            System.out.println("ERROR: No such entry.");
        }
    }

    private void view() {
        System.out.print("View Employee ID: ");
        int employeeID = Integer.parseInt(in.nextLine());

        if (!this.employeeService.containsEmployee(employeeID)) {
            System.out.println("ERROR: Employee not found");
            return;
        }

        Employee employee = this.employeeService.getEmployee(employeeID);

        System.out.printf("\tEmployee ID:%n\t\t %s%n", employee.getEmployeeID());
        System.out.printf("\tEmployee Name:%n\t\t %s, %s%n", employee.getEmployeeLastName(), employee.getEmployeeFirstName());
        System.out.printf("\tAddress:%n\t\t %s%n", employee.getEmployeeAddress());
        System.out.printf("\tPhone Number:%n\t\t %s%n", employee.getEmployeePhoneNumber());
        System.out.println();
        System.out.printf("\tSSS Number:%n\t\t %s%n", employee.getNumberSSS());
        System.out.printf("\tPHIC Number:%n\t\t %s%n", employee.getNumberPHIC());
        System.out.printf("\tTIN Number:%n\t\t %s%n", employee.getNumberTIN());
        System.out.printf("\tHDMF Number:%n\t\t %s%n", employee.getNumberHDMF());
        System.out.println();
        System.out.printf("\tEmployment Status:%n\t\t %s%n", employee.getEmploymentStatus());
        System.out.printf("\tPosition:%n\t\t %s%n", employee.getEmployeePosition());
        System.out.printf("\tImmediate Supervisor:%n\t\t %s%n", employee.getImmediateSupervisor());
        System.out.println();
        System.out.printf("\tBasic Salary:%n\t %11.2f%n", employee.getBasicSalary());
        System.out.printf("\tRice Subsidy:%n\t %11.2f%n", employee.getRiceSubsidy());
        System.out.printf("\tPhone Allowance:%n\t %11.2f%n", employee.getPhoneAllowance());
        System.out.printf("\tClothing Allowance:%n\t %11.2f%n", employee.getClothingAllowance());
        System.out.printf("\tGross Semi-Monthly Rate:%n\t %11.2f%n", employee.getGrossSemiMonthlyRate());
        System.out.printf("\tHourly Rate:%n\t %11.2f%n", employee.getHourlyRate());
    }
    private void add() {
        System.out.print("Enter Last Name: ");
        String lastName = in.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = in.nextLine();
        this.employeeService.addEmployee(lastName, firstName);
    }

    private void edit() {
        System.out.print("Enter Employee ID: ");
        int employeeID = Integer.parseInt(in.nextLine());

        if (!this.employeeService.containsEmployee(employeeID)) {
            return;
        }

        System.out.print("Enter Last Name: ");
        String lastName = in.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = in.nextLine();

        System.out.print("Enter Birth Date (YYYY-MM-DD): ");
        String birthDay = in.nextLine();

        System.out.print("Enter Employee Address: ");
        String address = in.nextLine();

        System.out.print("Enter Employee Phone Number (###-###-###): ");
        String phoneNumber = in.nextLine();

        System.out.print("Enter Employee SSS (##-#######-#): ");
        String sss = in.nextLine();

        System.out.print("Enter Employee PhilHealth (12-Digits): ");
        String phic = in.nextLine();

        System.out.print("Enter Employee TIN (###-###-###-###): ");
        String tin = in.nextLine();

        System.out.print("Enter Employee PagIBIG (12-Digits): ");
        String hdmf = in.nextLine();

        System.out.print("Enter Employment Status: ");
        String status = in.nextLine();

        System.out.print("Enter Employee Position: ");
        String position = in.nextLine();

        System.out.print("Enter Immediate Supervisor: ");
        String supervisor = in.nextLine();

        System.out.print("Enter Basic Salary: ");
        double basicSalary = Double.parseDouble(in.nextLine());

        System.out.print("Enter Rice Subsidy: ");
        double riceSubsidy = Double.parseDouble(in.nextLine());

        System.out.print("Enter Phone Allowance: ");
        double phoneAllowance = Double.parseDouble(in.nextLine());

        System.out.print("Enter Clothing Allowance:");
        double clothingAllowance = Double.parseDouble(in.nextLine());

        System.out.print("Enter Gross Semi-Monthly Rate: ");
        double monthlyRate = Double.parseDouble(in.nextLine());

        System.out.print("Enter Hourly Rate:");
        double hourlyRate = Double.parseDouble(in.nextLine());

        this.employeeService.editEmployee(
                employeeID,
                lastName,
                firstName,
                birthDay,
                address,
                phoneNumber,
                sss,
                phic,
                tin,
                hdmf,
                status,
                position,
                supervisor,
                basicSalary,
                riceSubsidy,
                phoneAllowance,
                clothingAllowance,
                monthlyRate,
                hourlyRate);
    }

    private void remove() {
        System.out.print("Enter Employee ID: ");
        int employee = Integer.parseInt(in.nextLine());
        this.employeeService.removeEmployee(employee);
    }

    private void exit() throws CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        this.employeeService.saveList();
        System.exit(0);
    }
    private void printList() {
        System.out.printf("%-10s %-15s %-15s%n", "ID", "LAST NAME", "FIRST NAME");
        this.printDash(40);
        this.employeeService.toString();
    }

    public void printDash(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printCommand() {
        System.out.println();
        System.out.println("0: List Commands");
        System.out.println("1: List All Employees");
        System.out.println("2: Search Employee");
        System.out.println("3: View Employee");
        System.out.println("4: Add Employees");
        System.out.println("5: Edit Employee");
        System.out.println("6: Remove Employees");
        System.out.println("x: Save and Quit");
        System.out.println();
    }
}
