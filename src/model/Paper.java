package model;

import java.io.Serializable;
import java.util.Map;

public class Paper implements Serializable {

    private PaperType type;
    private PaperSize size;

    public Paper(PaperType type, PaperSize size) {
        this.type = type;
        this.size = size;
    }

    public double getPricePerSheet(Map<PaperType, Double> basePricesA5,
                                   double increasePercent) {

        double basePrice = basePricesA5.get(type);

        double multiplier = size.getMultiplier();

        return basePrice * multiplier * (1 + increasePercent / 100.0);
    }

    public PaperType getType() {
        return type;
    }

    public PaperSize getSize() {
        return size;
    }
}
