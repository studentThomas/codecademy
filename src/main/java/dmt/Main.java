package dmt;

import java.util.ArrayList;
import java.sql.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Create a module

        getPersons();
        // Person person = new Person(null, null, null, null, null, null, null);
        // person.getEnrolledCourses();

    }

    public static void getPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        System.out.println("Persons");

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Person");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                String email = result.getString("Email");
                String name = result.getString("Name");
                Date dateOfBirth = result.getDate("Dob");
                String gender = result.getString("Gender");
                String address = result.getString("Adress");
                String city = result.getString("City");
                String country = result.getString("Country");
                Person person = new Person(email, name, dateOfBirth, gender, address, city, country);
                persons.add(person);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showPerson(persons);

    }

    // public static void addPerson() {

    // try {
    // Connection connection =
    // DatabaseConnectionManager.getInstance().getConnection();
    // PreparedStatement query = connection.prepareStatement("INSERT INTO Person
    // ()");
    // ResultSet result = query.executeQuery();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public static void showPerson(ArrayList<Person> persons) {
        for (Person person : persons) {
            System.out.println(person.toString());

            // TODO: Javax button. When clicked it goes to a different page and can see
            // person profile
            // person.getEnrolledCourses();

        }
    }

    public static void getCourse() {
        // TODO: Get all courses in course. When clicked show info. When no one follows
        // that course display that

    }

    public static void addCourse() {

    }

}