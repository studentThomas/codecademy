package dmt;

import java.sql.Date;

public class Webcast extends ContentItem {
    private String title;
    private String description;
    private String speakerName;
    private String organization;

    public Webcast(int id, Date datePublished, String status, String title, String description, String speakerName,
            String organization) {
        super(id, datePublished, status);
        this.title = title;
        this.description = description;
        this.speakerName = speakerName;
        this.organization = organization;
    }

    // Getters and setters for the class variables
}
