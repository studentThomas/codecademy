package dmt;

import java.util.ArrayList;
import java.sql.*;

public class Codecademy {
    public static void main(String[] args) {
        // Create a module
        addPersons();

    }

    public static void addPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        System.out.println("Persons");

        String url = "jdbc:sqlserver://aei-sql2.avans.nl:1443", user = "GeoGSSR", password = "Error404";
        try (Connection db = DriverManager.getConnection(url, user, password)) {
            PreparedStatement query = db.prepareStatement("SELECT * FROM Person");
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

        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }

}