package dmt.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseData {


    // TODO: Input field for data
    public static void addPerson() {

        // try {
        //     Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        //     PreparedStatement query = connection.prepareStatement(
        //             "INSERT INTO Course (Id, Name, Subject, Introduction, Level) VALUES (?, ?, ?, ?, ?)");

        //     // Explained: Sets the values for the insert query
        //     query.setString(1, "5");
        //     query.setString(2, "Optimizing Code part 2");
        //     query.setString(3, "Java Optimizing");
        //     query.setString(4, "test");
        //     query.setString(5, "Expert");
        //     int rowsAffected = query.executeUpdate();

        //     if (rowsAffected > 0) {
        //         System.out.println("Data inserted successfully");
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

    }
}
