/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.util.List;

/**
 * An interface for populating employee information based on the provided
 * employee number.
 *
 * @author Lance
 */
interface EmployeeInformationManager {

    /**
     * Populates employee information based on the provided employee number.
     * Retrieves data from the EmployeeInformation class and fills the
     * appropriate text fields.
     */
    void populateEmployeeInformation();

    /**
     * Updates the employee information text fields for the searched employee.
     *
     * @param employeeInfo The information of the employee.
     */
    void updateEmployeeInformationFields(List<String> employeeInfo);

    /**
     * Enables start and end date input fields for wage calculation.
     */
    void enableDateInputs();

    /**
     * Populates wage information based on the provided employee number and date
     * range. Retrieves data from the NetWageCalculation class and fills the
     * appropriate text fields.
     */
    void populateWageInformation();

    /**
     * Updates the wage information text fields for the searched employee.
     *
     * @param wageInfo The wage information of the employee.
     */
    void updateWageInformationFields(List<String> wageInfo);

    /**
     * Displays an error dialog with the provided error message.
     *
     * @param errorMessage The message to be displayed in the error dialog.
     */
    void showErrorDialog(String errorMessage);
}
