package dmt;

import java.sql.Date;

public class Webcast extends ContentItem {
    private String speakerName;
    private String organization;
    private int watchTime;
    private String url;

    public Webcast(int id, Date datePublished, String status, String title, String description, String speakerName,
            String organization, int progress, int watchTime, String url) {
        super(id, datePublished, status, title, description, progress);
        this.speakerName = speakerName;
        this.organization = organization;
        this.watchTime = watchTime;
        this.url = url;
    }

    public String getSpeakerName() {
        return this.speakerName;
    }

    public String getOrganization() {
        return this.organization;
    }

    public int getWatchTime() {
        return this.watchTime;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return "{" +
                " speakerName='" + speakerName + "'" +
                ", organization='" + organization + "'" +
                ", watchTime='" + watchTime + "'" +
                ", progress='" + getProgress() + "'" +
                "}";
    }

}
