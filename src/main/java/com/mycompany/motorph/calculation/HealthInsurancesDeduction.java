/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

/**
 * A class that calculates Pag-IBIG and PhilHealth deductions/contributions.
 *
 * @author Lance
 */
class HealthInsurancesDeduction {

    // Constants for PhilHealth calculation
    private static final double MIN_PHILHEALTH_DEDUCTION = 300;
    private static final double MAX_PHILHEALTH_DEDUCTION = 1800;
    private static final double PHILHEALTH_EMPLOYEE_SHARE = 0.50;
    private static final double PHILHEALTH_MIN_SALARY = 10000;
    private static final double PHILHEALTH_MAX_SALARY = 60000;
    private static final double PHILHEALTH_PREMIUM_RATE = 0.03;

    // Constants for Pag-IBIG calculation
    private static final double PAGIBIG_MIN_SALARY = 1000;
    private static final double PAGIBIG_MAX_SALARY = 1500;
    private static final double PAGIBIG_RATE_1000_TO_1500 = 0.03;
    private static final double PAGIBIG_RATE_ABOVE_1500 = 0.04;
    private static final double MAX_PAGIBIG_DEDUCTION = 100;

    /**
     * Calculates the PhilHealth deduction based on the employee's gross wage.
     *
     * @param grossWage The employee's gross wage
     * @return The calculated PhilHealth deduction amount
     */
    public double calculatePhilHealthDeduction(double grossWage) {
        double monthlyPremium = calculatePhilHealthPremium(grossWage);
        return monthlyPremium * PHILHEALTH_EMPLOYEE_SHARE;
    }

    /**
     * Calculates the Pag-IBIG deduction based on the employee's gross wage.
     *
     * @param grossWage The employee's gross wage
     * @return The calculated Pag-IBIG deduction amount
     */
    public double calculatePagIbigDeduction(double grossWage) {
        if (grossWage >= PAGIBIG_MIN_SALARY && grossWage <= PAGIBIG_MAX_SALARY) {
            return grossWage * PAGIBIG_RATE_1000_TO_1500;
        } else if (grossWage > PAGIBIG_MAX_SALARY) {
            double totalContribution = grossWage * PAGIBIG_RATE_ABOVE_1500;
            return Math.min(totalContribution, MAX_PAGIBIG_DEDUCTION);
        } else {
            return 0;
        }
    }

    /**
     * Calculates the PhilHealth monthly premium based on the employee's gross
     * wage.
     *
     * @param grossWage The employee's gross wage
     * @return The calculated PhilHealth premium amount
     */
    private double calculatePhilHealthPremium(double grossWage) {
        if (grossWage < PHILHEALTH_MIN_SALARY) {
            return MIN_PHILHEALTH_DEDUCTION;
        } else if (grossWage > PHILHEALTH_MAX_SALARY) {
            return MAX_PHILHEALTH_DEDUCTION;
        } else {
            return grossWage * PHILHEALTH_PREMIUM_RATE;
        }
    }
}
