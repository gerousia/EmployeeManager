package com.gerousia.employee;

import com.gerousia.database.CSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private List<Employee> employees;
    private static final String FILENAME = "employeedatabase.csv";

    public EmployeeService() throws FileNotFoundException {
        this.loadList();
    }

    public void loadList() throws FileNotFoundException {
        this.employees = CSV.loadCSV(FILENAME, Employee.class);
    }

    public void saveList() throws CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException,
            IOException {
        CSV.saveCSV(FILENAME, employees);
    }

    public void addEmployee(String lastName, String firstName) {
        employees.add(new Employee(
                employees.stream()
                .max(Comparator.comparing(Employee::getEmployeeID))
                .map(Employee::getEmployeeID)
                .get() + 1,
                lastName,
                firstName,
                LocalDate.parse("2000-01-01"),
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                0,
                0,
                0,
                0,
                0,
                0 )
        );
    }

    public void editEmployee(int employeeID,
                             String lastName,
                             String firstName,
                             String birthDay,
                             String address,
                             String phoneNumber,
                             String sss,
                             String phic,
                             String tin,
                             String hdmf,
                             String status,
                             String position,
                             String supervisor,
                             double basicSalary,
                             double riceSubsidy,
                             double phoneAllowance,
                             double clothingAllowance,
                             double monthlyRate,
                             double hourlyRate) {

        this.editEmployeeBasicInformation(
                employeeID,
                lastName,
                firstName,
                birthDay,
                address,
                phoneNumber);

        this.editEmployeeGovernmentID(
                employeeID,
                sss,
                phic,
                tin,
                hdmf);

        this.editEmployeeEmployment(
                employeeID,
                status,
                position,
                supervisor);


        this.editEmployeeSalary(
                employeeID,
                basicSalary,
                riceSubsidy,
                phoneAllowance,
                clothingAllowance,
                monthlyRate,
                hourlyRate
        );
    }


    public void editEmployeeBasicInformation(int employeeID,
                                             String lastName,
                                             String firstName,
                                             String birthDay,
                                             String address,
                                             String phoneNumber) {
        if (containsEmployee(employeeID)) {
            this.employees.stream()
                    .filter(employee -> employee.getEmployeeID() == employeeID)
                    .forEach(employee -> {
                        employee.setEmployeeLastName(lastName.isEmpty() ? employee.getEmployeeLastName(): lastName);
                        employee.setEmployeeFirstName(firstName.isEmpty() ? employee.getEmployeeFirstName() : firstName);
                        employee.setDateOfBirth(birthDay.isEmpty() ? employee.getDateOfBirth() : LocalDate.parse(birthDay));
                        employee.setEmployeeAddress(address.isEmpty() ? employee.getEmployeeAddress() : address);
                        employee.setEmployeePhoneNumber(phoneNumber.isEmpty() ? employee.getEmployeePhoneNumber() : phoneNumber);

                    });
        }
    }

    public void editEmployeeGovernmentID(int employeeID,
                                         String sss,
                                         String phic,
                                         String tin,
                                         String hdmf) {
        if (containsEmployee(employeeID)) {
            this.employees.stream()
                    .filter(employee -> employee.getEmployeeID() == employeeID)
                    .forEach(employee -> {
                        employee.setNumberSSS(sss.isEmpty() ? employee.getNumberSSS() : sss);
                        employee.setNumberPHIC(phic.isEmpty() ? employee.getNumberPHIC() : phic);
                        employee.setNumberTIN(tin.isEmpty() ? employee.getNumberTIN() : tin);
                        employee.setNumberHDMF(hdmf.isEmpty() ? employee.getNumberHDMF() : hdmf);
                    });
        }
    }

    public void editEmployeeEmployment(int employeeID,
                                       String status,
                                       String position,
                                       String supervisor) {
        if (containsEmployee(employeeID)) {
            this.employees.stream()
                    .filter(employee -> employee.getEmployeeID() == employeeID)
                    .forEach(employee -> {
                        employee.setEmploymentStatus(status.isEmpty() ? employee.getEmploymentStatus() : status);
                        employee.setEmployeePosition(position.isEmpty() ? employee.getEmployeePosition() : position);
                        employee.setImmediateSupervisor(supervisor.isEmpty() ? employee.getImmediateSupervisor() : supervisor);
                    });
        }
    }

    public void editEmployeeSalary(int employeeID,
                                   double basicSalary,
                                   double riceSubsidy,
                                   double phoneAllowance,
                                   double clothingAllowance,
                                   double monthlyRate,
                                   double hourlyRate) {
        if (containsEmployee(employeeID)) {
            this.employees.stream()
                    .filter(employee -> employee.getEmployeeID() == employeeID)
                    .forEach(employee -> {
                        employee.setBasicSalary(basicSalary == 0.0 ? employee.getBasicSalary() : basicSalary);
                        employee.setRiceSubsidy(riceSubsidy == 0.0 ? employee.getRiceSubsidy() : riceSubsidy);
                        employee.setPhoneAllowance(phoneAllowance == 0.0 ? employee.getPhoneAllowance() : phoneAllowance);
                        employee.setClothingAllowance(clothingAllowance == 0.0 ? employee.getClothingAllowance() : clothingAllowance);
                        employee.setGrossSemiMonthlyRate(monthlyRate == 0.0 ? employee.getGrossSemiMonthlyRate() : monthlyRate);
                        employee.setHourlyRate(hourlyRate == 0.0 ? employee.getHourlyRate() : hourlyRate);
                    });
        }
    }

    public void removeEmployee(int employeeID) {
        if (this.containsEmployee(employeeID)) {
            this.employees = this.employees.stream()
                    .filter(employee -> employee.getEmployeeID() != employeeID)
                    .toList();
        }
    }

    public boolean containsEmployee(int employeeID) {
        return this.employees.stream()
                .anyMatch(employee -> employee.getEmployeeID() == employeeID);
    }

    public boolean containsEmployee(String name) {
        return this.employees.stream()
                .anyMatch(employee -> employee.getEmployeeLastName().equalsIgnoreCase(name));
    }

    public void searchEmployee(int employeeID) {
        this.employees.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .forEach(System.out::println);
    }

    public void searchEmployee(String name) {
        this.employees.stream()
                .filter(employee -> employee.getEmployeeLastName().equalsIgnoreCase(name))
                .forEach(System.out::println);
    }

    public Employee getEmployee(int employeeID) {
        Optional<Employee> person = employees.stream()
                .filter(employee -> employee.getEmployeeID() == employeeID)
                .findFirst();

        return person.orElse(null);
    }


    @Override
    public String toString() {
        employees.forEach(System.out::println);
        return "\n";
    }

}
