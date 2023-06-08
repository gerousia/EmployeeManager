package com.gerousia.employee;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @CsvBindByName(column = "Employee #", required = true)
    private int employeeID;

    @CsvBindByName(column = "Last Name", required = true)
    private String employeeLastName;

    @CsvBindByName(column = "First Name", required = true)
    private String employeeFirstName;

    @CsvBindByName(column = "Birthday", required = true)
    @CsvDate("MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @CsvBindByName(column = "Address", required = true)
    private String employeeAddress;

    @CsvBindByName(column = "Phone Number", required = true)
    private String employeePhoneNumber;

    @CsvBindByName(column = "SSS #", required = true)
    private String numberSSS;

    @CsvBindByName(column = "Philhealth #", required = true)
    private String numberPHIC;

    @CsvBindByName(column = "TIN #", required = true)
    private String numberTIN;

    @CsvBindByName(column = "Pag-ibig #", required = true)
    private String numberHDMF;

    @CsvBindByName(column = "Status", required = true)
    private String employmentStatus;

    @CsvBindByName(column = "Position", required = true)
    private String employeePosition;

    @CsvBindByName(column = "Immediate Supervisor", required = true)
    private String immediateSupervisor;

    @CsvBindByName(column = "Basic Salary", required = true)
    @CsvNumber("###,###,###")
    private double basicSalary;

    @CsvBindByName(column = "Rice Subsidy", required = true)
    @CsvNumber("###,###,###")
    private double riceSubsidy;

    @CsvBindByName(column = "Phone Allowance", required = true)
    @CsvNumber("###,###,###")
    private double phoneAllowance;

    @CsvBindByName(column = "Clothing Allowance", required = true)
    @CsvNumber("###,###,###")
    private double clothingAllowance;

    @CsvBindByName(column = "Gross Semi-monthly Rate", required = true)
    @CsvNumber("###,###,###")
    private double grossSemiMonthlyRate;

    @CsvBindByName(column = "Hourly Rate", required = true)
    @CsvNumber("###,###,###")
    private double hourlyRate;

    @Override
    public String toString() {
        return String.format("%-10d %-15s %-15s"
                , this.employeeID
                , this.employeeLastName
                , this.employeeFirstName);
    }
}