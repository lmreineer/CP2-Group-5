/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.util;

import com.mycompany.motorph.model.Leave;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A utility class that provides methods to calculate the remaining leave days
 * for different types of leaves.
 *
 * @author Lance
 */
public class LeaveBalanceCalculator {

    // Constants
    public static final int SICK_LEAVE_DAYS = 1500;
    public static final int VACATION_LEAVE_DAYS = 1500;
    public static final int EMERGENCY_LEAVE_DAYS = 500;

    /**
     * Calculates the number of days between two dates.
     *
     * @param startDate The start date in MM/dd format
     * @param endDate The end date in MM/dd format
     * @return The number of days between the start and end dates
     * @throws ParseException If the date format is incorrect
     */
    public static int calculateDaysBetween(String startDate, String endDate) throws ParseException {
        // Create a date formatter with the MM/dd format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        // Parse the start and end dates
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        // Calculate the difference between end and start dates in milliseconds
        long difference = end.getTime() - start.getTime();

        // Convert milliseconds to days and return the result
        return (int) (difference / (1000 * 60 * 60 * 24)) + 1;
    }

    /**
     * Calculates the remaining leave days for a specified leave type.
     *
     * @param leaves The list of leave records
     * @param leaveType The type of leave (e.g., "Sick Leave")
     * @param totalDays The total number of leave days allocated
     * @return The remaining leave days
     * @throws ParseException If the date format is incorrect in any leave
     * record
     */
    public static int calculateRemainingLeave(List<Leave> leaves, String leaveType, int totalDays) throws ParseException {
        int usedDays = 0;

        // Loop through each leave record
        for (Leave leave : leaves) {
            // If the leave type matches the specified type
            if (leave.getLeaveType().equalsIgnoreCase(leaveType)) {
                // Calculate the days between start and end dates for the leave record
                usedDays += calculateDaysBetween(leave.getStartDate(), leave.getEndDate());
            }
        }

        // Calculate remaining leave days
        int remainingLeaveDays = totalDays - usedDays;

        // Return 0 if the remainingLeaveDays is less than 0, else, return the remainingLeaveDays value
        return remainingLeaveDays < 0 ? 0 : remainingLeaveDays;
    }

    /**
     * Calculates the remaining sick leave days.
     *
     * @param leaves The list of leave records
     * @return The remaining sick leave days
     * @throws ParseException If the date format is incorrect in any leave
     * record
     */
    public static int getRemainingSickLeave(List<Leave> leaves) throws ParseException {
        return calculateRemainingLeave(leaves, "Sick Leave", SICK_LEAVE_DAYS);
    }

    /**
     * Calculates the remaining vacation leave days.
     *
     * @param leaves The list of leave records
     * @return The remaining vacation leave days
     * @throws ParseException If the date format is incorrect in any leave
     * record
     */
    public static int getRemainingVacationLeave(List<Leave> leaves) throws ParseException {
        return calculateRemainingLeave(leaves, "Vacation Leave", VACATION_LEAVE_DAYS);
    }

    /**
     * Calculates the remaining emergency leave days.
     *
     * @param leaves The list of leave records
     * @return The remaining emergency leave days
     * @throws ParseException If the date format is incorrect in any leave
     * record
     */
    public static int getRemainingEmergencyLeave(List<Leave> leaves) throws ParseException {
        return calculateRemainingLeave(leaves, "Emergency Leave", EMERGENCY_LEAVE_DAYS);
    }
}
