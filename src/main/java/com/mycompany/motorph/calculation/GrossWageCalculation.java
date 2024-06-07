/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.util.CurrencyUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that calculates and displays gross wage.
 * <p>
 * Extends the abstract class WageCalculation.
 * <p>
 * This class calculates the gross wage based on hourly rate and hours worked,
 * and displays the employee's information along with the calculated wage
 *
 * @author Lance1
 */
public class GrossWageCalculation extends WageCalculation {

    /**
     * Mock-up implementation for late arrival deduction calculation, as it is
     * not supported for gross wage calculation.
     *
     * @param attendanceDataList The list containing attendance data
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @return The late arrival deduction amount
     * @throws ParseException If a date parsing error occurs
     */
    @Override
    protected double calculateLateArrivalDeduction(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        return 0.0;
    }

    /**
     * Calculates gross wage based on hourly rate and hours worked.
     *
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction for the employee
     * @return The calculated wage
     */
    @Override
    protected double calculateWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        // Calculate gross wage
        return hourlyRate * hoursWorked;
    }

    /**
     * Displays the employee's gross wage along with other information.
     *
     * @param employeeNumber The employee number
     * @param hourlyRate The hourly rate
     * @param hoursWorked The hours worked
     * @param lateArrivalDeduction The late arrival deduction
     * @return A list of strings containing wage information
     */
    @Override
    protected List<String> getWageInformation(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction) {
        List<String> wageInfo = new ArrayList<>();

        // Calculate gross wage
        double grossWage = calculateWage(hourlyRate, hoursWorked, lateArrivalDeduction);

        // Add the calculated gross wage to the list
        wageInfo.add(CurrencyUtil.formatCurrency(grossWage));

        return wageInfo;
    }
}
