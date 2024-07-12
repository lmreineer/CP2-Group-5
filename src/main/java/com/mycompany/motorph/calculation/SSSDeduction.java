/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that calculates SSS deductions based on gross wage.
 *
 * @author Lance
 */
public class SSSDeduction {

    // Path to the SSS deductions data file
    private static final String SSS_DEDUCTIONS_PATH = "src/main/resources/data/sss_deduction.csv";

    private static final double MIN_COMPENSATION_RANGE = 3250.00;
    private static final double MAX_COMPENSATION_RANGE = 24750.00;
    private static final double MIN_DEDUCTION = 135.00;
    private static final double MAX_DEDUCTION = 1125.00;
    private static final int SSS_DEDUCTION_EXPECTED_COL_LENGTH = 3;

    private final List<double[]> sssCompensationRanges = new ArrayList<>();
    private final List<Double> sssDeductions = new ArrayList<>();
    private boolean dataLoaded = false;

    /**
     * Calculates SSS deduction.
     *
     * @param grossWage Gross wage
     * @return SSS deduction amount
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If CSV validation fails
     */
    public double calculateSssDeduction(double grossWage) throws IOException, CsvValidationException {
        // Lazy loading of data
        if (!dataLoaded) {
            readSSSDeductions();
            dataLoaded = true;
        }

        // Determine the appropriate SSS deduction based on gross wage
        if (grossWage < MIN_COMPENSATION_RANGE) {
            return MIN_DEDUCTION;
        } else if (grossWage > MAX_COMPENSATION_RANGE) {
            return MAX_DEDUCTION;
        } else {
            for (int i = 0; i < sssCompensationRanges.size(); i++) {
                double[] range = sssCompensationRanges.get(i);
                double sssDeduction = sssDeductions.get(i);

                if (grossWage >= range[0] && grossWage <= range[1]) {
                    return sssDeduction;
                }
            }
        }

        // Default value if no matching range is found
        return 0.0;
    }

    /**
     * Reads SSS deductions data from the data file and populates the ranges and
     * deductions.
     *
     * @throws IOException If an I/O error occurs
     * @throws CsvValidationException If CSV validation fails
     */
    private void readSSSDeductions() throws IOException, CsvValidationException {
        // Open the file for reading
        try (CSVReader reader = new CSVReader(new FileReader(SSS_DEDUCTIONS_PATH))) {
            String[] data;
            // Skip header
            reader.readNext();

            // Read data per row from the data file
            while ((data = reader.readNext()) != null) {
                // If the data has the expected length per column
                if (data.length == SSS_DEDUCTION_EXPECTED_COL_LENGTH) {
                    double lowerRange = Double.parseDouble(data[0]);
                    double upperRange = Double.parseDouble(data[1]);
                    double sssDeduction = Double.parseDouble(data[2]);

                    sssCompensationRanges.add(new double[]{lowerRange, upperRange});
                    sssDeductions.add(sssDeduction);
                } else {
                    // Throw IllegalArgumentException with the exception message
                    throw new IllegalArgumentException("Invalid data length: " + data.length + " in row: " + String.join(",", data) + " of the SSS deductions data.");
                }
            }
        }
    }
}
