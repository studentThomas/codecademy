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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPerson {
    
    public static Parent getView() {
        ScrollPane scrollPane = new ScrollPane();
        ScrollPane scrollPane2 = new ScrollPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 20, 20, 13));
        vBox.setSpacing(7);
        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(10, 20, 20, 13));

        Label EmailAsk = new Label("Enter Email:");
        TextField EmailField = new TextField();
        Label NameAsk = new Label("Enter name:");
        TextField NameField = new TextField();
        Label DoBAsk = new Label("Enter date of birth:");
        TextField DoBField = new TextField();
        Label GenderAsk = new Label("Enter gender:");
        TextField GenderField = new TextField();
        Label AdressAsk = new Label("Enter adress");
        TextField AdressField = new TextField();
        Label CountryAsk = new Label("Enter country");
        TextField CountryField = new TextField();
        Label CityAsk = new Label("Enter city");
        TextField CityField = new TextField();
        Button startButton = new Button("Insert Data");
        vBox.getChildren().addAll(EmailAsk, EmailField, NameAsk, NameField, DoBAsk, DoBField, GenderAsk, GenderField, AdressAsk, AdressField, CountryAsk, CountryField, CityAsk, CityField, startButton);

        //collect inserted data
        startButton.setOnAction((event) -> {
            String email = EmailField.getText();
            String name = NameField.getText();
            String DoB = DoBField.getText();
            String gender = GenderField.getText();
            String adress = AdressField.getText();
            String country = CountryField.getText();
            String city = CityField.getText();
            try {
                Connection connection = DatabaseConnectionManager.getInstance().getConnection();
                PreparedStatement query = connection.prepareStatement(
                        "INSERT INTO Person (Email, Name, DoB, Gender, Adress, Country, City) VALUES (?, ?, ?, ?, ?, ?, ?)");
    
                // Explained: Sets the values for the insert query
                query.setString(1, email);
                query.setString(2, name);
                query.setString(3, DoB);
                query.setString(4, gender);
                query.setString(5, adress);
                query.setString(6, country);
                query.setString(7, city);
                int rowsAffected = query.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //second layout
            BorderPane layout2 = new BorderPane();
            Button BackAfterAdd = new Button("go back");;
            Label confirmation = new Label("entered into database: Email = " + email + ", Name = " + name + ", DoB = " + DoB + ", Gender = " + gender + ", Adress= " + adress + ", Country = " + country + ", City = " + city);
            vBox2.getChildren().addAll(confirmation, BackAfterAdd);
            BackAfterAdd.setOnAction((event2) -> {
                layout.setCenter(AddPerson.getView());
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
