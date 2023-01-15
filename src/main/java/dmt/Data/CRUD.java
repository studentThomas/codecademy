package dmt.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUD {

    public int updatePerson(String email, String name, String date, String gender, String adress, String country,
            String city) {
        int rowsAffected = 0;
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement(
                    "UPDATE Person SET Email = ?, Name = ?, DoB = ?, Gender = ?, Adress = ?, Country = ?, City = ? WHERE Email = ?");

            query.setString(1, email);
            query.setString(2, name);
            query.setString(3, date);
            query.setString(4, gender);
            query.setString(5, adress);
            query.setString(6, country);
            query.setString(7, city);
            query.setString(8, email);
            rowsAffected = query.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public void deletePerson(String email) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement query = connection.prepareStatement("DELETE FROM Person WHERE Email = ?");
            query.setString(1, email);
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
