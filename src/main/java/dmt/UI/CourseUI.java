package dmt.UI;

import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
import dmt.Data.DatabaseHandler;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CourseUI {
    public Parent getView() {
        CourseView courseView = new CourseView();
        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        ArrayList<Course> courses = databaseHandler.retrieveCourses();
        ScrollPane scrollPane = new ScrollPane();

        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);

        VBox vBox = new VBox();
        for (Course course : courses) {
            Button button = new Button();
            button.setText(course.getName());
            button.setStyle("-fx-background-color: blue; -fx-font-size: 12px; -fx-text-fill: white");
            vBox.getChildren().add(button);

            button.setOnAction((event) -> {
                layout.setCenter(courseView.getView(course));

            });

        }

        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background: #383838; -fx-border-color: red;");
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 20, 20, 7));
        return layout;
    }
}
