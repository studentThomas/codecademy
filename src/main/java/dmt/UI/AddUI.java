package dmt.UI;


import dmt.Course;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddUI {
    public static Parent getView() {

        
        AddCourse addCourse = new AddCourse();
        AddEnrollment addEnrollment = new AddEnrollment();
        
        ScrollPane scrollPane = new ScrollPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);

        VBox vBox = new VBox();

        //add each button
            Button AddPersonButton = new Button("Add a Person");;
            vBox.getChildren().add(AddPersonButton);
            AddPersonButton.setStyle("-fx-background-color: blue; -fx-font-size: 12px; -fx-text-fill: white");
            Button AddCourseButton = new Button("Add a course");;
            vBox.getChildren().add(AddCourseButton);
            AddCourseButton.setStyle("-fx-background-color: blue; -fx-font-size: 12px; -fx-text-fill: white");
            Button AddEnrollmentButton = new Button("Add a course enrollment");;
            vBox.getChildren().add(AddEnrollmentButton);
            AddEnrollmentButton.setStyle("-fx-background-color: blue; -fx-font-size: 12px; -fx-text-fill: white");
            Button AddCertificateButton = new Button("Add a certificate");;
            vBox.getChildren().add(AddCertificateButton);
            AddCertificateButton.setStyle("-fx-background-color: blue; -fx-font-size: 12px; -fx-text-fill: white");
            
            AddPersonButton.setOnAction((event) -> {
                layout.setCenter(AddPerson.getView());
            });
            AddCourseButton.setOnAction((event) -> {
                layout.setCenter(AddCourse.getView());
            });
             AddEnrollmentButton.setOnAction((event) -> {
                 layout.setCenter(AddEnrollment.getView());
             });
            // AddCertificateButton.setOnAction((event) -> {
            //     layout.setCenter(AddCourse.getView());
            // });

        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background: #383838; -fx-border-color: red;");
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 20, 20, 7));
        return layout;
    }
}

