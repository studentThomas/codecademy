package dmt.UI;

import java.util.ArrayList;

import dmt.Person;
import dmt.Data.PersonData;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class PersonUI extends Application {

    public void start(Stage stage) {
        ArrayList<Person> persons = PersonData.getPersons();
        ScrollPane scrollPane = new ScrollPane();

        VBox vBox = new VBox();
        for (Person person : persons) {
            Button button = new Button();
            button.setText(person.getName());
            vBox.getChildren().add(button);

            button.setOnAction((event) -> {
                Stage personWindow = new Stage();
                personWindow.setTitle(person.getName());
                personWindow.show();
                stage.close();
            });

        }
        scrollPane.setContent(vBox);

        Scene scene = new Scene(scrollPane, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}