
package com.motorph.project;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String birthday;
    private double hourlyRate;
    private Map<String, Integer> hoursWorkedPerDay;
    private double monthlyBasicSalary;

    // Constructor
    public Employee(String employeeNumber, String firstName, String lastName, String birthday, double hourlyRate, double monthlyBasicSalary) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hourlyRate = hourlyRate;
        this.hoursWorkedPerDay = new HashMap<>();
        this.monthlyBasicSalary = monthlyBasicSalary;
    }

    // Getter methods
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

   

    // Method to update hours worked
    public void updateHoursWorked(String dayOfWeek, int hours) {
        hoursWorkedPerDay.put(dayOfWeek, hours);
    }

    // Method to calculate total hours worked in a week
    public int calculateTotalHoursWorkedInWeek() {
        int totalHours = 0;
        for (int hours : hoursWorkedPerDay.values()) {
            totalHours += hours;
        }
        return totalHours;
    }

    // Method to calculate gross weekly salary
    public double calculateGrossWeeklySalary() {
        int totalHours = calculateTotalHoursWorkedInWeek();
        return hourlyRate * totalHours;
    }
    public double getMonthlyBasicSalary() {
        return monthlyBasicSalary;
    }

    // Method to calculate net weekly salary after deductions
    public double calculateNetWeeklySalary() {
        double grossSalary = calculateGrossWeeklySalary();
        double sssContribution = calculateSSSContribution(grossSalary);
        double pagibigContribution = calculatePagIBIGContribution(grossSalary);
        double philhealthContribution = calculatePhilHealthContribution(grossSalary);
        double taxWithholding = calculateTaxWithholding(grossSalary);
        double netSalary = grossSalary - sssContribution - pagibigContribution - philhealthContribution - taxWithholding;
        
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // In the calculateNetWeeklySalary() method
        System.out.println("DEDUCTIONS: \n");
        System.out.println("SSS Contribution: PHP" + decimalFormat.format(sssContribution));
        System.out.println("PAGIBIG Contribution: PHP" + decimalFormat.format(pagibigContribution));
        System.out.println("PHILHEALTH Contribution: PHP" + decimalFormat.format(philhealthContribution));
        System.out.println("Tax Withholding: PHP" + decimalFormat.format(taxWithholding));
        
        return netSalary;
    }

    // SSS contribution
    private double calculateSSSContribution(double monthlyBasicSalary) {
        if (monthlyBasicSalary < 3250) {
            return 135.00;
        } else if (monthlyBasicSalary >= 3250 && monthlyBasicSalary < 3750) {
            return 157.50;
        } else if (monthlyBasicSalary >= 3750 && monthlyBasicSalary < 4250) {
            return 180.00;
        } else if (monthlyBasicSalary >= 4250 && monthlyBasicSalary < 4750) {
            return 202.50;
        } else if (monthlyBasicSalary >= 4750 && monthlyBasicSalary < 5250) {
            return 225.00;
        } else if (monthlyBasicSalary >= 5250 && monthlyBasicSalary < 5750) {
            return 247.50;
        } else if (monthlyBasicSalary >= 5750 && monthlyBasicSalary < 6250) {
            return 270.00;
        } else if (monthlyBasicSalary >= 6250 && monthlyBasicSalary < 6750) {
            return 292.50;
        } else if (monthlyBasicSalary >= 6750 && monthlyBasicSalary < 7250) {
            return 315.00;
        } else if (monthlyBasicSalary >= 7250 && monthlyBasicSalary < 7750) {
            return 337.50;
        } else if (monthlyBasicSalary >= 7750 && monthlyBasicSalary < 8250) {
            return 360.00;
        } else if (monthlyBasicSalary >= 8250 && monthlyBasicSalary < 8750) {
            return 382.50;
        } else if (monthlyBasicSalary >= 8750 && monthlyBasicSalary < 9250) {
            return 405.00;
        } else if (monthlyBasicSalary >= 9250 && monthlyBasicSalary < 9750) {
            return 427.50;
        } else if (monthlyBasicSalary >= 9750 && monthlyBasicSalary < 10250) {
            return 450.00;
        } else if (monthlyBasicSalary >= 10250 && monthlyBasicSalary < 10750) {
            return 472.50;
        } else if (monthlyBasicSalary >= 10750 && monthlyBasicSalary < 11250) {
            return 495.00;
        } else if (monthlyBasicSalary >= 11250 && monthlyBasicSalary < 11750) {
            return 517.50;
        } else if (monthlyBasicSalary >= 11750 && monthlyBasicSalary < 12250) {
            return 540.00;
        } else if (monthlyBasicSalary >= 12250 && monthlyBasicSalary < 12750) {
            return 562.50;
        } else if (monthlyBasicSalary >= 12750 && monthlyBasicSalary < 13250) {
            return 585.00;
        } else if (monthlyBasicSalary >= 13250 && monthlyBasicSalary < 13750) {
            return 607.50;
        } else if (monthlyBasicSalary >= 13750 && monthlyBasicSalary < 14250) {
            return 630.00;
        } else if (monthlyBasicSalary >= 14250 && monthlyBasicSalary < 14750) {
            return 652.50;
        } else if (monthlyBasicSalary >= 14750 && monthlyBasicSalary < 15250) {
            return 675.00;
        } else if (monthlyBasicSalary >= 15250 && monthlyBasicSalary < 15750) {
            return 697.50;
        } else if (monthlyBasicSalary >= 15750 && monthlyBasicSalary < 16250) {
            return 720.00;
        } else if (monthlyBasicSalary >= 16250 && monthlyBasicSalary < 16750) {
            return 742.50;
        } else if (monthlyBasicSalary >= 16750 && monthlyBasicSalary < 17250) {
            return 765.00;
        } else if (monthlyBasicSalary >= 17250 && monthlyBasicSalary < 17750) {
            return 787.50;
        } else if (monthlyBasicSalary >= 17750 && monthlyBasicSalary < 18250) {
            return 810.00;
        } else if (monthlyBasicSalary >= 18250 && monthlyBasicSalary < 18750) {
            return 832.50;
        } else if (monthlyBasicSalary >= 18750 && monthlyBasicSalary < 19250) {
            return 855.00;
        } else if (monthlyBasicSalary >= 19250 && monthlyBasicSalary < 19750) {
            return 877.50;
        } else if (monthlyBasicSalary >= 19750 && monthlyBasicSalary < 20250) {
            return 900.00;
        } else if (monthlyBasicSalary >= 20250 && monthlyBasicSalary < 20750) {
            return 922.50;
        } else if (monthlyBasicSalary >= 20750 && monthlyBasicSalary < 21250) {
            return 945.00;
        } else if (monthlyBasicSalary >= 21250 && monthlyBasicSalary < 21750) {
            return 967.50;
        } else if (monthlyBasicSalary >= 21750 && monthlyBasicSalary < 22250) {
            return 990.00;
        } else if (monthlyBasicSalary >= 22250 && monthlyBasicSalary < 22750) {
            return 1012.50;
        } else if (monthlyBasicSalary >= 22750 && monthlyBasicSalary < 23250) {
            return 1035.00;
        } else if (monthlyBasicSalary >= 23250 && monthlyBasicSalary < 23750) {
            return 1057.50;
        } else if (monthlyBasicSalary >= 23750 && monthlyBasicSalary < 24250) {
            return 1080.00;
        } else if (monthlyBasicSalary >= 24250 && monthlyBasicSalary < 24750) {
            return 1102.50;
        } else {
            return 1125.00;
        }
    }

    // Method to calculate Pag-IBIG contribution
    private double calculatePagIBIGContribution(double monthlyBasicSalary) {
        if (monthlyBasicSalary >= 1000 && monthlyBasicSalary <= 1500) {
            return monthlyBasicSalary * 0.01;
        } else if (monthlyBasicSalary > 1500) {
            return monthlyBasicSalary * 0.02;
        } else {
            return 0.0; // Default contribution if salary is not within any range
        }
     }

    // PhilHealth contribution
     private double calculatePhilHealthContribution(double monthlyBasicSalary) {
        if (monthlyBasicSalary <= 10000) {
            return monthlyBasicSalary * 0.03;
        } else if (monthlyBasicSalary > 10000 && monthlyBasicSalary <= 59999.99) {
            return 300.0;
        } else if (monthlyBasicSalary >= 60000) {
            return 1800.0;
        } else {
            return 0.0; // Default contribution if salary is not within any range
        }
     }
    // tax withholding
    private double calculateTaxWithholding(double netSalary) {
        if (netSalary <= 0) {
            return 0.0;
        } else if (netSalary > 0 && netSalary <= 20832) {
            return 0.0;
        } else if (netSalary > 20832 && netSalary <= 33333) {
            return (netSalary - 20832) * 0.20;
        } else if (netSalary > 33333 && netSalary <= 66667) {
            return 2500 + (netSalary - 33333) * 0.25;
        } else if (netSalary > 66667 && netSalary <= 166667) {
            return 10833 + (netSalary - 66667) * 0.30;
        } else if (netSalary > 166667 && netSalary <= 666667) {
            return 40833.33 + (netSalary - 166667) * 0.32;
        } else {
            return 200833.33 + (netSalary - 666667) * 0.35;
        }
    }
}
// Employees
public class Project {
    private static final Map<String, Employee> employees = new HashMap<>();

    // Initialize employee data
    static {
        employees.put("1", new Employee("1", "Manuel", "Garcia III", "10/11/1983", 535.71, 90000));
        employees.put("2", new Employee("2", "Antonio", "Lim", "06/19/1988", 357.14, 60000));
        employees.put("3", new Employee("3", "Bianca Sofia", "Aquino", "08/04/1989", 357.14, 60000));
        employees.put("4", new Employee("4", "Isabella", "Reyes", "06/16/1994", 357.14, 60000));
        employees.put("5", new Employee("5", "Eduard", "Hernandez", "09/23/1989", 313.51, 52670));
        employees.put("6", new Employee("6", "Andrea Mae", "Villanueva", "02/14/1988", 313.51, 52670));
        employees.put("7", new Employee("7", "Brad", "San Jose", "03/15/1996", 255.80, 42975));
        employees.put("8", new Employee("8", "Alice", "Romualdez", "05/14/1992", 133.93, 22500));
        employees.put("9", new Employee("9", "Rosie ", "Atienza", "1988-09-30", 133.93, 22500));
        employees.put("10", new Employee("10", "Roderick", "Alvaro", "03/30/1988", 313.51, 52670));
        employees.put("11", new Employee("11", "Anthony", "Salcedo", "09/14/1993", 302.53, 50825));
        employees.put("12", new Employee("12", "Josie ", "Lopez", "01/14/1987", 229.02, 38475));
        employees.put("13", new Employee("13", "Martha", "Farala", "01/11/1942", 142.86, 24000));
        employees.put("14", new Employee("14", "Leila", "Martinez", "07/11/1970", 142.86, 24000));
        employees.put("15", new Employee("15", "Fredrick ", "Romualdez", "03/10/1985", 318.45, 53500));
        employees.put("16", new Employee("16", "Christian", "Mata", "10/21/1987", 255.80, 42975));
        employees.put("17", new Employee("17", "Selena", "De Leon", "02/20/1975", 249.11, 41850));
        employees.put("18", new Employee("18", "Allison ", "San Jose", "06/24/1986", 133.93, 22500));
        employees.put("19", new Employee("19", "Cydney ", "Rosario", "10/06/1996", 133.93, 22500));
        employees.put("20", new Employee("20", "Mark", "Bautista", "02/12/1991", 138.39, 23250));
        employees.put("21", new Employee("21", "Darlene", "Lazaro", "11/25/1985", 138.39, 23250));
        employees.put("22", new Employee("22", "Kolby ", "Delos Santos", "02/26/1980", 142.86, 24000));
        employees.put("23", new Employee("23", "Vella", "Santos", "12/31/1983", 133.93, 22500));
        employees.put("24", new Employee("24", "Tomas", "Del Rosario", "12/18/1978", 133.93, 22500));
        employees.put("25", new Employee("25", "Jacklyn", "Tolentino", "05/19/1984", 142.86, 24000));
        employees.put("26", new Employee("26", "Percival", "Gutierrez", "12/18/1970", 147.32, 24750));
        employees.put("27", new Employee("27", "Garfield", "Manalaysay", "08/28/1986", 147.32, 24750));
        employees.put("28", new Employee("28", "Lizeth", "Villegas", "12/12/1981", 142.86, 24000));
        employees.put("29", new Employee("29", "Carol", "Ramos", "08/20/1978", 133.93, 22500));
        employees.put("30", new Employee("30", "Emelia", "Maceda", "04/14/1973", 133.93, 22500));
        employees.put("31", new Employee("31", "Delia", "Aguilar", "01/27/1989", 133.93, 22500));
        employees.put("32", new Employee("32", "John Rafael", "Castro", "02/09/1992", 313.51, 52670));
        employees.put("33", new Employee("33", "Carlos Ian", "Martinez", "11/16/1990", 313.51, 52670));
        employees.put("34", new Employee("34", "Beatriz", "Santos", "08/07/1990", 313.51, 52670));
    }
    // Welcome 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        
        System.out.print("\n Welcome!\n");
        System.out.print("Enter employee number: ");
        String employeeNumber = scanner.nextLine();

        Employee employee = employees.get(employeeNumber);
        if (employee != null) {
            System.out.println("Employee Information:");
            System.out.println("Employee Number: " + employee.getEmployeeNumber());
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Birthday: " + employee.getBirthday());
            System.out.println("Basic Salary: " + decimalFormat.format(employee.getMonthlyBasicSalary()));

             for (String dayOfWeek : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}) {
                System.out.print("Enter number of hours worked on " + dayOfWeek + ": ");
                int hoursWorked = scanner.nextInt();
                employee.updateHoursWorked(dayOfWeek, hoursWorked);
            }
            double grossWeeklySalary = employee.calculateGrossWeeklySalary();
            double netWeeklySalary = employee.calculateNetWeeklySalary();
            
            
            System.out.println("\n SALARY:\n");
            System.out.println("Gross Weekly Salary: PHP" + decimalFormat.format(grossWeeklySalary));
            System.out.println("Net Weekly Salary: PHP" + decimalFormat.format(netWeeklySalary));
        } else {
            System.out.println("Employee not found.");
        }
    }
}
