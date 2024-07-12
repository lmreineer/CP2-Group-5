/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motorph;

import com.mycompany.motorph.calculation.WageCalculation;
import com.mycompany.motorph.employee.EmployeeInformation;
import com.mycompany.motorph.model.DateRange;
import static com.mycompany.motorph.model.DateRange.createMonthRange;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A class that displays the specific employee's pay information based on the
 * selected month, and his/her full information and allows updating of them.
 *
 * @author Lance
 */
class EmployeeInformationFrame extends javax.swing.JFrame implements EmployeeInformationManager {

    // Constants for button coloring changes
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(203, 203, 239);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color RED = new java.awt.Color(191, 47, 47);
    private static final java.awt.Color GRAY = new java.awt.Color(242, 242, 242);

    private boolean deleteButtonClicked = false;

    private JButton computeButton;
    private JComboBox<String> monthComboBox;
    private JLabel instructionLabel;
    private JPanel topPanel;

    /**
     * Creates new EmployeeInformationFrame.
     *
     * @param employeeInformation List of strings that contains initial employee
     * information.
     */
    public EmployeeInformationFrame(List<String> employeeInformation) {
        initComponents();
        setupFrame(employeeInformation);
        assignClickHandlersToTextFields();
    }

    /**
     * Sets up the frame with initial components and layout.
     *
     * @param employeeDetails List of strings that contains initial employee
     * information.
     */
    private void setupFrame(List<String> employeeDetails) {
        setLayout(new BorderLayout());

        // Create components
        initializeTopPanel(employeeDetails);

        // Set frame visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the top panel with a label, combo box for months, and compute
     * button.
     *
     * @param employeeDetails List of strings that contains initial employee
     * information.
     */
    private void initializeTopPanel(List<String> employeeDetails) {
        // Create the label
        instructionLabel = new JLabel("Select the month for wage information");

        // Create the month combo box
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        monthComboBox = new JComboBox<>(months);

        // Create the compute button
        computeButton = createButton("Compute", e -> {
            String selectedMonth = String.format("%02d", monthComboBox.getSelectedIndex() + 1);
            showInformation(employeeDetails, selectedMonth);
        });

        computeButton.addMouseListener(createButtonHoverAdapter(computeButton, LIGHT_BLUE, WHITE));

        computeButton.setBackground(WHITE);
        computeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        scrollPaneMain.setVisible(false);  // Initially hide the information panel

        // Create a panel for the top components
        topPanel = new JPanel();
        // Add a 12-pixel margin
        topPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(instructionLabel);
        topPanel.add(monthComboBox);

        monthComboBox.setFocusable(false);
        computeButton.setFocusable(false);

        // Add the top panel and the compute button to the frame
        add(topPanel, BorderLayout.NORTH);
        add(computeButton, BorderLayout.CENTER);
    }

    /**
     * Creates a JButton with specified text and action listener.
     *
     * @param text Text to display on the button
     * @param actionListener ActionListener for button click event
     * @return JButton created button
     */
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    /**
     * Creates a MouseAdapter for handling button hover effects.
     *
     * @param button JButton to apply hover effect
     * @param hoverColor Color when mouse hovers over the button
     * @param defaultColor Default color of the button
     * @return MouseAdapter created MouseAdapter for button hover effects
     */
    private MouseAdapter createButtonHoverAdapter(JButton button, Color hoverColor, Color defaultColor) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultColor);
            }
        };
    }

    /**
     * Displays employee and wage information for the selected month.
     *
     * @param employeeDetails List of strings that contains employee
     * information.
     * @param selectedMonth Selected month in "MM" format.
     */
    private void showInformation(List<String> employeeDetails, String selectedMonth) {
        remove(topPanel);
        remove(computeButton);

        populateEmployeeInformation(employeeDetails.get(0));

        // Set the intended size
        scrollPaneMain.setPreferredSize(new Dimension(603, 627));
        scrollPaneMain.setVisible(true);
        add(scrollPaneMain, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();

        populateWageInformation(selectedMonth);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneMain = new javax.swing.JScrollPane();
        pnlMain = new javax.swing.JPanel();
        lblMotorPhHeader = new javax.swing.JLabel();
        lblEmployeeInformationHeader = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        lblBirthdate = new javax.swing.JLabel();
        txtBirthdate = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        lblPhoneNumber = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtSssNumber = new javax.swing.JTextField();
        lblSssNumber = new javax.swing.JLabel();
        txtPhilHealthNumber = new javax.swing.JTextField();
        lblPhilHealthNumber = new javax.swing.JLabel();
        txtTinNumber = new javax.swing.JTextField();
        lblTinNumber = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        txtPagIbigNumber = new javax.swing.JTextField();
        lblPagIbigNumber = new javax.swing.JLabel();
        txtPosition = new javax.swing.JTextField();
        lblPosition = new javax.swing.JLabel();
        txtBasicSalary = new javax.swing.JTextField();
        lblBasicSalary = new javax.swing.JLabel();
        txtPhoneAllowance = new javax.swing.JTextField();
        lblPhoneAllowance = new javax.swing.JLabel();
        txtGrossSemimonthlyRate = new javax.swing.JTextField();
        lblGrossSemimonthlyRate = new javax.swing.JLabel();
        txtImmediateSupervisor = new javax.swing.JTextField();
        lblImmediateSupervisor = new javax.swing.JLabel();
        txtRiceSubsidy = new javax.swing.JTextField();
        lblRiceSubsidy = new javax.swing.JLabel();
        txtClothingAllowance = new javax.swing.JTextField();
        lblClothingAllowance = new javax.swing.JLabel();
        txtHourlyRate = new javax.swing.JTextField();
        lblHourlyRate = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblBottomSeparator = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnDeleteInfo = new javax.swing.JButton();
        btnUpdateInfo = new javax.swing.JButton();
        lblEmployeeNumber = new javax.swing.JLabel();
        txtEmployeeNumber = new javax.swing.JTextField();
        lblEmployeeInformationHeader1 = new javax.swing.JLabel();
        lblGrossWage = new javax.swing.JLabel();
        txtGrossWage = new javax.swing.JTextField();
        txtSssDeduction = new javax.swing.JTextField();
        lblSssDeduction = new javax.swing.JLabel();
        lblPhilHealthDeduction = new javax.swing.JLabel();
        txtPhilHealthDeduction = new javax.swing.JTextField();
        txtPagIbigDeduction = new javax.swing.JTextField();
        lblPagIbigDeduction = new javax.swing.JLabel();
        lblWithholdingTax = new javax.swing.JLabel();
        txtWithholdingTax = new javax.swing.JTextField();
        txtLateArrivalDeduction = new javax.swing.JTextField();
        lblLateArrivalDeduction = new javax.swing.JLabel();
        lblTotalDeductions = new javax.swing.JLabel();
        txtTotalDeductions = new javax.swing.JTextField();
        txtNetWage = new javax.swing.JTextField();
        lblNetWage = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();
        txtMonth = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee and Wage Information");
        setResizable(false);

        scrollPaneMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblMotorPhHeader.setBackground(new java.awt.Color(255, 255, 255));
        lblMotorPhHeader.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        lblMotorPhHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMotorPhHeader.setText("MotorPH Payroll System");
        lblMotorPhHeader.setOpaque(true);

        lblEmployeeInformationHeader.setBackground(new java.awt.Color(223, 54, 54));
        lblEmployeeInformationHeader.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblEmployeeInformationHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblEmployeeInformationHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeInformationHeader.setText("Employee Information");
        lblEmployeeInformationHeader.setOpaque(true);

        txtLastName.setEditable(false);
        txtLastName.setBackground(new java.awt.Color(242, 242, 242));
        txtLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLastName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLastName.setFocusable(false);

        lblLastName.setBackground(new java.awt.Color(242, 242, 242));
        lblLastName.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblLastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLastName.setText("Last Name:");
        lblLastName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblLastName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLastName.setMaximumSize(new java.awt.Dimension(93, 25));
        lblLastName.setMinimumSize(new java.awt.Dimension(93, 25));
        lblLastName.setOpaque(true);

        lblBirthdate.setBackground(new java.awt.Color(242, 242, 242));
        lblBirthdate.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblBirthdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBirthdate.setText("Birthdate:");
        lblBirthdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblBirthdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblBirthdate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblBirthdate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblBirthdate.setOpaque(true);

        txtBirthdate.setEditable(false);
        txtBirthdate.setBackground(new java.awt.Color(242, 242, 242));
        txtBirthdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBirthdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtBirthdate.setFocusable(false);

        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtPhoneNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhoneNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPhoneNumber.setFocusable(false);

        lblPhoneNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblPhoneNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoneNumber.setText("Phone #:");
        lblPhoneNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPhoneNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPhoneNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPhoneNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPhoneNumber.setOpaque(true);

        txtAddress.setEditable(false);
        txtAddress.setBackground(new java.awt.Color(242, 242, 242));
        txtAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtAddress.setFocusable(false);

        lblAddress.setBackground(new java.awt.Color(242, 242, 242));
        lblAddress.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblAddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddress.setText("Address:");
        lblAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblAddress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAddress.setMaximumSize(new java.awt.Dimension(93, 25));
        lblAddress.setMinimumSize(new java.awt.Dimension(93, 25));
        lblAddress.setOpaque(true);

        txtSssNumber.setEditable(false);
        txtSssNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtSssNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSssNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtSssNumber.setFocusable(false);

        lblSssNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblSssNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblSssNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSssNumber.setText("SSS #:");
        lblSssNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblSssNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSssNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblSssNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblSssNumber.setOpaque(true);

        txtPhilHealthNumber.setEditable(false);
        txtPhilHealthNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtPhilHealthNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhilHealthNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPhilHealthNumber.setFocusable(false);

        lblPhilHealthNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblPhilHealthNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhilHealthNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhilHealthNumber.setText("PhilHealth #:");
        lblPhilHealthNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPhilHealthNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPhilHealthNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthNumber.setOpaque(true);

        txtTinNumber.setEditable(false);
        txtTinNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtTinNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTinNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTinNumber.setFocusable(false);

        lblTinNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblTinNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTinNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTinNumber.setText("TIN:");
        lblTinNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblTinNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTinNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblTinNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblTinNumber.setOpaque(true);

        txtStatus.setEditable(false);
        txtStatus.setBackground(new java.awt.Color(242, 242, 242));
        txtStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtStatus.setFocusable(false);

        lblStatus.setBackground(new java.awt.Color(242, 242, 242));
        lblStatus.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Status:");
        lblStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblStatus.setMaximumSize(new java.awt.Dimension(93, 25));
        lblStatus.setMinimumSize(new java.awt.Dimension(93, 25));
        lblStatus.setOpaque(true);

        txtPagIbigNumber.setEditable(false);
        txtPagIbigNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtPagIbigNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagIbigNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPagIbigNumber.setFocusable(false);

        lblPagIbigNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblPagIbigNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPagIbigNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagIbigNumber.setText("Pag-IBIG #:");
        lblPagIbigNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPagIbigNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPagIbigNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPagIbigNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPagIbigNumber.setOpaque(true);

        txtPosition.setEditable(false);
        txtPosition.setBackground(new java.awt.Color(242, 242, 242));
        txtPosition.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPosition.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPosition.setFocusable(false);

        lblPosition.setBackground(new java.awt.Color(242, 242, 242));
        lblPosition.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosition.setText("Position:");
        lblPosition.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPosition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPosition.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPosition.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPosition.setOpaque(true);

        txtBasicSalary.setEditable(false);
        txtBasicSalary.setBackground(new java.awt.Color(242, 242, 242));
        txtBasicSalary.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBasicSalary.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtBasicSalary.setFocusable(false);

        lblBasicSalary.setBackground(new java.awt.Color(242, 242, 242));
        lblBasicSalary.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblBasicSalary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBasicSalary.setText("Basic Salary:");
        lblBasicSalary.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblBasicSalary.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblBasicSalary.setMaximumSize(new java.awt.Dimension(93, 25));
        lblBasicSalary.setMinimumSize(new java.awt.Dimension(93, 25));
        lblBasicSalary.setOpaque(true);

        txtPhoneAllowance.setEditable(false);
        txtPhoneAllowance.setBackground(new java.awt.Color(242, 242, 242));
        txtPhoneAllowance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhoneAllowance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPhoneAllowance.setFocusable(false);

        lblPhoneAllowance.setBackground(new java.awt.Color(242, 242, 242));
        lblPhoneAllowance.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhoneAllowance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoneAllowance.setText("Phone Allowance:");
        lblPhoneAllowance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPhoneAllowance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPhoneAllowance.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPhoneAllowance.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPhoneAllowance.setOpaque(true);

        txtGrossSemimonthlyRate.setEditable(false);
        txtGrossSemimonthlyRate.setBackground(new java.awt.Color(242, 242, 242));
        txtGrossSemimonthlyRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrossSemimonthlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtGrossSemimonthlyRate.setFocusable(false);

        lblGrossSemimonthlyRate.setBackground(new java.awt.Color(242, 242, 242));
        lblGrossSemimonthlyRate.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGrossSemimonthlyRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGrossSemimonthlyRate.setText("Gross Semi-monthly Rate:");
        lblGrossSemimonthlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblGrossSemimonthlyRate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblGrossSemimonthlyRate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblGrossSemimonthlyRate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblGrossSemimonthlyRate.setOpaque(true);

        txtImmediateSupervisor.setEditable(false);
        txtImmediateSupervisor.setBackground(new java.awt.Color(242, 242, 242));
        txtImmediateSupervisor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImmediateSupervisor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtImmediateSupervisor.setFocusable(false);

        lblImmediateSupervisor.setBackground(new java.awt.Color(242, 242, 242));
        lblImmediateSupervisor.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblImmediateSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImmediateSupervisor.setText("Immediate Supervisor:");
        lblImmediateSupervisor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblImmediateSupervisor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImmediateSupervisor.setMaximumSize(new java.awt.Dimension(93, 25));
        lblImmediateSupervisor.setMinimumSize(new java.awt.Dimension(93, 25));
        lblImmediateSupervisor.setOpaque(true);

        txtRiceSubsidy.setEditable(false);
        txtRiceSubsidy.setBackground(new java.awt.Color(242, 242, 242));
        txtRiceSubsidy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRiceSubsidy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtRiceSubsidy.setFocusable(false);

        lblRiceSubsidy.setBackground(new java.awt.Color(242, 242, 242));
        lblRiceSubsidy.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblRiceSubsidy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRiceSubsidy.setText("Rice Subsidy:");
        lblRiceSubsidy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblRiceSubsidy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblRiceSubsidy.setMaximumSize(new java.awt.Dimension(93, 25));
        lblRiceSubsidy.setMinimumSize(new java.awt.Dimension(93, 25));
        lblRiceSubsidy.setOpaque(true);

        txtClothingAllowance.setEditable(false);
        txtClothingAllowance.setBackground(new java.awt.Color(242, 242, 242));
        txtClothingAllowance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClothingAllowance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtClothingAllowance.setFocusable(false);

        lblClothingAllowance.setBackground(new java.awt.Color(242, 242, 242));
        lblClothingAllowance.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblClothingAllowance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClothingAllowance.setText("Clothing Allowance:");
        lblClothingAllowance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblClothingAllowance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblClothingAllowance.setMaximumSize(new java.awt.Dimension(93, 25));
        lblClothingAllowance.setMinimumSize(new java.awt.Dimension(93, 25));
        lblClothingAllowance.setOpaque(true);

        txtHourlyRate.setEditable(false);
        txtHourlyRate.setBackground(new java.awt.Color(242, 242, 242));
        txtHourlyRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHourlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtHourlyRate.setFocusable(false);

        lblHourlyRate.setBackground(new java.awt.Color(242, 242, 242));
        lblHourlyRate.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblHourlyRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHourlyRate.setText("Hourly Rate:");
        lblHourlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblHourlyRate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHourlyRate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblHourlyRate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblHourlyRate.setOpaque(true);

        lblFirstName.setBackground(new java.awt.Color(242, 242, 242));
        lblFirstName.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblFirstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFirstName.setText("First Name:");
        lblFirstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblFirstName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFirstName.setMaximumSize(new java.awt.Dimension(93, 25));
        lblFirstName.setMinimumSize(new java.awt.Dimension(93, 25));
        lblFirstName.setOpaque(true);

        txtFirstName.setEditable(false);
        txtFirstName.setBackground(new java.awt.Color(242, 242, 242));
        txtFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFirstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtFirstName.setFocusable(false);

        lblBottomSeparator.setBackground(new java.awt.Color(51, 51, 51));
        lblBottomSeparator.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblBottomSeparator.setForeground(new java.awt.Color(255, 255, 255));
        lblBottomSeparator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBottomSeparator.setOpaque(true);

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnExit.setText("Exit");
        btnExit.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.setFocusable(false);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setEnabled(false);
        btnSave.setFocusable(false);
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setFocusable(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnDeleteInfo.setBackground(new java.awt.Color(199, 73, 73));
        btnDeleteInfo.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnDeleteInfo.setText("Delete Information");
        btnDeleteInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDeleteInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteInfo.setEnabled(false);
        btnDeleteInfo.setFocusable(false);
        btnDeleteInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInfoActionPerformed(evt);
            }
        });

        btnUpdateInfo.setBackground(new java.awt.Color(73, 199, 73));
        btnUpdateInfo.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnUpdateInfo.setText("Update Information");
        btnUpdateInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnUpdateInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateInfo.setEnabled(false);
        btnUpdateInfo.setFocusable(false);
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });

        lblEmployeeNumber.setBackground(new java.awt.Color(242, 242, 242));
        lblEmployeeNumber.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeNumber.setText("Employee #");
        lblEmployeeNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblEmployeeNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblEmployeeNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblEmployeeNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblEmployeeNumber.setOpaque(true);

        txtEmployeeNumber.setEditable(false);
        txtEmployeeNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtEmployeeNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmployeeNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtEmployeeNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtEmployeeNumber.setFocusable(false);

        lblEmployeeInformationHeader1.setBackground(new java.awt.Color(223, 54, 54));
        lblEmployeeInformationHeader1.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblEmployeeInformationHeader1.setForeground(new java.awt.Color(255, 255, 255));
        lblEmployeeInformationHeader1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeInformationHeader1.setText("Wage Information");
        lblEmployeeInformationHeader1.setOpaque(true);

        lblGrossWage.setBackground(new java.awt.Color(242, 242, 242));
        lblGrossWage.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGrossWage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGrossWage.setText("Gross Wage:");
        lblGrossWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblGrossWage.setMaximumSize(new java.awt.Dimension(93, 25));
        lblGrossWage.setMinimumSize(new java.awt.Dimension(93, 25));
        lblGrossWage.setOpaque(true);

        txtGrossWage.setEditable(false);
        txtGrossWage.setBackground(new java.awt.Color(242, 242, 242));
        txtGrossWage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrossWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtGrossWage.setFocusable(false);

        txtSssDeduction.setEditable(false);
        txtSssDeduction.setBackground(new java.awt.Color(242, 242, 242));
        txtSssDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSssDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtSssDeduction.setFocusable(false);

        lblSssDeduction.setBackground(new java.awt.Color(242, 242, 242));
        lblSssDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblSssDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSssDeduction.setText("SSS Deduction:");
        lblSssDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblSssDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblSssDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblSssDeduction.setOpaque(true);

        lblPhilHealthDeduction.setBackground(new java.awt.Color(242, 242, 242));
        lblPhilHealthDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhilHealthDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhilHealthDeduction.setText("PhilHealth Deduction:");
        lblPhilHealthDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPhilHealthDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthDeduction.setOpaque(true);

        txtPhilHealthDeduction.setEditable(false);
        txtPhilHealthDeduction.setBackground(new java.awt.Color(242, 242, 242));
        txtPhilHealthDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhilHealthDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPhilHealthDeduction.setFocusable(false);

        txtPagIbigDeduction.setEditable(false);
        txtPagIbigDeduction.setBackground(new java.awt.Color(242, 242, 242));
        txtPagIbigDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagIbigDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPagIbigDeduction.setFocusable(false);

        lblPagIbigDeduction.setBackground(new java.awt.Color(242, 242, 242));
        lblPagIbigDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPagIbigDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagIbigDeduction.setText("Pag-IBIG Deduction:");
        lblPagIbigDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPagIbigDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPagIbigDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPagIbigDeduction.setOpaque(true);

        lblWithholdingTax.setBackground(new java.awt.Color(242, 242, 242));
        lblWithholdingTax.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblWithholdingTax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWithholdingTax.setText("Withholding Tax:");
        lblWithholdingTax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblWithholdingTax.setMaximumSize(new java.awt.Dimension(93, 25));
        lblWithholdingTax.setMinimumSize(new java.awt.Dimension(93, 25));
        lblWithholdingTax.setOpaque(true);

        txtWithholdingTax.setEditable(false);
        txtWithholdingTax.setBackground(new java.awt.Color(242, 242, 242));
        txtWithholdingTax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWithholdingTax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtWithholdingTax.setFocusable(false);

        txtLateArrivalDeduction.setEditable(false);
        txtLateArrivalDeduction.setBackground(new java.awt.Color(242, 242, 242));
        txtLateArrivalDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLateArrivalDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtLateArrivalDeduction.setFocusable(false);

        lblLateArrivalDeduction.setBackground(new java.awt.Color(242, 242, 242));
        lblLateArrivalDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblLateArrivalDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLateArrivalDeduction.setText("Late Arrival Deduction:");
        lblLateArrivalDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblLateArrivalDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblLateArrivalDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblLateArrivalDeduction.setOpaque(true);

        lblTotalDeductions.setBackground(new java.awt.Color(242, 242, 242));
        lblTotalDeductions.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTotalDeductions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDeductions.setText("Total Deductions:");
        lblTotalDeductions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblTotalDeductions.setMaximumSize(new java.awt.Dimension(93, 25));
        lblTotalDeductions.setMinimumSize(new java.awt.Dimension(93, 25));
        lblTotalDeductions.setOpaque(true);

        txtTotalDeductions.setEditable(false);
        txtTotalDeductions.setBackground(new java.awt.Color(242, 242, 242));
        txtTotalDeductions.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalDeductions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTotalDeductions.setFocusable(false);

        txtNetWage.setEditable(false);
        txtNetWage.setBackground(new java.awt.Color(242, 242, 242));
        txtNetWage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNetWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtNetWage.setFocusable(false);

        lblNetWage.setBackground(new java.awt.Color(242, 242, 242));
        lblNetWage.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblNetWage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNetWage.setText("Net Wage:");
        lblNetWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblNetWage.setMaximumSize(new java.awt.Dimension(93, 25));
        lblNetWage.setMinimumSize(new java.awt.Dimension(93, 25));
        lblNetWage.setOpaque(true);

        lblMonth.setBackground(new java.awt.Color(242, 242, 242));
        lblMonth.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonth.setText("Month:");
        lblMonth.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblMonth.setMaximumSize(new java.awt.Dimension(93, 25));
        lblMonth.setMinimumSize(new java.awt.Dimension(93, 25));
        lblMonth.setOpaque(true);

        txtMonth.setEditable(false);
        txtMonth.setBackground(new java.awt.Color(242, 242, 242));
        txtMonth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonth.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtMonth.setFocusable(false);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBottomSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblEmployeeInformationHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmployeeNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastName))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstName))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBirthdate))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSssNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSssNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPhilHealthNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhilHealthNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTinNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTinNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPagIbigNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPagIbigNumber))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPosition))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblImmediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImmediateSupervisor))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBasicSalary))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblRiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRiceSubsidy))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneAllowance))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClothingAllowance))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblGrossSemimonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrossSemimonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnDeleteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnUpdateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addComponent(lblEmployeeInformationHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNetWage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalDeductions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLateArrivalDeduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWithholdingTax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPagIbigDeduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPhilHealthDeduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSssDeduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGrossWage, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGrossWage, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSssDeduction, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhilHealthDeduction, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPagIbigDeduction, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtWithholdingTax, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtLateArrivalDeduction, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotalDeductions, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNetWage, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMonth))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEmployeeInformationHeader)
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSssNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSssNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhilHealthNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhilHealthNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTinNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagIbigNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagIbigNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImmediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImmediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrossSemimonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrossSemimonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblEmployeeInformationHeader1)
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGrossWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrossWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSssDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSssDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhilHealthDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhilHealthDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagIbigDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPagIbigDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLateArrivalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLateArrivalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNetWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNetWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblBottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPaneMain.setViewportView(pnlMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action event of the back button to close the current page.
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Close the current page
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * Handles mouse exit event on the back button by resetting its background
     * color.
     */
    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        btnBack.setBackground(WHITE);
    }//GEN-LAST:event_btnBackMouseExited

    /**
     * Handles mouse hover event on the back button by changing its background
     * color.
     */
    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        btnBack.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnBackMouseEntered

    /**
     * Handles the action event of the exit button to exit the application.
     */
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // Exit the application
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * Handles mouse exit event on the exit button by resetting its background
     * color.
     */
    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(WHITE);
    }//GEN-LAST:event_btnExitMouseExited

    /**
     * Handles mouse hover event on the exit button by changing its background
     * color.
     */
    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(RED);
    }//GEN-LAST:event_btnExitMouseEntered

    /**
     * Updates the employee information and sets fields non-editable.
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        updateEmployeeInformation();
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * Handles mouse hover event on the save button by resetting its background
     * color.
     */
    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        btnSave.setBackground(WHITE);
    }//GEN-LAST:event_btnSaveMouseExited

    /**
     * Handles mouse hover event on the save button by changing its background
     * color.
     */
    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        btnSave.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnSaveMouseEntered

    /**
     * Sets the fields editable for updating employee information.
     */
    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInfoActionPerformed
        deleteButtonClicked = false;
        // Set fields editable for updating
        setFieldsEditable(true);
    }//GEN-LAST:event_btnUpdateInfoActionPerformed

    /**
     * Sets the fields editable for deleting employee information.
     */
    private void btnDeleteInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInfoActionPerformed
        deleteButtonClicked = true;
        // Set fields editable for deleting
        setFieldsEditable(true);
    }//GEN-LAST:event_btnDeleteInfoActionPerformed

    /**
     * Updates the employee information text fields for the chosen employee.
     *
     * @param employeeInfo The information of the employee
     */
    public void updateEmployeeInformationFields(List<String> employeeInfo) {
        JTextField[] textFields = {txtEmployeeNumber, txtLastName, txtFirstName, txtBirthdate, txtAddress, txtPhoneNumber,
            txtSssNumber, txtPhilHealthNumber, txtTinNumber, txtPagIbigNumber, txtStatus, txtPosition,
            txtImmediateSupervisor, txtBasicSalary, txtRiceSubsidy, txtPhoneAllowance, txtClothingAllowance,
            txtGrossSemimonthlyRate, txtHourlyRate};

        // Set the text of each field with the employee information
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText(employeeInfo.get(i));
        }
    }

    /**
     * Populates employee information fields based on the provided employee
     * number.
     *
     * @param employeeNumberString The employee number as a string.
     */
    public void populateEmployeeInformation(String employeeNumberString) {
        try {
            // Parse the employee number from the text field
            int employeeNumber = Integer.parseInt(employeeNumberString);

            // Get employee information
            List<String> employeeInfo = new EmployeeInformation().showEmployeeInformation(employeeNumber);

            // Populate text fields with employee information
            updateEmployeeInformationFields(employeeInfo);

            // Enable the delete and update buttons
            btnDeleteInfo.setEnabled(true);
            btnUpdateInfo.setEnabled(true);
        } catch (IOException | ParseException | CsvValidationException | IllegalArgumentException e) {
            // Show error dialog with the exception message
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Populates wage information fields based on the provided employee number
     * and selected month.
     *
     * @param selectedMonth The selected month for which the wage information is
     * displayed
     */
    public void populateWageInformation(String selectedMonth) {
        try {
            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());

            // Get employee information
            new EmployeeInformation().showEmployeeInformation(employeeNumber);

            // Create a DateRange object based on the selected month
            DateRange dateRange = createMonthRange(selectedMonth);

            // Create an instance of NetWageCalculation
            WageCalculation wageCalculation = new WageCalculation();

            // Get wage information for the given employee number and date range
            List<String> wageInfo = wageCalculation.calculateWage(employeeNumber, dateRange);

            // Display the net wage in the appropriate text fields
            updateWageInformationFields(wageInfo, selectedMonth);
        } catch (IOException | ParseException | CsvValidationException | IllegalArgumentException e) {
            // Show error dialog with the exception message
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Updates the wage information text fields for the chosen employee.
     *
     * @param wageInfo The wage information of the employee to display
     * @param selectedMonth The selected month for which the wage information is
     * displayed
     */
    public void updateWageInformationFields(List<String> wageInfo, String selectedMonth) {
        String monthName = getMonthName(selectedMonth);

        txtMonth.setText(monthName);
        txtGrossWage.setText(wageInfo.get(0));
        txtSssDeduction.setText(wageInfo.get(1));
        txtPhilHealthDeduction.setText(wageInfo.get(2));
        txtPagIbigDeduction.setText(wageInfo.get(3));
        txtWithholdingTax.setText(wageInfo.get(4));
        txtLateArrivalDeduction.setText(wageInfo.get(5));
        txtTotalDeductions.setText(wageInfo.get(6));
        txtNetWage.setText(wageInfo.get(7));
    }

    /**
     * Gets the name of the month based on the provided month number.
     *
     * @param monthNumber The month number as a string
     * @return The name of the month.
     * @throws IllegalArgumentException If the month number is invalid (not
     * between 1 and 12).
     */
    public String getMonthName(String monthNumber) throws IllegalArgumentException {
        int monthInt = Integer.parseInt(monthNumber);
        if (monthInt < 1 || monthInt > 12) {
            showErrorDialog("Invalid month number: " + monthNumber);
        }

        Month month = Month.of(monthInt);
        // Convert to lowercase
        String monthName = month.toString().toLowerCase();
        // Capitalize first letter
        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);

        return monthName;
    }

    /**
     * Displays an error dialog with the provided error message.
     *
     * @param errorMessage The error message to display in the dialog.
     */
    @Override
    public void showErrorDialog(String errorMessage) {
        // Show a dialog with the error message
        JOptionPane.showMessageDialog(pnlMain, "Error updating employee information: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Assigns a mouse click event handler to a text field.
     *
     * @param textField JTextField to assign the click handler
     */
    private void assignClickHandlerToTextField(JTextField textField) {
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When the mouse is clicked on the text field, call handleTextFieldClick method
                handleTextFieldClick(textField);
            }
        });
    }

    /**
     * Assigns mouse click event handlers to all text fields.
     */
    private void assignClickHandlersToTextFields() {
        JTextField[] textFields = {
            txtLastName, txtFirstName, txtBirthdate, txtAddress, txtPhoneNumber,
            txtSssNumber, txtPhilHealthNumber, txtTinNumber, txtPagIbigNumber,
            txtStatus, txtPosition, txtImmediateSupervisor, txtBasicSalary,
            txtRiceSubsidy, txtPhoneAllowance, txtClothingAllowance,
            txtGrossSemimonthlyRate, txtHourlyRate
        };

        // Assign click event handler to each text field
        for (JTextField field : textFields) {
            assignClickHandlerToTextField(field);
        }
    }

    /**
     * Handles mouse click event on a text field.
     *
     * @param textField JTextField that was clicked
     */
    private void handleTextFieldClick(JTextField textField) {
        // If deleteButtonClicked is true
        if (deleteButtonClicked) {
            // Clear the text field
            textField.setText("");
        }
    }

    /**
     * Sets the editability of the fields.
     *
     * @param allowed A boolean for indicating whether the fields should be
     * editable.
     */
    private void setFieldsEditable(boolean allowed) {
        // Define an array of text fields
        JTextField[] textFields = {
            txtLastName, txtFirstName, txtBirthdate, txtAddress, txtPhoneNumber,
            txtSssNumber, txtPhilHealthNumber, txtTinNumber, txtPagIbigNumber,
            txtStatus, txtPosition, txtImmediateSupervisor, txtBasicSalary,
            txtRiceSubsidy, txtPhoneAllowance, txtClothingAllowance,
            txtGrossSemimonthlyRate, txtHourlyRate
        };

        // Loop through each text field
        for (JTextField field : textFields) {
            // Change text fields' background color
            field.setBackground(allowed ? WHITE : GRAY);

            if (deleteButtonClicked) {
                field.setCursor(new Cursor(Cursor.HAND_CURSOR));
                // Set text fields to non-editable
                field.setEditable(false);
                field.setFocusable(false);
            } else {
                deleteButtonClicked = false;
                field.setCursor(new Cursor(Cursor.TEXT_CURSOR));
                // Set text fields to editable
                field.setEditable(allowed);
                field.setFocusable(allowed);
            }
        }

        // Enable the save button
        btnSave.setEnabled(true);
    }

    /**
     * Gathers employee information from the fields.
     *
     * @return A list of strings that contains the employee information.
     */
    private List<String> gatherEmployeeInformationFromFields() {
        List<String> employeeInfo = new ArrayList<>();

        // Add the text of each field to the list
        employeeInfo.add(txtLastName.getText());
        employeeInfo.add(txtFirstName.getText());
        employeeInfo.add(txtBirthdate.getText());
        employeeInfo.add(txtAddress.getText());
        employeeInfo.add(txtPhoneNumber.getText());
        employeeInfo.add(txtSssNumber.getText());
        employeeInfo.add(txtPhilHealthNumber.getText());
        employeeInfo.add(txtTinNumber.getText());
        employeeInfo.add(txtPagIbigNumber.getText());
        employeeInfo.add(txtStatus.getText());
        employeeInfo.add(txtPosition.getText());
        employeeInfo.add(txtImmediateSupervisor.getText());
        employeeInfo.add(txtBasicSalary.getText());
        employeeInfo.add(txtRiceSubsidy.getText());
        employeeInfo.add(txtPhoneAllowance.getText());
        employeeInfo.add(txtClothingAllowance.getText());
        employeeInfo.add(txtGrossSemimonthlyRate.getText());
        employeeInfo.add(txtHourlyRate.getText());

        return employeeInfo;
    }

    /**
     * Updates the employee information based on input from fields. Gets data
     * and updates CSV files.
     */
    private void updateEmployeeInformation() {
        try {
            // Parse the employee number from the text field
            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());

            // Gather the updated employee information from the fields
            List<String> updatedEmployeeInfo = gatherEmployeeInformationFromFields();

            // Update the employee information in the CSV file
            new EmployeeInformation().updateEmployeeInformationInCsv(employeeNumber, updatedEmployeeInfo);

            // Set fields non-editable after saving
            setFieldsEditable(false);
            // Set deleteButtonClicked value to false
            deleteButtonClicked = false;
        } catch (IOException | ParseException | CsvValidationException | IllegalArgumentException e) {
            // Show error dialog with the exception message
            showErrorDialog(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDeleteInfo;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdateInfo;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBasicSalary;
    private javax.swing.JLabel lblBirthdate;
    private javax.swing.JLabel lblBottomSeparator;
    private javax.swing.JLabel lblClothingAllowance;
    private javax.swing.JLabel lblEmployeeInformationHeader;
    private javax.swing.JLabel lblEmployeeInformationHeader1;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGrossSemimonthlyRate;
    private javax.swing.JLabel lblGrossWage;
    private javax.swing.JLabel lblHourlyRate;
    private javax.swing.JLabel lblImmediateSupervisor;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblLateArrivalDeduction;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblMotorPhHeader;
    private javax.swing.JLabel lblNetWage;
    private javax.swing.JLabel lblPagIbigDeduction;
    private javax.swing.JLabel lblPagIbigNumber;
    private javax.swing.JLabel lblPhilHealthDeduction;
    private javax.swing.JLabel lblPhilHealthNumber;
    private javax.swing.JLabel lblPhoneAllowance;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblRiceSubsidy;
    private javax.swing.JLabel lblSssDeduction;
    private javax.swing.JLabel lblSssNumber;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTinNumber;
    private javax.swing.JLabel lblTotalDeductions;
    private javax.swing.JLabel lblWithholdingTax;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scrollPaneMain;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBasicSalary;
    private javax.swing.JTextField txtBirthdate;
    private javax.swing.JTextField txtClothingAllowance;
    private javax.swing.JTextField txtEmployeeNumber;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGrossSemimonthlyRate;
    private javax.swing.JTextField txtGrossWage;
    private javax.swing.JTextField txtHourlyRate;
    private javax.swing.JTextField txtImmediateSupervisor;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLateArrivalDeduction;
    private javax.swing.JTextField txtMonth;
    private javax.swing.JTextField txtNetWage;
    private javax.swing.JTextField txtPagIbigDeduction;
    private javax.swing.JTextField txtPagIbigNumber;
    private javax.swing.JTextField txtPhilHealthDeduction;
    private javax.swing.JTextField txtPhilHealthNumber;
    private javax.swing.JTextField txtPhoneAllowance;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtRiceSubsidy;
    private javax.swing.JTextField txtSssDeduction;
    private javax.swing.JTextField txtSssNumber;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTinNumber;
    private javax.swing.JTextField txtTotalDeductions;
    private javax.swing.JTextField txtWithholdingTax;
    // End of variables declaration//GEN-END:variables
}
