/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import com.mycompany.motorph.employee.EmployeeInformation;
import com.mycompany.motorph.calculation.HealthInsurancesDeduction;
import com.mycompany.motorph.model.Employee;
import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.data.EmployeeDataReader;
import com.mycompany.motorph.calculation.WithholdingTaxCalculation;
import com.mycompany.motorph.calculation.SSSDeduction;
import com.mycompany.motorph.calculation.WageCalculation;
import com.mycompany.motorph.calculation.NetWageCalculation;
import com.mycompany.motorph.calculation.GrossWageCalculation;

import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that manages the MotorPH application/payroll system.
 * <p>
 * Displays menus, handles user input, and manages functionalities.
 *
 * @author Lance1
 */
public class MotorPHManager {

    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsuranceDeduction;
    private final WithholdingTaxCalculation withholdingTaxCalculation;
//
//    // Path to the employee data file
//    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";

    /**
     * Constructor for MotorPHManager.
     * <p>
     * Initializes dependencies.
     */
    public MotorPHManager() {
        this.sssDeduction = new SSSDeduction();
        this.healthInsuranceDeduction = new HealthInsurancesDeduction();
        this.withholdingTaxCalculation = new WithholdingTaxCalculation(sssDeduction, healthInsuranceDeduction);
    }

//    /**
//     * Shows either gross or net wage calculation for an employee.
//     *
//     * @param scanner Scanner for user input
//     * @param isGross Indicates whether to calculate gross or net wage
//     * @throws ParseException If date parsing error occurs
//     * @throws IOException If I/O error occurs
//     */
//    public void showWage(Scanner scanner, boolean isGross) throws ParseException, IOException {
//        try {
//            // Prompt the user for the employee number
//            int employeeNumber = getValidEmployeeNumberFromUser(scanner);
//            DateRange dateRange = getDateRangeFromUser(scanner);
//
//            // Retrieve employee information from the data source
//            Employee employeeInfo = getEmployeeInfo(employeeNumber, EMPLOYEES_DATA_PATH);
//            WageCalculation wageCalculation = createWageCalculation(isGross, employeeNumber, employeeInfo);
//
//            wageCalculation.showWage(employeeNumber, dateRange);
//
//            // Discard any remaining input and prompt the user to go back to the main menu
//            scanner.nextLine();
//        } catch (RuntimeException e) {
//            // Throw RuntimeException with an error message
//            throw new RuntimeException("Employee not found.");
//        }
//    }

    /**
     * Prompts the user to enter start and end dates, then creates a DateRange
     * object.
     *
     * @param scanner Scanner for user input
     * @return DateRange object of the inputted date range
     * @throws ParseException If date parsing error occurs
     */
    private DateRange getDateRangeFromUser(Scanner scanner) throws ParseException {
        // Prompt the user to enter start and end dates
        System.out.print("Enter start date (MM/dd): ");
        String startDateString = scanner.next();
        System.out.print("Enter end date (MM/dd): ");
        String endDateString = scanner.next();

        // Create a DateRange object based on the inputted start and end date
        return DateRange.createDateRange(startDateString, endDateString);
    }

    /**
     * Creates a WageCalculation object based on the type of wage to calculate.
     *
     * @param isGross Indicates whether to calculate gross or net wage
     * @param employeeNumber Employee number
     * @param employeeInfo Employee object containing employee information
     * @return WageCalculation object containing either gross or net wage
     * calculation
     */
    private WageCalculation createWageCalculation(boolean isGross, int employeeNumber, Employee employeeInfo) {
        // If the type of wage to show is gross wage based on the isGross value given
        if (isGross) {
            // Return gross wage calculation
            return new GrossWageCalculation(employeeNumber,
                    employeeInfo.getLastName(),
                    employeeInfo.getFirstName(),
                    employeeInfo.getBirthdateAsString());

            // Else
        } else {
            // return net wage calculation
            return new NetWageCalculation(employeeNumber,
                    employeeInfo.getLastName(),
                    employeeInfo.getFirstName(),
                    employeeInfo.getBirthdateAsString(),
                    sssDeduction,
                    healthInsuranceDeduction,
                    withholdingTaxCalculation);
        }
    }

    /**
     * Gets employee information from the employee data.
     *
     * @param employeeNumber For getting the information of the employee with
     * the inputted employee number
     * @param filePath File path to the employee data file
     * @return Employee information
     * @throws IOException If I/O error occurs
     * @throws ParseException If date parsing error occurs
     */
    private Employee getEmployeeInfo(int employeeNumber, String filePath) throws IOException, ParseException {
        // Create instance of EmployeeDataReader
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();

        // Get employee information using inputted employee number and specified file path
        return employeeDataReader.getEmployeeInfo(employeeNumber, filePath);
    }
}
