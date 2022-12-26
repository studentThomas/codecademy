package dmt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HomeUI extends Application {
    public static void main(String[] args) {
        System.out.println("Hallo");
        launch(args);
    }

    public void start(Stage stage) {
        ArrayList<Person> persons = PersonData.getPersons();
        ScrollPane scrollPane = new ScrollPane();

        VBox vBox = new VBox();
        // for (int i = 0; i < 40; i++) {
        // vBox.getChildren().add(new Button("Button " + i));
        // }
        for (Person person : persons) {
            Button button = new Button();
            button.setText(person.getName());
            vBox.getChildren().add(button);

            button.setOnAction((event) -> {
                System.out.println("Clicked");
            });

        }

        scrollPane.setContent(vBox);

        Scene scene = new Scene(scrollPane, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}
