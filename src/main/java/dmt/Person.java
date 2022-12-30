package dmt;

import java.sql.Date;
import java.util.ArrayList;
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
    }

    public void getViewedWebcasts() {
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

                this.webcasts.add(new Webcast(id, publicationDate, url, title, description, speakerName, organization,
                        progress, watchTime));

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

        for (ContentItem webcast : webcasts) {
            System.out.println(webcast.getTitle());
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

                this.courses.add(new Course(id, name, subject, introduction, level));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Course course : courses) {
            System.out.println(course.getName());
        }

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
