package com.gerousia.employee;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Employee {

    private int employeeID;
    private String employeeLastName;
    private String employeeFirstName;
    private LocalDate dateOfBirth;
    private String employeeAddress;
    private String employeePhoneNumber;
    private String numberSSS;
    private String numberPHIC;
    private String numberTIN;
    private String numberHDMF;
    private String employmentStatus;
    private String employeePosition;
    private String immediateSupervisor;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyRate;
    private double hourlyRate;


    @Override
    public String toString() {
        return String.format("%-10d %-15s %-15s"
                , this.employeeID
                , this.employeeLastName
                , this.employeeFirstName);
    }
}

