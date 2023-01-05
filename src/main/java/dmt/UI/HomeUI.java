package dmt.UI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HomeUI extends Application {

    public void start(Stage Stage) {
        Button PersonButton = new Button("Person");
        Button CourseButton = new Button("Course");
        Button AddButton = new Button("Add");

        //create subviews
        PersonUI personUI = new PersonUI();
        CourseUI courseUI = new CourseUI();

        //create main layout
        BorderPane layout = new BorderPane();

        //create Layout
        HBox componentGroup = new HBox();
        componentGroup.setSpacing(20);
        componentGroup.getChildren().addAll(PersonButton, CourseButton, AddButton);

        layout.setTop(componentGroup);


        Scene scene = new Scene(componentGroup, 300, 250);
        Stage.setScene(scene);
        Stage.show();
    }
}
