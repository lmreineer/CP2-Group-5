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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that manages leave applications, including saving, loading, and
 * retrieving leave data.
 *
 * @author Lance
 */
public class LeaveDataManager {

    // Path to the leave data file
    private static final String LEAVE_DATA_PATH = "src/main/resources/data/leave_applications.csv";

    // Header for the CSV file
    private static final String[] HEADER = {"Employee Number", "Leave Type", "Start Date", "End Date", "Reason"};

    /**
     * Saves a leave application to the CSV file.
     *
     * @param leave The leave application to save
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If an error occurs during CSV validation
     */
    public void saveLeaveApplication(Leave leave) throws IOException, CsvValidationException {
        // Load existing leave applications
        List<Leave> leaves = loadLeaveApplications();

        // Variable to track if the leave application is updated
        boolean updated = false;
        // Loop through existing leave applications
        for (Leave l : leaves) {
            // If a matching leave application is found
            if (l.getEmployeeNumber() == leave.getEmployeeNumber() && l.getLeaveType().equals(leave.getLeaveType())) {
                l.setStartDate(leave.getStartDate());
                l.setEndDate(leave.getEndDate());
                l.setReason(leave.getReason());
                updated = true;
                break;
            }
        }

        // If leave application is not updated
        if (!updated) {
            // Add it
            leaves.add(leave);
        }

        // Write leave applications to CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(LEAVE_DATA_PATH))) {
            // Write header first
            writer.writeNext(HEADER);
            // Write each leave application to CSV
            for (Leave l : leaves) {
                writer.writeNext(l.toStringArray());
            }
        }
    }

    /**
     * Loads leave applications from the CSV file.
     *
     * @return A list of leave applications
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If an error occurs during CSV validation
     */
    public List<Leave> loadLeaveApplications() throws IOException, CsvValidationException {
        List<Leave> leaves = new ArrayList<>();
        File file = new File(LEAVE_DATA_PATH);

        // If file exists
        if (file.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                String[] data;
                boolean firstLine = true;
                // Read each line from CSV
                while ((data = reader.readNext()) != null) {
                    if (firstLine) {
                        firstLine = false;
                        // Skip header
                        continue;
                    }

                    // Create leave object from CSV data and add to list
                    leaves.add(new Leave(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            data[4]
                    ));
                }
            }
        }

        return leaves;
    }

    /**
     * Retrieves leave applications for a specific employee number.
     *
     * @param employeeNumber The employee number to retrieve leave applications
     * for
     * @return A list of leave applications for the specified employee number
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If an error occurs during CSV validation
     */
    public List<Leave> getLeavesByEmployeeNumber(int employeeNumber) throws IOException, CsvValidationException {
        // Filter leave applications by employee number
        return loadLeaveApplications().stream()
                .filter(leave -> leave.getEmployeeNumber() == employeeNumber)
                .collect(Collectors.toList());
    }
}
