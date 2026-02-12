import exceptions.UnsupportedPrintTypeException;
import model.*;
import utils.FileManager;

public class Main {

    public static void main(String[] args) {

        PrintingHouse house = new PrintingHouse("NBUPress");

        // Manager bonus threshold
        house.setManagerBonusThreshold(5000);

        // Employees
        house.addEmployee(new Operator("Ivan", 1200));

        house.addEmployee(new Manager(
                "Boss",
                2000,
                20,
                house
        ));

        // Machines
        PrintingMachine bw = new BlackWhiteMachine("M1", 5000, 60);
        PrintingMachine color = new ColorMachine("M2", 50000, 120);

        bw.loadPaper(3000);
        color.loadPaper(30000);

        house.addMachine(bw);
        house.addMachine(color);

        // Publication
        Publication book = new Publication("Java Book", 20, PaperSize.A4);

        // Buy enough paper (expense)
        house.buyPaper(new Paper(PaperType.NORMAL, PaperSize.A4), 3000);

        System.out.println("\n--- PRINTING HOUSE INFO ---");
        System.out.println(house);

        // Exception
        System.out.println("\n--- TRY COLOR PRINT ON BLACK-WHITE MACHINE ---");
        try {
            bw.print(book, 5, true);
        } catch (UnsupportedPrintTypeException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Black-white printing
        System.out.println("\n--- PRINT BLACK-WHITE ---");
        try {
            house.printPublication(book, 10, false, 20);
        } catch (UnsupportedPrintTypeException e) {
            System.out.println("Print error: " + e.getMessage());
        }

        // Color printing with discount
        System.out.println("\n--- PRINT WITH DISCOUNT (100 copies) ---");
        try {
            house.printPublication(book, 100, true, 50);
        } catch (UnsupportedPrintTypeException e) {
            System.out.println("Print error: " + e.getMessage());
        }

        // Machine statistics
        System.out.println("\n--- MACHINE STATISTICS ---");
        System.out.println("Total printed pages on color machine: "
                + color.getTotalPrintedPages());

        // Final info
        System.out.println("\n--- FINAL PRINTING HOUSE INFO ---");
        System.out.println(house);

        // Save + Load with exception
        System.out.println("\n--- SAVING TO FILE ---");

        try {
            FileManager.save(house, "printinghouse.dat");

            PrintingHouse loaded = FileManager.load("printinghouse.dat");

            System.out.println("\n--- LOADED FROM FILE ---");
            System.out.println(loaded);

        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }

    }
}
