/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A class for calculating employee working hours.
 *
 * @author Lance
 */
class TimeCalculation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private static final int ATTENDANCE_EXPECTED_COL_LENGTH = 6;
    private static final double ASSUMED_HOURS_PER_DAY = 9.0;

    /**
     * Calculates the total hours worked by the employee, late arrival deduction
     * if applicable, within the inputted date range.
     *
     * @param attendanceDataList The list containing attendance data
     * @param employeeNumber The employee number
     * @param dateRange The date range
     * @throws ParseException If there is an error parsing dates
     */
    double calculateTotalHoursWorked(List<String[]> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalHoursWorked = 0.0;

        // Iterate through each row of attendance data
        for (String[] data : attendanceDataList) {
            // If the data has the expected length per column and has the matching employee number from the inputted one
            if (data.length == ATTENDANCE_EXPECTED_COL_LENGTH && Integer.parseInt(data[0]) == employeeNumber) {
                // Parse attendance date, time in, and time out from the data
                Date attendanceDate = DATE_FORMAT.parse(data[3]);
                Date attendanceTimeIn = TIME_FORMAT.parse(data[4]);
                Date attendanceTimeOut = TIME_FORMAT.parse(data[5]);

                // If the attendance date is within the inputted date range
                if (dateRange.isWithinDateRange(attendanceDate)) {
                    // Calculate hours worked
                    totalHoursWorked += calculateHoursWorked(attendanceTimeIn, attendanceTimeOut);
                }
            }
        }

        return totalHoursWorked;
    }

    /**
     * Calculates assumed hours worked based on the inputted date range.
     *
     * @param dateRange The date range
     * @return The assumed hours worked
     */
    double calculateAssumedHoursWorked(DateRange dateRange) {
        // Calculate the number of days within the date range
        long numberOfDays = calculateNumberOfDays(dateRange);

        // Assume 9 hours per day
        return ASSUMED_HOURS_PER_DAY * numberOfDays;
    }

    /**
     * Calculates the number of days within the inputted date range.
     *
     * @param dateRange The date range
     * @return The number of days
     */
    public long calculateNumberOfDays(DateRange dateRange) {
        long startMillis = dateRange.getStartDate().getTime();
        long endMillis = dateRange.getEndDate().getTime();

        // Calculate the difference in milliseconds
        long durationInMillis = endMillis - startMillis;

        // Convert milliseconds to days and add 1 to include the end date
        return TimeUnit.MILLISECONDS.toDays(durationInMillis) + 1;
    }

    /**
     * Calculates the number of hours worked between the specified time in and
     * time out.
     *
     * @param timeIn The time the employee clocked in
     * @param timeOut The time the employee clocked out
     * @return The number of hours worked
     */
    private double calculateHoursWorked(Date timeIn, Date timeOut) {
        // Calculate the difference in milliseconds
        long timeDifferenceMillis = timeOut.getTime() - timeIn.getTime();

        // Convert milliseconds to hours
        return TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis);
    }
}
