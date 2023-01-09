package dmt.UI;

import java.util.ArrayList;

import dmt.Person;
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

// public class PersonView {
//     public Parent getView(Person person) {
//         DatabaseHandler databaseHandler = new DatabaseHandler(null);
//         ArrayList<Person> Pers = databaseHandler.retrieveCourses();
//         ScrollPane scrollPane = new ScrollPane();

//         Label labelEmail = new Label("id: " + person.getEmail());
//         // Label labelName = new Label("name: " + person.getName());
//         // Label labelSubject = new Label("subject: " + person.getSubject());
//         // Label labelIntroduction = new Label("introduction: " + person.getIntroduction());
//         // Label labelLevel = new Label("level: " + person.getLevel());
//         VBox layout = new VBox(20);
//         layout.setPadding(new Insets(10, 20, 20, 20));
//         layout.getChildren().addAll(labelEmail, labelName, labelSubject, labelIntroduction, labelLevel);
//         return layout;
//     }
// }
