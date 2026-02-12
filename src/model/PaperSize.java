package model;

public enum PaperSize {
    A1, A2, A3, A4, A5;


    public double getMultiplier() {
        return switch (this) {
            case A5 -> 1.0;
            case A4 -> 1.2;
            case A3 -> 1.4;
            case A2 -> 1.6;
            case A1 -> 1.8;
        };
    }
}
