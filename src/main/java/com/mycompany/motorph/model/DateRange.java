/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a date range.
 *
 * @author Lance
 */
public class DateRange {

    private final Date startDate;
    private final Date endDate;

    private static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MM");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");

    /**
     * Constructor for DateRange.
     *
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     */
    public DateRange(Date startDate, Date endDate) {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End date must be on or after the start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * Checks if the inputted date falls within the date range.
     *
     * @param date The date to check
     * @return true if the date is within the range, false otherwise
     */
    public boolean isWithinDateRange(Date date) {
        return !date.before(startDate) && !date.after(endDate);
    }

    /**
     * Creates a DateRange object for the entire specified month.
     *
     * @param month The month in "MM" format
     * @return The DateRange object that represents the specified month
     * @throws ParseException If a parsing error occurs
     */
    public static DateRange createMonthRange(String month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(MONTH_FORMAT.parse(month));

        // Set start date to the beginning of the specified month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Set end date to the end of the specified month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        return new DateRange(startDate, endDate);
    }

    /**
     * Creates a DateRange object for the specified start and end dates.
     *
     * @param startDateString The start date string in "MM/dd" format
     * @param endDateString The end date string in "MM/dd" format
     * @return The DateRange object of the date range specified
     * @throws ParseException If a parsing error occurs
     */
    public static DateRange createDateRange(String startDateString, String endDateString) throws ParseException {
        DATE_FORMAT.setLenient(false); // Strict parsing

        // Parse start and end dates
        Date startDate = DATE_FORMAT.parse(startDateString);
        Date endDate = DATE_FORMAT.parse(endDateString);

        return new DateRange(startDate, endDate);
    }
}
