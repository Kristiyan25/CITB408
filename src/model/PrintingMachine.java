package model;

import exceptions.UnsupportedPrintTypeException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class PrintingMachine implements Serializable {

    private String id;
    private int maxSheets;
    private int loadedSheets;
    private int pagesPerMinute;

    private Map<Publication, Integer> printedPublications;

    public PrintingMachine(String id, int maxSheets, int pagesPerMinute) {
        this.id = id;
        this.maxSheets = maxSheets;
        this.pagesPerMinute = pagesPerMinute;
        this.loadedSheets = 0;
        this.printedPublications = new HashMap<>();
    }

    public abstract boolean canPrintColor();

    public void loadPaper(int sheets) {
        if (loadedSheets + sheets > maxSheets) {
            throw new IllegalArgumentException("Too much paper loaded!");
        }
        loadedSheets += sheets;
    }

    public void print(Publication pub, int copies, boolean needColor)
            throws UnsupportedPrintTypeException {

        if (needColor && !canPrintColor()) {
            throw new UnsupportedPrintTypeException(
                    "Machine " + id + " cannot print in color!"
            );
        }

        int neededSheets = pub.getPages() * copies;

        if (neededSheets > loadedSheets) {
            throw new IllegalArgumentException("Not enough paper loaded!");
        }

        loadedSheets -= neededSheets;

        printedPublications.put(
                pub,
                printedPublications.getOrDefault(pub, 0) + copies
        );

        System.out.println("Printed " + copies + " copies of " + pub.getTitle());
    }


    public int getTotalPrintedPages() {

        int totalPages = 0;

        for (Map.Entry<Publication, Integer> entry : printedPublications.entrySet()) {

            Publication pub = entry.getKey();
            int copies = entry.getValue();

            totalPages += pub.getPages() * copies;
        }

        return totalPages;
    }


    public String getId() {
        return id;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }
}
