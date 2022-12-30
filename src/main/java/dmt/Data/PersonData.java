package dmt.Data;

import java.sql.*;
import java.util.ArrayList;

import dmt.DatabaseConnectionManager;
import dmt.Person;

public class PersonData {

    public static ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>();

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Person");
            ResultSet result = query.executeQuery();

            // Explained: Gets data from all tables in database en creates new Person and
            // puts that in Arraylist
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
        return persons;

    }

    // TODO: Input field for data
    public static void addPerson() {

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO Person (Email, Name, DoB, Gender, Adress, Country, City) VALUES (?, ?, ?, ?, ?, ?, ?)");

            // Explained: Sets the values for the insert query
            query.setString(1, "Zevi@example.com");
            query.setString(2, "Z Smith");
            query.setDate(3, Date.valueOf("1980-01-01"));
            query.setString(4, "male");
            query.setString(5, "123 Z Street.");
            query.setString(6, "Nederlands");
            query.setString(7, "Ablasserdam");
            int rowsAffected = query.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
