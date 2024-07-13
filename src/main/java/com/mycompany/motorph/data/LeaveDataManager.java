/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.data;

import com.mycompany.motorph.model.Leave;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * A class that manages leave applications, including saving, loading, and
 * retrieving leave data.
 *
 * @author Lance
 */
public class LeaveDataManager {

    // Path to leave data file
    private static final String LEAVE_DATA_PATH = "src/main/resources/data/leave_balances.csv";

    // Header for the CSV data file
    private static final String[] HEADER = {"Employee Number", "Leave Type", "Start Date", "End Date", "Reason", "Sick Leave", "Vacation Leave", "Emergency Leave"};

    /**
     * Saves a leave application to the CSV file.
     *
     * @param leave The leave application to be saved
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If a CSV validation error occurs
     * @throws ParseException If date parsing error occurs
     */
    public void saveLeaveApplication(Leave leave) throws IOException, CsvValidationException, ParseException {
        List<Leave> leaves = loadLeaveApplications();

        // Calculate leave amounts based on leave type and duration
        calculateLeaveAmounts(leave);

        // Check if the leave application already exists for the employee
        boolean recordExists = leaves.stream()
                .anyMatch(l -> l.getEmployeeNumber() == leave.getEmployeeNumber());

        if (recordExists) {
            throw new IllegalArgumentException("Record already exists for employee number: " + leave.getEmployeeNumber());
        }

        // Add new leave application
        leaves.add(leave);

        // Write the updated list of leaves back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(LEAVE_DATA_PATH))) {
            // Write header first
            writer.writeNext(HEADER);

            // Write each leave application to CSV
            for (Leave l : leaves) {
                String[] leaveData = {
                    String.valueOf(l.getEmployeeNumber()),
                    l.getLeaveType(),
                    l.getStartDate(),
                    l.getEndDate(),
                    l.getReason(),
                    String.valueOf(l.getSickLeaveAmount()),
                    String.valueOf(l.getVacationLeaveAmount()),
                    String.valueOf(l.getEmergencyLeaveAmount())
                };
                writer.writeNext(leaveData);
            }
        }
    }

    /**
     * Retrieves leave applications for a specific employee.
     *
     * @param employeeNumber The employee number
     * @return A list of leave applications for the specified employee
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If a CSV validation error occurs
     */
    public List<Leave> getLeavesByEmployeeNumber(int employeeNumber) throws IOException, CsvValidationException {
        List<Leave> allLeaves = loadLeaveApplications();

        // Filter leaves by employee number
        List<Leave> employeeLeaves = allLeaves.stream()
                .filter(leave -> leave.getEmployeeNumber() == employeeNumber)
                .collect(Collectors.toList());

        // If no leave applications found
        if (employeeLeaves.isEmpty()) {
            throw new IllegalArgumentException("No leave information found for employee number: " + employeeNumber);
        }

        return employeeLeaves;
    }

    /**
     * Loads all leave applications from the CSV file.
     *
     * @return A list of all leave applications
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If a CSV validation error occurs
     */
    private List<Leave> loadLeaveApplications() throws IOException, CsvValidationException {
        List<Leave> leaves = new ArrayList<>();
        File file = new File(LEAVE_DATA_PATH);

        // If the file exists before attempting to read
        if (file.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                String[] data;
                boolean firstLine = true;

                // Read each line from the data file
                while ((data = reader.readNext()) != null) {
                    // Skip header
                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }

                    // Create Leave object from CSV and add to list
                    Leave leave = new Leave(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            data[4]
                    );

                    // Set leave amounts from CSV
                    leave.setSickLeaveAmount(Double.parseDouble(data[5]));
                    leave.setVacationLeaveAmount(Double.parseDouble(data[6]));
                    leave.setEmergencyLeaveAmount(Double.parseDouble(data[7]));

                    leaves.add(leave);
                }
            }
        }

        return leaves;
    }

    /**
     * Calculates leave amounts based on leave type and duration.
     *
     * @param leave The leave application to calculate amounts for
     * @throws ParseException If date parsing error occurs
     */
    private void calculateLeaveAmounts(Leave leave) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");

        Date startDate = sdf.parse(leave.getStartDate());
        Date endDate = sdf.parse(leave.getEndDate());

        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        // Include both start and end days
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

        // Reset amounts to 0
        leave.setSickLeaveAmount(0);
        leave.setVacationLeaveAmount(0);
        leave.setEmergencyLeaveAmount(0);

        switch (leave.getLeaveType()) {
            case "Sick Leave" ->
                leave.setSickLeaveAmount(1500 * diffInDays);
            case "Vacation Leave" ->
                leave.setVacationLeaveAmount(1500 * diffInDays);
            case "Emergency Leave" ->
                leave.setEmergencyLeaveAmount(500 * diffInDays);
        }
    }
}
