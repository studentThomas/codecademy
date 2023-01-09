package dmt.UI;

import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
import dmt.Data.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseView {
    public Parent getView(Course course) {
        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        ArrayList<Course> courses = databaseHandler.retrieveCourses();
        ScrollPane scrollPane = new ScrollPane();

        Label labelId = new Label("id: " + course.getId());
        Label labelName = new Label("name: " + course.getName());
        Label labelSubject = new Label("subject: " + course.getSubject());
        Label labelIntroduction = new Label("introduction: " + course.getIntroduction());
        Label labelLevel = new Label("level: " + course.getLevel());
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 20, 20, 20));
        layout.getChildren().addAll(labelId, labelName, labelSubject, labelIntroduction, labelLevel);
        return layout;
    }
}
