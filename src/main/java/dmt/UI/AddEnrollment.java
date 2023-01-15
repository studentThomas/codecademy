package dmt.UI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
import dmt.Data.DatabaseConnectionManager;
import dmt.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddEnrollment {

    public static Parent getView() {
        ArrayList<Person> persons = PersonData.getPersons();
        ScrollPane scrollPane = new ScrollPane();
        ScrollPane scrollPane2 = new ScrollPane();
        BorderPane layout = new BorderPane();
      
        layout.setCenter(scrollPane);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 20, 20, 13));
        vBox.setSpacing(7);
        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(10, 20, 20, 13));
        
        ComboBox comboBox = new ComboBox();
        for (Person person : persons) {
            comboBox.getItems().add(person.getEmail());
        }

        Label EmailAsk = new Label("Enter Email:");
        TextField EmailField = new TextField();
        Label RegistrationDateAsk = new Label("Enter Registration date");
        TextField RegistrationDateField = new TextField();
        Label IdAsk = new Label("Enter Id:");
        TextField IdField = new TextField();
        Label CertificateIdAsk = new Label("Enter CertificateId:");
        TextField CertificateIdField = new TextField();
        Button startButton = new Button("Insert Data");
        vBox.getChildren().addAll(EmailAsk, comboBox, IdAsk, IdField, RegistrationDateAsk, RegistrationDateField, CertificateIdAsk, CertificateIdField, startButton);

        // collect inserted data
        startButton.setOnAction((event) -> {
            Object email = comboBox.getValue();
            String registrationDate = RegistrationDateField.getText();
            String id = IdField.getText();
            String certificateid = CertificateIdField.getText();

            try {
                Connection connection = DatabaseConnectionManager.getInstance().getConnection();
                PreparedStatement query = connection.prepareStatement(
                        "INSERT INTO CourseEnrolled (Email, RegistrationDate, Id, CertificateId) VALUES (?, ?, ?, ?)");

                // Explained: Sets the values for the insert query
                query.setObject(1, email);
                query.setString(2, registrationDate);
                query.setString(3, id);
                query.setString(4, certificateid);
                int rowsAffected = query.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // second layout
            BorderPane layout2 = new BorderPane();
            Button BackAfterAdd = new Button("go back");
            ;
            Label confirmation = new Label(
                    "entered into database: Email = " + email + ", RegistrationDate = " + registrationDate + ", Id = "
                            + id + ", CertificateId = " + certificateid);
            vBox2.getChildren().addAll(confirmation, BackAfterAdd);
            BackAfterAdd.setOnAction((event2) -> {
                layout.setCenter(AddEnrollment.getView());
            });
            scrollPane.setContent(vBox2);
            layout2.setCenter(scrollPane2);
            scrollPane2.setStyle("-fx-background: white; -fx-border-color: gray;"); 
        });

        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: gray;");

        return layout;
    }
}
