package dmt;

import java.sql.Date;

public class Module extends ContentItem {

    private String title;
    private String version;
    private String description;
    private String contactName;
    private String contactEmail;

    public Module(int id, Date datePublished, String status, String title, String version, String description,
            String contactName, String contactEmail) {
        super(id, datePublished, status);
        this.title = title;
        this.version = version;
        this.description = description;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

}
