package dmt;

import java.sql.Date;
import java.util.ArrayList;

import dmt.Data.DatabaseConnectionManager;
// import dmt.Data.DatabaseHandler;
// import dmt.Data.DatabaseConnectionManager;

import java.sql.*;

public class Person {
    private String email;
    private String name;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String city;
    private String country;
    private ArrayList<Course> courses;
    private ArrayList<ContentItem> webcasts;
    private ArrayList<Certificate> certificates;
    private DatabaseHandler databaseHandler;

    public Person(String email, String name, Date dateOfBirth, String gender, String address, String city,
            String country) {
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.courses = new ArrayList<>();
        this.webcasts = new ArrayList<>();
        this.certificates = new ArrayList<>();
        this.databaseHandler = new DatabaseHandler();
    }

    public void getViewedWebcasts() {
        // ArrayList<ContentItem> wItems = databaseHandler.getViewedWebcasts2();
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Webcast JOIN WebcastViewed ON Webcast.Id = WebcastViewed.Id WHERE WebcastViewed.Email = ?");
            query.setString(1, this.email);

            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String speakerName = result.getString("SpeakerName");
                String organization = result.getString("Organization");
                int watchTime = result.getInt("WatchTime");
                Date publicationDate = result.getDate("PublicationDate");
                String url = result.getString("Url");
                int progress = result.getInt("Progress");

                // haal arralist op database.getviewewebcast

                this.webcasts.add(new Webcast(id, publicationDate, url, title, description, speakerName, organization,
                        progress, watchTime));

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

        for (ContentItem webcast : webcasts) {
            System.out.println(webcast);
        }

    }

    public void getEnrolledCourses() {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Course JOIN CourseEnrolled ON Course.Id = CourseEnrolled.Id WHERE CourseEnrolled.Email = ?");
            query.setString(1, this.email);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String name = result.getString("Name");
                String subject = result.getString("Subject");
                String introduction = result.getString("Introduction");
                String level = result.getString("Level");
                Date registrationDate = result.getDate("RegistrationDate");

                this.courses.add(new Course(id, name, subject, introduction, level, registrationDate));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Course course : courses) {
            System.out.println(course);
        }

    }

    public void getModules() {
        for (Course course : courses) {
            System.out.println(course.getModules(this.email) + "\n");
        }
    }

    public void getProgress() {
        for (Course course : courses) {
            System.out.println(course.checkProgress());
        }
    }

    public void setCertificate() {
        for (Course course : courses) {
            if (course.checkCertificate()) {
                System.out.println("Krijgt Certificate");
                try {
                    Connection connection = DatabaseConnectionManager.getInstance().getConnection();
                    PreparedStatement query = connection.prepareStatement(
                            "INSERT INTO Certificate (PersonEmail, CourseId, RegistrationDate) VALUES (?, ?, ?)");
                    query.setString(1, this.email);
                    query.setInt(2, course.getId());
                    query.setDate(3, course.getRegistrationDate());
                    query.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Geen certicase");

            }

        }
    }

    public void getCertificate() {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Certificate WHERE PersonEmail = ?");
            query.setString(1, this.email);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String personEmai = result.getString("PersonEmail");
                String courseId = result.getString("CourseId");
                Date registrationDate = result.getDate("RegistrationDate");

                this.certificates.add(new Certificate(id, personEmai, courseId, registrationDate));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Certificate certificate : certificates) {
            System.out.println(certificate);
        }
    }

    public void updateCertificate() {

    }

    public String toString() {
        return name + "\n" + email + "\n" + dateOfBirth + "\n" + gender + "\n" + address + "\n" + city + "\n" + country
                + "\n";
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

}
