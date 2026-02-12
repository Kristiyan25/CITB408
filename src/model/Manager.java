package model;

public class Manager extends Employee {

    private double bonusPercent;
    private PrintingHouse printingHouse;

    public Manager(String name,
                   double baseSalary,
                   double bonusPercent,
                   PrintingHouse printingHouse) {

        super(name, baseSalary);
        this.bonusPercent = bonusPercent;
        this.printingHouse = printingHouse;
    }

    @Override
    public double getSalary() {

        if (printingHouse.getRevenue() > printingHouse.getManagerBonusThreshold()) {
            return baseSalary * (1 + bonusPercent / 100.0);
        }

        return baseSalary;
    }
}
