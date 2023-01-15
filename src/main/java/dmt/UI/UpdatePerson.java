package dmt.UI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
import dmt.Data.CRUD;
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

public class UpdatePerson {

    public Parent getView(String email, String name, Date date, String gender, String adress, String country,
            String city) {

        CRUD crud = new CRUD();
        PersonView personView = new PersonView();
        VBox vBox = new VBox();
        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);

        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(7);
        Label affected = new Label();

        Label EmailAsk = new Label("Enter Email:");
        TextField EmailField = new TextField();
        EmailField.setText(email);
        Label NameAsk = new Label("Enter name:");
        TextField NameField = new TextField();
        NameField.setText(name);
        Label DoBAsk = new Label("Enter date of birth:");
        TextField DoBField = new TextField();
        DoBField.setText(date.toString());
        Label GenderAsk = new Label("Enter gender:");
        TextField GenderField = new TextField();
        GenderField.setText(gender);
        Label AdressAsk = new Label("Enter adress");
        TextField AdressField = new TextField();
        AdressField.setText(adress);
        Label CountryAsk = new Label("Enter country");
        TextField CountryField = new TextField();
        CountryField.setText(country);
        Label CityAsk = new Label("Enter city");
        TextField CityField = new TextField();
        CityField.setText(city);
        Button update = new Button("Update");
        update.setStyle(
                "-fx-background-color: #eaf0f4; -fx-text-fill: #3a11e5; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");
        vBox.getChildren().addAll(EmailAsk, EmailField, NameAsk, NameField, DoBAsk, DoBField, GenderAsk, GenderField,
                AdressAsk, AdressField, CountryAsk, CountryField, CityAsk, CityField, update, affected);

        update.setOnAction((event) -> {
            String Email = EmailField.getText();
            String Name = NameField.getText();
            String DoB = DoBField.getText();
            String Gender = GenderField.getText();
            String Adress = AdressField.getText();
            String Country = CountryField.getText();
            String City = CityField.getText();
            int rowsAffected = crud.updatePerson(Email, Name, DoB, Gender, Adress, Country, City);
            affected.setText("Rows affected " + String.valueOf(rowsAffected));
        });

        vBox.setStyle("-fx-background: white; -fx-border-color: gray;");
        vBox.setMinWidth(1160);

        return vBox;
    }
}
