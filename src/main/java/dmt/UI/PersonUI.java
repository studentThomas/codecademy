
package dmt.UI;

import java.util.ArrayList;

import dmt.Person;
import dmt.Data.PersonData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonUI {

    public Parent getView() {
        ArrayList<Person> persons = PersonData.getPersons();
        ScrollPane scrollPane = new ScrollPane();
        PersonView personView = new PersonView();

        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);

        VBox vBox = new VBox();
        for (Person person : persons) {
            Button button = new Button();
            button.setText(person.getName());
            button.setStyle("-fx-background-color: #bdbdbb; -fx-font-size: 12px; -fx-text-fill: black;");
            vBox.getChildren().add(button);

            button.setOnAction((event) -> {
                layout.setCenter(personView.getView(person));

            });

        }
        vBox.setSpacing(10);
        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: gray;");
        vBox.setPadding(new Insets(10, 20, 20, 7));
        return layout;
    }
}