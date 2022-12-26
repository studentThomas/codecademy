package dmt;

import java.sql.Date;

public class ContentItem {
    private int id;
    private Date datePublished;
    private String status;

    public ContentItem(int id, Date datePublished, String status) {
        this.id = id;
        this.datePublished = datePublished;
        this.status = status;
    }

    // Getters and setters for the class variables
}
