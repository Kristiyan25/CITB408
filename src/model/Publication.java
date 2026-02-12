package model;

import java.io.Serializable;

public class Publication implements Serializable {

    private String title;
    private int pages;
    private PaperSize size;

    public Publication(String title, int pages, PaperSize size) {
        this.title = title;
        this.pages = pages;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public PaperSize getSize() {
        return size;
    }
}
