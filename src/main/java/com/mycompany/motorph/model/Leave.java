/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.model;

/**
 * Represents a leave application.
 *
 * @author Lance1
 */
public class Leave {

    private int employeeNumber;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;

    public Leave(int employeeNumber, String leaveType, String startDate, String endDate, String reason) {
        this.employeeNumber = employeeNumber;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    // Getters and setters
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Converts the Leave object to an array of strings.
     *
     * @return An array of strings representing the Leave object
     */
    public String[] toStringArray() {
        return new String[]{
            String.valueOf(employeeNumber),
            leaveType,
            startDate,
            endDate,
            reason
        };
    }
}
