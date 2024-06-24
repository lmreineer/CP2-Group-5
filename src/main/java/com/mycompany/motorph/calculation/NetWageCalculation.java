/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.util.CurrencyUtil;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A class that calculates and displays net wage.
 * <p>
 * Extends the abstract class WageCalculation.
 * <p>
 * This class calculates the net wage by subtracting total deductions from gross
 * wage and late arrival deduction and displays the employee's information along
 * with the calculated wage
 *
 * @author Lance
 */
public class NetWageCalculation {

    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsurancesDeduction;
    private final WithholdingTaxCalculation withholdingTaxCalculation;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private static final int ATTENDANCE_EXPECTED_COL_LENGTH = 6;
    private static final double LATE_ARRIVAL_DEDUCTION_PER_MINUTE = 1.66;
    private static final int LATE_HOUR_START = 8;
    private static final int LATE_MINUTE_START = 11;
    private static final int MINUTES_IN_HOUR = 60;

    /**
     * Constructor for NetWageCalculator.
     */
    public NetWageCalculation() {
        // Initialize dependencies
        this.sssDeduction = new SSSDeduction();
        this.healthInsurancesDeduction = new HealthInsurancesDeduction();
        this.withholdingTaxCalculation = new WithholdingTaxCalculation();
    }

    /**
     * Calculates late arrival deduction for an employee.
     *
     * @param attendanceDataList The list containing attendance data
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @return The late arrival deduction amount
     * @throws ParseException If a date parsing error occurs
     */
    public double calculateLateArrivalDeduction(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalLateArrivalDeduction = 0.0;

        // Iterate through each row of attendance data
        for (String[] data : attendanceDataList) {
            // If the data has the expected length per row and has the matching employee number from the inputted one
            if (data.length == ATTENDANCE_EXPECTED_COL_LENGTH && Integer.parseInt(data[0]) == employeeNumber) {
                // Parse attendance date, time in, and time out from the data
                Date attendanceDate = DATE_FORMAT.parse(data[3]);
                Date attendanceTimeIn = TIME_FORMAT.parse(data[4]);

                // If the attendance date is within the inputted date range and the employee arrived late
                if (dateRange.isWithinDateRange(attendanceDate) && arrivedLate(attendanceTimeIn)) {
                    // Calculate late arrival duration in minutes
                    int lateArrivalMinutes = calculateLateArrivalMinutes(attendanceTimeIn);
                    // Calculate late arrival deduction
                    double lateArrivalDeduction = lateArrivalMinutes * LATE_ARRIVAL_DEDUCTION_PER_MINUTE;
                    // Add late arrival deduction to the total
                    totalLateArrivalDeduction += lateArrivalDeduction;
                }
            }
        }

        return totalLateArrivalDeduction;
    }

    /**
     * Displays the employee's net wage with other information.
     *
     * @param employeeNumber The employee number
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction
     * @return A list of strings containing wage information
     */
    public List<String> getWageInformation(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction) throws IOException, CsvValidationException {
        // Calculate gross wage
        double grossWage = hourlyRate * hoursWorked;
        // Calculate net wage
        double netWage = calculateNetWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        List<String> wageInfo = new ArrayList<>();

        wageInfo.add(CurrencyUtil.formatCurrency(grossWage));
        wageInfo.add(CurrencyUtil.formatCurrency(sssDeduction.calculateSssDeduction(grossWage)));
        wageInfo.add(CurrencyUtil.formatCurrency(healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage)));
        wageInfo.add(CurrencyUtil.formatCurrency(healthInsurancesDeduction.calculatePagIbigDeduction(grossWage)));
        wageInfo.add(CurrencyUtil.formatCurrency(withholdingTaxCalculation.calculateWithholdingTax(grossWage)));
        wageInfo.add(CurrencyUtil.formatCurrency(lateArrivalDeduction));
        wageInfo.add(CurrencyUtil.formatCurrency(calculateTotalDeductions(grossWage, lateArrivalDeduction)));
        wageInfo.add(CurrencyUtil.formatCurrency(netWage));

        return wageInfo;
    }

    /**
     * Calculates net wage by subtracting total deductions from gross wage.
     *
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction for the employee
     * @return The calculated net wage
     */
    double calculateNetWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction) throws IOException, CsvValidationException {
        // Calculate gross wage
        double grossWage = hourlyRate * hoursWorked;

        // Calculate total deductions
        double totalDeductions = calculateTotalDeductions(grossWage, lateArrivalDeduction);

        // Calculate net wage by subtracting total deductions from the gross wage and make sure it's non-negative
        return Math.max(grossWage - totalDeductions, 0);
    }

    /**
     * Checks if the given time indicates a late arrival for the employee.
     *
     * @param timeIn The time of arrival
     * @return True if the arrival is considered late. Otherwise, false
     */
    private boolean arrivedLate(Date timeIn) {
        // Create a Calendar instance and set it to the specified arrival time
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeIn);

        // Extract the hour and minute from the arrival time
        int arrivalHour = cal.get(Calendar.HOUR_OF_DAY);
        int arrivalMinute = cal.get(Calendar.MINUTE);

        // Return true if the arrival hour is after 8 AM, or if it's 8 AM and the minutes are 11 or later
        return arrivalHour > LATE_HOUR_START || (arrivalHour == LATE_HOUR_START && arrivalMinute >= LATE_MINUTE_START);
    }

    /**
     * Calculates the duration of late arrival in minutes based on the time of
     * arrival.
     *
     * @param timeIn The time of arrival
     * @return The duration of late arrival in minutes
     */
    private int calculateLateArrivalMinutes(Date timeIn) {
        // Create a Calendar instance and set it to the specified arrival time
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeIn);

        // Extract the hour and minute from the arrival time
        int arrivalHour = cal.get(Calendar.HOUR_OF_DAY);
        int arrivalMinute = cal.get(Calendar.MINUTE);

        // Get the total minutes of late arrival by calculating the difference from the late start time
        int lateArrivalMinutes = (arrivalHour - LATE_HOUR_START) * MINUTES_IN_HOUR;
        lateArrivalMinutes += arrivalMinute - (LATE_MINUTE_START);
        return lateArrivalMinutes;
    }

    /**
     * Calculates total deductions.
     *
     * @param grossWage The gross wage before deductions
     * @param lateArrivalDeduction The late arrival deduction
     * @return The total deductions
     */
    private double calculateTotalDeductions(double grossWage, double lateArrivalDeduction) throws IOException, CsvValidationException {
        // Calculate SSS deductions
        double sssDeductions = sssDeduction.calculateSssDeduction(grossWage);

        // Calculate PhilHealth deductions
        double philHealthDeductions = healthInsurancesDeduction.calculatePhilHealthDeduction(grossWage);

        // Calculate Pag-IBIG deductions
        double pagIbigDeductions = healthInsurancesDeduction.calculatePagIbigDeduction(grossWage);

        // Calculate withholding tax
        double withholdingTax = withholdingTaxCalculation.calculateWithholdingTax(grossWage);

        // Return the sum of all deductions
        return sssDeductions + philHealthDeductions + pagIbigDeductions + withholdingTax + lateArrivalDeduction;
    }
}
