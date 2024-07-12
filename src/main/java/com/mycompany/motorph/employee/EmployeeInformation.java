/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.employee;

import com.mycompany.motorph.model.Employee;
import com.mycompany.motorph.data.EmployeeDataReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A class for managing employee information.
 * <p>
 * It allows displaying employee information using employee numbers, and
 * provides methods for finding employees.
 *
 * @author Lance
 */
public class EmployeeInformation {

    private static final SimpleDateFormat BIRTHDATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    // Path to the employee data file
    private static final String EMPLOYEES_DATA_PATH = "src/main/resources/data/employee_information.csv";

    /**
     * Displays employee information with the inputted employee number.
     *
     * @param employeeNumber The employee number to search for
     * @return The list containing employee information
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    public List<String> showEmployeeInformation(int employeeNumber) throws IOException, CsvValidationException, ParseException {
        // Read the list of employees from the data file
        List<Employee> employees = getAllEmployees();

        // Find the employee with the inputted employee number
        Employee foundEmployee = findEmployeeByNumber(employees, employeeNumber);

        // If an employee is found with the inputted employee number
        if (foundEmployee != null) {
            // Return the information of the employee
            return foundEmployee.getEmployeeInformation();
        } else {
            // Throw IllegalArgumentException with the exception message
            throw new IllegalArgumentException("Employee is not found.");
        }
    }

    /**
     * Updates employee information in the CSV file.
     *
     * @param employeeNumber The employee number to update
     * @param updatedEmployeeInfo The updated information of the employee
     * @throws IOException If an I/O error occurs while writing to the file
     * @throws CsvValidationException If data validation fails
     * @throws ParseException If parsing error occurs
     */
    public void updateEmployeeInformationInCsv(int employeeNumber, List<String> updatedEmployeeInfo) throws IOException, CsvValidationException, ParseException {
        // Create an instance of EmployeeDataReader
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();

        // Read the list of employees from the data file
        List<Employee> employees = employeeDataReader.readEmployees(EMPLOYEES_DATA_PATH);

        try {
            // Loop through each employee information
            for (Employee employee : employees) {
                if (employee.getEmployeeNumber() == employeeNumber) {
                    // Update employee information
                    updateEmployee(employee, updatedEmployeeInfo);
                    // Exit loop after updating
                    break;
                }
            }

            // Write updated employees back to CSV file
            employeeDataReader.writeEmployees(EMPLOYEES_DATA_PATH, employees);
        } catch (NumberFormatException e) {
            // Throw NumberFormatException with the exception message
            throw new NumberFormatException("Currencies cannot be empty.");
        }
    }

    /**
     * Retrieves the list of all employees.
     *
     * @return The list of employees
     * @throws IOException If an I/O error occurs while reading the file
     * @throws CsvValidationException If data from a row is invalid
     * @throws ParseException If parsing error occurs
     */
    public List<Employee> getAllEmployees() throws IOException, CsvValidationException, ParseException {
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();
        return employeeDataReader.readEmployees(EMPLOYEES_DATA_PATH);
    }

    /**
     * Finds an employee by their employee number.
     *
     * @param employees The list of employees to search in
     * @param employeeNumber The employee number to search for
     * @return The found employee, or null if not found
     */
    private Employee findEmployeeByNumber(List<Employee> employees, int employeeNumber) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeNumber() == employeeNumber)
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the given employee with the updated information.
     *
     * @param employee The employee to update
     * @param updatedEmployeeInfo The updated information of the employee
     * @throws ParseException If parsing error occurs
     */
    private void updateEmployee(Employee employee, List<String> updatedEmployeeInfo) throws ParseException {
        // Update employee information
        employee.setLastName(updatedEmployeeInfo.get(0));
        employee.setFirstName(updatedEmployeeInfo.get(1));
        employee.setBirthdate(BIRTHDATE_FORMAT.parse(updatedEmployeeInfo.get(2)));
        employee.setAddress(updatedEmployeeInfo.get(3));
        employee.setPhoneNumber(updatedEmployeeInfo.get(4));
        employee.setSssNumber(updatedEmployeeInfo.get(5));
        employee.setPhilHealthNumber(updatedEmployeeInfo.get(6));
        employee.setTin(updatedEmployeeInfo.get(7));
        employee.setPagIbigNumber(updatedEmployeeInfo.get(8));
        employee.setStatus(updatedEmployeeInfo.get(9));
        employee.setPosition(updatedEmployeeInfo.get(10));
        employee.setImmediateSupervisor(updatedEmployeeInfo.get(11));
        employee.setBasicSalary(Double.parseDouble(updatedEmployeeInfo.get(12).replace(",", "")));
        employee.setRiceSubsidy(Double.parseDouble(updatedEmployeeInfo.get(13).replace(",", "")));
        employee.setPhoneAllowance(Double.parseDouble(updatedEmployeeInfo.get(14).replace(",", "")));
        employee.setClothingAllowance(Double.parseDouble(updatedEmployeeInfo.get(15).replace(",", "")));
        employee.setGrossSemimonthlyRate(Double.parseDouble(updatedEmployeeInfo.get(16).replace(",", "")));
        employee.setHourlyRate(Double.parseDouble(updatedEmployeeInfo.get(17).replace(",", "")));
    }
}
