/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that calculates wages based on hours worked.
 * <p>
 * This class reads employee and attendance data from files, calculates total
 * hours worked, late arrival deduction, and wage based on both, and displays
 * the employee's calculated wage
 *
 * @author Lance1
 */
abstract class WageCalculation {

    private final TimeCalculation timeCalculation;

    // Paths to data files
    private static final String EMPLOYEES_DATA_PATH = "src/main/resources/data/employee_information.csv";
    private static final String ATTENDANCE_DATA_PATH = "src/main/resources/data/employee_attendance.csv";

    // Expected total number of data per row from the data
    private static final int EMPLOYEE_EXPECTED_DATA_LENGTH = 19;
    private static final int EMPLOYEE_NUM_INDEX = 0;
    private static final int HOURLY_RATE_INDEX = 18;

    /**
     * Constructor for WageCalculation.
     * <p>
     * Initializes an instance of TimeCalculation.
     */
    public WageCalculation() {
        this.timeCalculation = new TimeCalculation();
    }

    /**
     * Calculates and displays wage for an employee.
     *
     * @param employeeNumber The employee number for which wage is calculated
     * @param dateRange The date range for which wage is calculated
     * @return The wage information
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    public List<String> showWage(int employeeNumber, DateRange dateRange) throws IOException, CsvValidationException, ParseException {
        List<String[]> attendanceDataList = readAttendanceData();

        // Calculate total hours worked 
        double hoursWorked = calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        // Get the hourly rate for the employee
        double hourlyRate = getHourlyRate(employeeNumber);

        // Calculate the assumed hours worked based on the provided date range
        double assumedHoursWorked = calculateAssumedHoursWorked(dateRange);

        // Calculate late arrival deduction
        double lateArrivalDeduction = calculateLateArrivalDeduction(attendanceDataList, employeeNumber, dateRange);

        // If an employee with the inputted employee number is found in the attendance data
        if (hoursWorked > 0) {
            // Calculate wage based on hours worked from the attendance data
            getWageInformation(employeeNumber, hourlyRate, hoursWorked, lateArrivalDeduction);
        }

        // Else, use assumed hours worked of 9.0 per day to calculate wage
        return getWageInformation(employeeNumber, hourlyRate, assumedHoursWorked, lateArrivalDeduction);
    }

    /**
     * Abstract method to calculate late arrival deduction for an employee.
     *
     * @param attendanceDataList The list containing attendance data
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @return The late arrival deduction amount
     * @throws ParseException If a date parsing error occurs
     */
    protected abstract double calculateLateArrivalDeduction(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException;

    /**
     * Abstract method to calculate wage based on hourly rate and hours worked.
     *
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction for the employee
     * @return The calculated wage
     */
    protected abstract double calculateWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction);

    /**
     * Abstract method to display wage for an employee.
     *
     * @param employeeNumber The employee number
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction
     * @return A list of strings containing wage information
     */
    protected abstract List<String> getWageInformation(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction);

    /**
     * Gets the hourly rate of an employee from the employee data file.
     *
     * @param employeeNumber The employee number
     * @return The hourly rate of the employee
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    private double getHourlyRate(int employeeNumber) throws IOException, CsvValidationException, ParseException {
        // Open the file for reading
        try (CSVReader reader = new CSVReader(new FileReader(EMPLOYEES_DATA_PATH))) {
            String[] data;
            // Skip header
            reader.readNext();
            // Read data per row from the file
            while ((data = reader.readNext()) != null) {
                // If the data has the expected length per row and has the matching employee number from the inputted one
                if (data.length == EMPLOYEE_EXPECTED_DATA_LENGTH && Integer.parseInt(data[EMPLOYEE_NUM_INDEX]) == employeeNumber) {
                    // Return the hourly rate of the employee
                    return Double.parseDouble(data[HOURLY_RATE_INDEX]);
                }
            }
        }

        // Return 0 as default if the employee is not found
        return 0.0;
    }

    /**
     * Calculates the total hours worked by an employee within an inputted date
     * range.
     *
     * @param attendanceDataList The list containing attendance data
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @return The total hours worked
     * @throws ParseException If a date parsing error occurs
     */
    private double calculateTotalHoursWorked(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        return timeCalculation.calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);
    }

    /**
     * Calculates assumed hours worked based on a date range.
     *
     * @param dateRange The date range
     * @return The assumed hours worked
     */
    private double calculateAssumedHoursWorked(DateRange dateRange) {
        return timeCalculation.calculateAssumedHoursWorked(dateRange);
    }

    /**
     * Reads attendance data from the file and returns it as a list.
     *
     * @return The list of attendance data
     * @throws IOException If an I/O error occurs
     */
    private List<String[]> readAttendanceData() throws IOException, CsvValidationException {
        // Initialize a list
        List<String[]> attendanceDataList = new ArrayList<>();

        // Open the file for reading
        try (CSVReader reader = new CSVReader(new FileReader(ATTENDANCE_DATA_PATH))) {
            String[] data;
            // Skip header
            reader.readNext();
            // Read data per row from the file
            while ((data = reader.readNext()) != null) {
                // Add each row of attendance data to the list
                attendanceDataList.add(data);
            }
        }

        return attendanceDataList;
    }
}
