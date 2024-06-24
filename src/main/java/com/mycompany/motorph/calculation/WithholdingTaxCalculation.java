/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

/**
 * A class that calculates withholding tax using gross wage and deductions.
 *
 * @author Lance
 */
class WithholdingTaxCalculation {

    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsuranceDeduction;

    /**
     * Constructor for WithholdingTaxCalculator.
     */
    public WithholdingTaxCalculation() {
        // Initialize deductions
        this.sssDeduction = new SSSDeduction();
        this.healthInsuranceDeduction = new HealthInsurancesDeduction();
    }

    /**
     * Calculates the withholding tax based on the gross wage.
     *
     * @param grossWage Employee's gross wage
     * @return Withholding tax amount
     * @throws IOException If an I/O error occurs while calculating deductions
     * @throws CsvValidationException If there is an error in CSV validation
     */
    public double calculateWithholdingTax(double grossWage) throws IOException, CsvValidationException {
        // Calculate taxable income
        double taxableIncome = calculateTaxableIncome(grossWage);

        // Determine additional rate
        double additionalRate = determineAdditionalRate(taxableIncome);

        // Calculate withholding tax
        double withholdingTax = taxableIncome * additionalRate;

        return withholdingTax;
    }

    /**
     * Calculates the total monthly deductions from gross wage.
     *
     * @param grossWage Employee's gross wage
     * @return Total monthly deductions
     * @throws IOException If an I/O error occurs while calculating deductions
     * @throws CsvValidationException If there is an error in CSV validation
     */
    private double calculateMonthlyDeductions(double grossWage) throws IOException, CsvValidationException {
        double sssDeductionAmount = sssDeduction.calculateSssDeduction(grossWage);
        double philHealthContribution = healthInsuranceDeduction.calculatePhilHealthDeduction(grossWage);
        double pagIbigContribution = healthInsuranceDeduction.calculatePagIbigDeduction(grossWage);

        // Total deductions
        return sssDeductionAmount + philHealthContribution + pagIbigContribution;
    }

    /**
     * Calculates taxable income by subtracting deductions from gross wage.
     *
     * @param grossWage Employee's gross wage
     * @return Taxable income
     * @throws IOException If an I/O error occurs while calculating deductions
     * @throws CsvValidationException If there is an error in CSV validation
     */
    private double calculateTaxableIncome(double grossWage) throws IOException, CsvValidationException {
        double deductions = calculateMonthlyDeductions(grossWage);
        return grossWage - deductions;
    }

    /**
     * Determines the additional tax rate based on taxable income.
     *
     * @param taxableIncome Taxable income amount
     * @return Additional tax rate
     */
    private double determineAdditionalRate(double taxableIncome) {
        if (taxableIncome >= 666667) {
            return 0.35;
        } else if (taxableIncome >= 166667) {
            return 0.32;
        } else if (taxableIncome >= 66667) {
            return 0.30;
        } else if (taxableIncome >= 33333) {
            return 0.25;
        } else if (taxableIncome >= 20832) {
            return 0.20;
        } else {
            return 0.0;
        }
    }
}
