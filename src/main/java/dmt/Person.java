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
    private ArrayList<Webcast> webcasts;

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
                    "SELECT Webcast.Title, Webcast.Id, WebcastViewed.Id, WebcastViewed.Email FROM Webcast JOIN WebcastViewed ON Webcast.Id = WebcastViewed.Id WHERE WebcastViewed.Email = ?");
            query.setString(1, this.email);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                String email = result.getString("Email");
                String title = result.getString("Title");
                int id = result.getInt("Id");
                System.out.println(email + "   " + id + "  " + title + "\n");
                this.courses.add(new Course(title, title, email, title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getEnrolledCourses() {
        // TODO: get all enrolled coursed and add them to arraylist

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT Course.CourseId, CourseEnrollment.CourseId, Course.CourseLevel, CourseEnrollment.Email FROM Course JOIN CourseEnrollment ON Course.CourseId = CourseEnrollment.CourseId WHERE CourseEnrollment.Email = ?");
            query.setString(1, this.email);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("CourseId");
                String email = result.getString("Email");
                String level = result.getString("CourseLevel");
                System.out.println(id + "  " + level + "   " + email + "\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
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
