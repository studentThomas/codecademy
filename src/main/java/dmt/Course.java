package dmt;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class Course {
    private int id;
    private String name;
    private String subject;
    private String introduction;
    private String level;
    private ArrayList<ContentItem> modules;

    public Course(int id, String name, String subject, String introduction, String level) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.introduction = introduction;
        this.level = level;
        this.modules = new ArrayList<>();
    }

    // TODO: Add certicaat

    // TODO: get data from database en print that

    // TODO: print all modules in course they have the same ID course -> serial
    // number module
    public ArrayList<ContentItem> getModules(String email) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Module JOIN ModuleViewed ON [Module].Id = ModuleViewed.Id WHERE ModuleViewed.Email = ? AND Module.SerialNumber = ?");
            query.setString(1, email);
            query.setInt(2, this.id);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int serialNumber = result.getInt("SerialNumber");
                String title = result.getString("Title");
                int version = result.getInt("Version");
                int progress = result.getInt("Progress");
                String description = result.getString("Description");
                String contactName = result.getString("ContactName");
                String contactEmail = result.getString("ContactEmail");
                Date publicationDate = result.getDate("PublicationDate");

                // System.out.println(serialNumber + " " + title + " " + progress);

                this.modules.add(new Module(progress, publicationDate, contactEmail, title, description, version,
                        contactName, contactEmail, progress, serialNumber));

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

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

    public double checkProgress() {
        double average = modules.stream()
                .mapToInt(module -> module.getProgress())
                .average()
                .getAsDouble();
        return average;
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

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", name='" + name + "'" +
                ", subject='" + subject + "'" +
                ", introduction='" + introduction + "'" +
                ", level='" + level + "'" +
                "}";
    }

}
