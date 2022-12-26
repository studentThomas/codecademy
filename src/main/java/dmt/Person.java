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
    }

    public void getEnrolledCourses() {
        // TODO: get all enrolled coursed and add them to arraylist

        // TODO: do this with join statemtn. Join with id.

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM CourseEnrollment WHERE Email = ?");
            query.setString(1, this.email);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                String email = result.getString("Email");
                int courseID = result.getInt("CourseID");
                int certificateID = result.getInt("CertificateID");
                System.out.println(email + "   " + courseID + certificateID + "\n");
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
