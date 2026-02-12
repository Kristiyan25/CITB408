package model;

import exceptions.UnsupportedPrintTypeException;

import java.io.Serializable;
import java.util.*;

public class PrintingHouse implements Serializable {

    private String name;

    private List<Employee> employees;
    private List<PrintingMachine> machines;

    private double revenue;
    private double paperExpenses;


    private int discountThreshold = 50;
    private double discountPercent = 10.0;


    private double managerBonusThreshold = 2000.0;



    private Map<PaperType, Double> basePricesA5;
    private double sizeIncreasePercent = 20.0;

    public PrintingHouse(String name) {
        this.name = name;

        employees = new ArrayList<>();
        machines = new ArrayList<>();

        revenue = 0;
        paperExpenses = 0;

        basePricesA5 = new HashMap<>();
        basePricesA5.put(PaperType.NORMAL, 0.10);
        basePricesA5.put(PaperType.GLOSSY, 0.20);
        basePricesA5.put(PaperType.NEWSPAPER, 0.05);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void buyPaper(Paper paper, int sheets) {

        double pricePerSheet =
                paper.getPricePerSheet(basePricesA5, sizeIncreasePercent);

        paperExpenses += pricePerSheet * sheets;
    }

    public void addRevenue(Publication pub, int copies, double pricePerCopy) {

        if (copies > discountThreshold) {
            pricePerCopy *= (1 - discountPercent / 100.0);
            System.out.println("Discount applied: " + discountPercent + "%");
        }

        revenue += copies * pricePerCopy;
    }


    public void printPublication(Publication pub,
                                 int copies,
                                 boolean needColor,
                                 double pricePerCopy)
            throws UnsupportedPrintTypeException {

        if (machines.isEmpty()) {
            throw new IllegalStateException("No machines available!");
        }

        PrintingMachine selectedMachine = null;

        for (PrintingMachine machine : machines) {

            // If color is needed → machine must support color
            if (needColor && machine.canPrintColor()) {
                selectedMachine = machine;
                break;
            }

            // If color is NOT needed → any machine works
            if (!needColor) {
                selectedMachine = machine;
                break;
            }
        }

        if (selectedMachine == null) {
            throw new UnsupportedPrintTypeException(
                    "No suitable machine available for this print type!"
            );
        }


        selectedMachine.print(pub, copies, needColor);

        addRevenue(pub, copies, pricePerCopy);
    }


    public double getSalaryExpenses() {
        double total = 0;
        for (Employee e : employees) {
            total += e.getSalary();
        }
        return total;
    }

    public double getTotalExpenses() {
        return paperExpenses + getSalaryExpenses();
    }

    public double getRevenue() {
        return revenue;
    }

    public double getManagerBonusThreshold() {
        return managerBonusThreshold;
    }

    public void setManagerBonusThreshold(double managerBonusThreshold) {
        this.managerBonusThreshold = managerBonusThreshold;
    }

    public double getProfit() {
        return revenue - getTotalExpenses();
    }

    @Override
    public String toString() {
        return "PrintingHouse: " + name +
                "\nRevenue: " + revenue +
                "\nExpenses: " + getTotalExpenses() +
                "\nProfit: " + getProfit();
    }
}
