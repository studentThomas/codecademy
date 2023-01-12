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

        Label labelEmail = new Label("id: " + person.getEmail());
        Label labelName = new Label("name: " + person.getName());
        Label labelGender = new Label("subject: " + person.getGender());
        Label labelAdress = new Label("introduction: " + person.getAddress());
        Label labelCity = new Label("level: " + person.getCity());
        Label labelCountry = new Label("level: " + person.getCountry());
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 20, 20, 20));
        layout.getChildren().addAll(labelEmail, labelName, labelGender, labelAdress, labelCity, labelCountry);
        return layout;
    }
}
