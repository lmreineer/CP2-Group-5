/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.model;

/**
 * Represents a leave application.
 *
 * @author Lance
 */
public class Leave {

    private int employeeNumber;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private double sickLeaveAmount;
    private double vacationLeaveAmount;
    private double emergencyLeaveAmount;

    public Leave(int employeeNumber, String leaveType, String startDate, String endDate, String reason) {
        this.employeeNumber = employeeNumber;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        // Default leave amounts
        this.sickLeaveAmount = 0.0;
        this.vacationLeaveAmount = 0.0;
        this.emergencyLeaveAmount = 0.0;
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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getSickLeaveAmount() {
        return sickLeaveAmount;
    }

    public void setSickLeaveAmount(double sickLeaveAmount) {
        this.sickLeaveAmount = sickLeaveAmount;
    }

    public double getVacationLeaveAmount() {
        return vacationLeaveAmount;
    }

    public void setVacationLeaveAmount(double vacationLeaveAmount) {
        this.vacationLeaveAmount = vacationLeaveAmount;
    }

    public double getEmergencyLeaveAmount() {
        return emergencyLeaveAmount;
    }

    public void setEmergencyLeaveAmount(double emergencyLeaveAmount) {
        this.emergencyLeaveAmount = emergencyLeaveAmount;
    }
}
