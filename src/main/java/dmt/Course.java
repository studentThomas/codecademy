package dmt;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dmt.Data.DatabaseConnectionManager;
import dmt.Data.DatabaseHandler;
import javafx.scene.input.MouseButton;

public class Course {
    private int id;
    private String name;
    private String subject;
    private String introduction;
    private String level;
    private Date registrationDate;
    private ArrayList<ContentItem> modules;
    private DatabaseHandler databaseHandler;

    public Course(int id, String name, String subject, String introduction, String level, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.introduction = introduction;
        this.level = level;
        this.registrationDate = registrationDate;
        this.modules = new ArrayList<>();
    }

    public ArrayList<ContentItem> getModules(String email) {
        // this.databaseHandler = new DatabaseHandler(email);
        this.modules = databaseHandler.getModules(email, this.id);
        return this.modules;
    }

    public boolean checkCertificate() {
        for (ContentItem module : modules) {
            if (module.getProgress() < 100) {
                return false;
            }
        }
        return true;
    }

    public double checkProgressCourse(String email) { // progress of course
        this.databaseHandler = new DatabaseHandler(null);
        ArrayList<ContentItem> modules = getModules(email);
        double average = modules.stream()
                .mapToInt(module -> module.getProgress())
                .average()
                .getAsDouble();
        return average;
    }

    // public double getProgressModule(int moduleId) { // progress of module
    // this.databaseHandler = new DatabaseHandler(null);
    // ArrayList<ContentItem> modulesProgress =
    // this.databaseHandler.retrieveProgressModule(moduleId);
    // double average = modulesProgress.stream()
    // .mapToInt(module -> module.getProgress())
    // .average()
    // .getAsDouble();
    // return average;
    // }

    // public void checkProgressModule() {
    // try {
    // this.databaseHandler = new DatabaseHandler(null);
    // ArrayList<ContentItem> modules =
    // databaseHandler.retrieveCouresModules(this.id);
    // for (ContentItem module : modules) {
    // System.out.println(getProgressModule(module.getId()));
    // }
    // } catch (Exception e) {
    // System.out.println("Course does not have any modules in use");
    // }

    // }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public String getLevel() {
        return this.level;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", name='" + name + "'" +
                ", subject='" + subject + "'" +
                ", introduction='" + introduction + "'" +
                ", level='" + level + "'" +
                ", registrationDate='" + registrationDate + "'" +
                "}";
    }

}
