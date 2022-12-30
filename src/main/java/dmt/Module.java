package dmt;

import java.sql.Date;

public class Module extends ContentItem {
    private int serialNumber;
    private int version;
    private String contactName;
    private String contactEmail;

    public Module(int id, Date datePublished, String status, String title, String description, int version,
            String contactName, String contactEmail, int progress, int serialNumber) {
        super(id, datePublished, status, title, description, progress);
        this.version = version;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "{" +
                " serialNumber='" + serialNumber + "'" +
                ", version='" + version + "'" +
                ", contactName='" + contactName + "'" +
                ", contactEmail='" + contactEmail + "'" +
                "}";
    }

}