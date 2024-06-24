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
 * @author Lance
 */
public class WageCalculation {

    private final TimeCalculation timeCalculator;

    // Paths to data files
    private static final String EMPLOYEES_DATA_PATH = "src/main/resources/data/employee_information.csv";
    private static final String ATTENDANCE_DATA_PATH = "src/main/resources/data/employee_attendance.csv";

    // Constants for data indices and lengths
    private static final int EMPLOYEE_EXPECTED_DATA_LENGTH = 19;
    private static final int EMPLOYEE_NUM_INDEX = 0;
    private static final int HOURLY_RATE_INDEX = 18;

    /**
     * Constructor for WageCalculator.
     */
    public WageCalculation() {
        this.timeCalculator = new TimeCalculation();
    }

    /**
     * Calculates and displays the wage for an employee.
     *
     * @param employeeNumber The employee number for which wage is calculated
     * @param dateRange The date range for which wage is calculated
     * @return The wage information as a list of strings
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    public List<String> calculateWage(int employeeNumber, DateRange dateRange) throws IOException, CsvValidationException, ParseException {
        NetWageCalculation netWageCalculation = new NetWageCalculation();

        List<String[]> attendanceDataList = readAttendanceData();

        // Calculate total hours worked 
        double hoursWorked = calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        // Get the hourly rate for the employee
        double hourlyRate = getHourlyRate(employeeNumber);

        // Calculate assumed hours worked based on the provided date range
        double assumedHoursWorked = calculateAssumedHoursWorked(dateRange);

        // Calculate late arrival deduction
        double lateArrivalDeduction = netWageCalculation.calculateLateArrivalDeduction(attendanceDataList, employeeNumber, dateRange);

        if (hoursWorked > 0) {
            // Calculate wage based on hours worked from the attendance data
            return netWageCalculation.getWageInformation(employeeNumber, hourlyRate, hoursWorked, lateArrivalDeduction);
        }

        // Else, use assumed hours worked of 9.0 per day to calculate wage
        return netWageCalculation.getWageInformation(employeeNumber, hourlyRate, assumedHoursWorked, lateArrivalDeduction);
    }

    /**
     * Retrieves the hourly rate of an employee from the employee data file.
     *
     * @param employeeNumber The employee number
     * @return The hourly rate of the employee
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    private double getHourlyRate(int employeeNumber) throws IOException, CsvValidationException, ParseException {
        try (CSVReader reader = new CSVReader(new FileReader(EMPLOYEES_DATA_PATH))) {
            String[] data;
            reader.readNext(); // Skip header
            while ((data = reader.readNext()) != null) {
                if (data.length == EMPLOYEE_EXPECTED_DATA_LENGTH && Integer.parseInt(data[EMPLOYEE_NUM_INDEX]) == employeeNumber) {
                    return Double.parseDouble(data[HOURLY_RATE_INDEX]);
                }
            }
        }

        return 0.0;
    }

    /**
     * Calculates the total hours worked by an employee within a given date
     * range.
     *
     * @param attendanceDataList The attendance data list
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @return Total hours worked
     * @throws ParseException If parsing error occurs
     */
    private double calculateTotalHoursWorked(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        return timeCalculator.calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);
    }

    /**
     * Calculates assumed hours worked based on a given date range.
     *
     * @param dateRange The date range
     * @return Assumed hours worked
     */
    private double calculateAssumedHoursWorked(DateRange dateRange) {
        return timeCalculator.calculateAssumedHoursWorked(dateRange);
    }

    /**
     * Reads attendance data from the file and returns it as a list.
     *
     * @return List of attendance data arrays
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     */
    private List<String[]> readAttendanceData() throws IOException, CsvValidationException {
        List<String[]> attendanceDataList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(ATTENDANCE_DATA_PATH))) {
            String[] data;
            reader.readNext(); // Skip header
            while ((data = reader.readNext()) != null) {
                attendanceDataList.add(data);
            }
        }
        return attendanceDataList;
    }
}
