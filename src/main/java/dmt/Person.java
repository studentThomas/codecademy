package dmt;

import java.sql.Date;
import java.util.ArrayList;

import dmt.Data.DatabaseConnectionManager;
import dmt.Data.DatabaseHandler;

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
    private ArrayList<ContentItem> modules;
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
        this.modules = new ArrayList<>();
        this.certificates = new ArrayList<>();
        this.databaseHandler = new DatabaseHandler(email);
    }

    public Person(String email2, String name2, Date doB, String gender2, String adress, String country2, String city2,
            Object object) {
    }

    public ArrayList<ContentItem> getViewedWebcasts() {
        this.webcasts = databaseHandler.retrieveViewedWebcasts();

        return this.webcasts;
    }

    public ArrayList<Course> getEnrolledCourses() {
        this.courses = databaseHandler.retrieveEnrolledCourses();
        if (this.webcasts.isEmpty()) {
            return null;
        }
        return this.courses;
    }

    // public ArrayList<ContentItem> getModules() {
    // for (Course course : courses) {
    // System.out.println(course.getModules(this.email) + "\n");
    // this.modules = course.getModules(this.email);
    // }
    // }

    // public void getProgress() {
    // for (Course course : courses) {
    // System.out.println("Progress cursus: " +
    // course.checkProgressCourse(this.email));
    // }
    // }

    public void getCertificate() {
        this.certificates = databaseHandler.retrieveCertificate();
        for (Certificate certificate : certificates) {
            System.out.println(certificate);
        }
    }

    public void insertCertificate() {
        databaseHandler.insertCertificate();
    }

    public void updateCertificate() {
        databaseHandler.updateCertificate(0, email, address, dateOfBirth);
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
