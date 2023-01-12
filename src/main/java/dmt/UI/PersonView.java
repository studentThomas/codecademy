package dmt.UI;

import java.util.ArrayList;

import dmt.Person;
import dmt.Data.DatabaseHandler;
import dmt.Data.PersonData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonView {
    public static Parent getView(Person person) {
        PersonData persondata = new PersonData();
        ArrayList<Person> Person = PersonData.getPersons();
        ScrollPane scrollPane = new ScrollPane();

        Label labelEmail = new Label("Email: " + person.getEmail()); labelEmail.setStyle("-fx-text-fill: white;");
        Label labelName = new Label("name: " + person.getName()); labelName.setStyle("-fx-text-fill: white;");
        Label labelGender = new Label("Gender: " + person.getGender()); labelGender.setStyle("-fx-text-fill: white;");
        Label labelAdress = new Label("Adress: " + person.getAddress()); labelAdress.setStyle("-fx-text-fill: white;");
        Label labelCity = new Label("City: " + person.getCity()); labelCity.setStyle("-fx-text-fill: white;");
        Label labelCountry = new Label("Country: " + person.getCountry()); labelCountry.setStyle("-fx-text-fill: white;");
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 20, 20, 20));
        layout.getChildren().addAll(labelEmail, labelName, labelGender, labelAdress, labelCity, labelCountry);
        layout.setStyle("-fx-background-color: #383838; -fx-border-color: red; ");
        return layout;
    }
}
