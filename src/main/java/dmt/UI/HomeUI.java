package dmt.UI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HomeUI extends Application {

    public void start(Stage Stage) {
        Button PersonButton = new Button("Person");
        TextField rightText = new TextField();
        Button CourseButton = new Button("Course");

        // this is how it will connect i think
        // PersonButton.setOnAction((event) -> {
        // window.setScene(PersonUI);
        // });
        // CourseButton.setOnAction((event) -> {
        // window.setScene(CourseUI);
        // });
        HBox componentGroup = new HBox();
        componentGroup.setSpacing(20);
        componentGroup.getChildren().addAll(PersonButton, CourseButton);

        Scene scene = new Scene(componentGroup);

        Stage.setScene(scene);
        Stage.show();
    }
}
