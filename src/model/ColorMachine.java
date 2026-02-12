package model;

public class ColorMachine extends PrintingMachine {

    public ColorMachine(String id, int maxSheets, int ppm) {
        super(id, maxSheets, ppm);
    }

    @Override
    public boolean canPrintColor() {
        return true;
    }
}
