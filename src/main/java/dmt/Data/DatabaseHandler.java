package dmt.Data;

import java.sql.Date;
import java.util.ArrayList;

import dmt.ContentItem;
import dmt.Course;
import dmt.Module;
import dmt.Person;
import dmt.Webcast;
import dmt.Certificate;
import dmt.Data.DatabaseHandler;

import java.sql.*;

public class DatabaseHandler {
    private String email;
    private ArrayList<Course> courses;
    private ArrayList<ContentItem> webcasts;
    private ArrayList<ContentItem> modules;
    private ArrayList<Certificate> certificates;
    private ArrayList<Person> persons;

    public DatabaseHandler(String email) {
        this.email = email;
        this.courses = new ArrayList<>();
        this.webcasts = new ArrayList<>();
        this.certificates = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.persons = new ArrayList<>();

    }

    public ArrayList<ContentItem> retrieveViewedWebcasts() {
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

        return webcasts;
    }

    public ArrayList<Course> retrieveEnrolledCourses() {
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

        return courses;

    }

    public ArrayList<Course> retrieveCourses() {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Course");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String name = result.getString("Name");
                String subject = result.getString("Subject");
                String introduction = result.getString("Introduction");
                String level = result.getString("Level");

                this.courses.add(new Course(id, name, subject, introduction, level, null));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Certificate> retrieveCertificate() {
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
                updateCertificate(id, personEmai, courseId, registrationDate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.certificates;

    }

    public boolean checkCertificateNotExists(String personEmail, Integer courseId, Date registrationDate) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Certificate WHERE PersonEmail = ? AND CourseId = ? AND RegistrationDate = ?");
            query.setString(1, personEmail);
            query.setInt(2, courseId);
            query.setDate(3, registrationDate);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                return false;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void insertCertificate() {
        for (Course course : courses) {
            if (course.checkCertificate()
                    && checkCertificateNotExists(this.email, course.getId(), course.getRegistrationDate())) {
                System.out.println("Krijgt Certificate voor: " + course.getId());
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
                System.out.println("Onvoldoende progress of certifcate bestaat al");

            }

        }
        retrieveCertificate();
    }

    public void updateCertificate(int certificateId, String email, String id, Date registrationDate) {
        System.out.println("Update");
        System.out.println(certificateId + " " + email + " " + id + " " + registrationDate);
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "UPDATE CourseEnrolled SET CertificateId = ? WHERE Email = ? AND Id = ? AND RegistrationDate = ?");
            query.setInt(1, certificateId);
            query.setString(2, email);
            query.setString(3, id);
            query.setDate(4, registrationDate);
            query.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ContentItem> getModules(int serial) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Module JOIN ModuleViewed ON [Module].Id = ModuleViewed.Id WHERE ModuleViewed.Email = ? AND Module.SerialNumber = ?");
            query.setString(1, this.email);
            query.setInt(2, serial);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String status = result.getString("Status");
                int serialNumber = result.getInt("SerialNumber");
                String title = result.getString("Title");
                int version = result.getInt("Version");
                int progress = result.getInt("Progress");
                String description = result.getString("Description");
                String contactName = result.getString("ContactName");
                String contactEmail = result.getString("ContactEmail");
                Date publicationDate = result.getDate("PublicationDate");

                this.modules.add(new Module(id, publicationDate, status, title, description, version,
                        contactName, contactEmail, progress, serialNumber));

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

        return this.modules;
    }

    public double retrieveCertificateStatistics(String gender) {
        int total = 0;
        int achieved = 0;
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT COUNT(*) FROM CourseEnrolled JOIN Person ON CourseEnrolled.Email = Person.Email WHERE CourseEnrolled.CertificateId IS NOT NULL AND Person.Gender = ?");
            query.setString(1, gender);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                achieved = result.getInt(1);
            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT COUNT(*) FROM CourseEnrolled JOIN Person ON CourseEnrolled.Email = Person.Email WHERE Person.Gender = ?");
            query.setString(1, gender);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                total = result.getInt(1);
            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }

        return (double) achieved / total * 100;

    }

    public String retrieveTopViewedWebcasts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Most watched webcasts: ");
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT TOP 3 WebcastViewed.Id, Webcast.Title, COUNT(WebcastViewed.Id) as count FROM WebcastViewed JOIN Webcast ON Webcast.Id = WebcastViewed.Id GROUP BY WebcastViewed.Id, Webcast.Title ORDER BY count DESC");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                String title = result.getString("Title");
                stringBuilder.append(title);
                stringBuilder.append(", ");

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public int retrieveAmoutOfCertificatesPerCourse(int id) {
        int amount = 0;
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT COUNT(Id) FROM CourseEnrolled WHERE CertificateId IS NOT NULL AND Id = ? GROUP BY Id");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                amount = result.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;

    }

    public String retrieveTopCertificates() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Most succeeded cursus: ");
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT TOP 3 CourseEnrolled.Id, Course.Name, COUNT(CourseEnrolled.Id) as count FROM CourseEnrolled JOIN Course ON Course.Id = CourseEnrolled.Id  WHERE CourseEnrolled.CertificateId IS NOT NULL GROUP BY CourseEnrolled.Id, Course.Name  ORDER BY count DESC");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                String course = result.getString("Name");
                stringBuilder.append(course);
                stringBuilder.append(", ");

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public ArrayList<ContentItem> retrieveProgressModule(int moduleId) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM Module JOIN ModuleViewed ON ModuleViewed.Id = ?");
            query.setInt(1, moduleId);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                String status = result.getString("Status");
                int serialNumber = result.getInt("SerialNumber");
                String title = result.getString("Title");
                int version = result.getInt("Version");
                int progress = result.getInt("Progress");
                String description = result.getString("Description");
                String contactName = result.getString("ContactName");
                String contactEmail = result.getString("ContactEmail");
                Date publicationDate = result.getDate("PublicationDate");

                this.modules.add(new Module(id, publicationDate, status, title, description, version,
                        contactName, contactEmail, progress, serialNumber));
            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return this.modules;
    }

    public ArrayList<ContentItem> retrieveCouresModules(int courseId) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "SELECT * FROM [Module] WHERE SerialNumber = ?");
            query.setInt(1, courseId);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int id = result.getInt("Id");
                this.modules.add(new ContentItem(id, null, null, null, email, id));
            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return this.modules;
    }

}
