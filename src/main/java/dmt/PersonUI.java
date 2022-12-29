package dmt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PersonUI extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){
        Label labelEmail = new Label("Enter your email:");
        TextField inputEmail = new TextField();

        Label labelName = new Label("Enter your name:");
        TextField inputName = new TextField();

        Label labelDate = new Label("Select a date:");
        DatePicker datePicker = new DatePicker();

        Label labelGender = new Label("Enter your gender:");
        TextField inputGender = new TextField();

        Label labelCity = new Label("Enter your city:");
        TextField inputCity = new TextField();

        Label labelCountry = new Label("Enter your country:");
        TextField inputCountry = new TextField();

        Button confirmButton = new Button("Confirm");

        Label errorLabel = new Label("Not enough information filled in!");

        confirmButton.setOnAction(event -> {

            String email = inputEmail.getText();
            String name = inputName.getText();
            Date date = localDateToDate(datePicker.getValue());
            String gender = inputGender.getText();
            String city = inputCity.getText();
            String country = inputCountry.getText();

            if(email.isEmpty() || name.isEmpty() || (date == null) || gender.isEmpty() || city.isEmpty() || country.isEmpty()){

                errorLabel.setVisible(true);
            }else{

                errorLabel.setVisible(false);

//Hier moet code komen die van de data een nieuw record maakt

            }
        });

        VBox root = new VBox(labelEmail, inputEmail, labelName, inputName, labelDate, datePicker, labelGender, inputGender, labelCity, inputCity, labelCountry, inputCountry, confirmButton);
        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("Create new user");
        stage.setScene(scene);
        stage.show();
    }

    public Date localDateToDate(LocalDate localDate){
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
