package dmt;

import java.sql.Date;
import java.util.ArrayList;

import dmt.Data.DatabaseHandler;

public class ContentItem {
    private int id;
    private Date datePublished;
    private String status; // concept, actief, gearchiveerd
    private String title;
    private String description;
    private int progress;

    public ContentItem(int id, Date datePublished, String status, String title, String description, int progress) {
        this.id = id;
        this.datePublished = datePublished;
        this.status = status;
        this.title = title;
        this.description = description;
        this.progress = progress;

    }

    public double getProgressModule() { // progress of module
        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        ArrayList<ContentItem> modulesProgress = databaseHandler.retrieveProgressModule(this.id);
        if (modulesProgress.size() == 0) {
            return 0;
        }
        double average = modulesProgress.stream()
                .mapToInt(module -> module.getProgress())
                .average()
                .getAsDouble();
        return average;
    }

    public int getId() {
        return this.id;
    }

    public Date getDatePublished() {
        return this.datePublished;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getProgress() {
        return this.progress;
    }

}