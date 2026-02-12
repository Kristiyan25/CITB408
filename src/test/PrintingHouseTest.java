package test;

import model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintingHouseTest {

    @Test
    public void testDiscountApplied() {

        PrintingHouse house = new PrintingHouse("TestHouse");

        Publication book =
                new Publication("Java Book", 100, PaperSize.A4);

        house.addRevenue(book, 100, 10);

        // 10% discount → price becomes 9
        assertEquals(900.0, house.getRevenue(), 0.01);
    }

    @Test
    public void testProfitCalculation() {

        PrintingHouse house = new PrintingHouse("TestHouse");

        house.addEmployee(new Operator("Ivan", 1000));

        Publication book =
                new Publication("Book", 50, PaperSize.A5);

        house.addRevenue(book, 10, 20);

        assertEquals(house.getProfit(),
                house.getRevenue() - house.getTotalExpenses(),
                0.01);
    }
}
