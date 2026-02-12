package model;

public class BlackWhiteMachine extends PrintingMachine {

    public BlackWhiteMachine(String id, int maxSheets, int ppm) {
        super(id, maxSheets, ppm);
    }

    @Override
    public boolean canPrintColor() {
        return false;
    }
}
