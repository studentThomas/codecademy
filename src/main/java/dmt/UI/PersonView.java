package dmt.UI;

import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Certificate;
import dmt.ContentItem;
import dmt.Course;
import dmt.Person;
import dmt.Data.DatabaseHandler;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class PersonView {
    public static Parent getView(Person person) {
        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        ArrayList<Course> courses = person.getEnrolledCourses();
        ArrayList<ContentItem> webcasts = person.getViewedWebcasts();
        ArrayList<Certificate> certificates = person.getCertificates();

        for (Certificate certificate : certificates) {
            System.out.println(certificate.getId());
        }

        HBox layout = new HBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setStyle("-fx-background-color: #f5fcff; ");
        layout.setSpacing(100);

        VBox personInfo = new VBox();
        personInfo.setSpacing(10);
        Label user = new Label(person.getName() + " (" + person.getGender() + ")");
        Label email = new Label(person.getEmail());
        email.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        Label country = new Label(person.getCountry());

        personInfo.getChildren().addAll(email, user, country);

        VBox personData = new VBox();
        personData.setSpacing(10);
        Label courseLabel = new Label("Course(s)" + " (" + courses.size() + ") ");
        courseLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        personData.getChildren().add(courseLabel);
        if (webcasts.size() > 0) {
            for (Course course : courses) {
                personData.getChildren().add(createCourse(course, person));
            }
        }
        Label webcastLabel = new Label("Webcast(s)" + " (" + webcasts.size() + ") ");
        webcastLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        personData.getChildren().add(webcastLabel);
        if (webcasts.size() > 0) {
            for (ContentItem webcast : webcasts) {
                personData.getChildren().add(createWebcast(webcast, person));
            }
        }

        Label certificateLabel = new Label("Certificate(s)" + " (" + certificates.size() + ") ");
        certificateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        personData.getChildren().add(certificateLabel);
        if (certificates.size() > 0) {
            for (Certificate certificate : certificates) {
                personData.getChildren().add(createCertificate(certificate, person));
                System.out.println(certificate.getTeachter());
            }
        }

        layout.getChildren().addAll(personInfo, personData);
        return layout;
    }

    private static BorderPane createCourse(Course course, Person person) {

        BorderPane borderPane = new BorderPane();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(800);
        rectangle.setHeight(100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.LIGHTGRAY);
        rectangle.setStrokeWidth(1);

        stackPane.getChildren().add(rectangle);

        VBox moduleInfo = new VBox();
        VBox progressInfo = new VBox();

        Label courseLabel = new Label("Course");
        courseLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        courseLabel.setTextFill(Color.web("#10162f"));
        Label title = new Label(course.getName());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        title.setTextFill(Color.web("#10162f"));

        ProgressBar progress = new ProgressBar();
        progress.setProgress(course.checkProgressCourse(person.getEmail()) / 100);
        progress.getTransforms().setAll(
                new Translate(80, 20),
                new Rotate(-90, 0, 0));
        Label progressText = new Label("Hallo");
        progressText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        progressText.textProperty().bind(Bindings.format("%.0f%%", progress.progressProperty().multiply(100)));
        progress.setStyle(
                "-fx-accent: #ffd302; -fx-background-color: #ffd302, linear-gradient(to bottom, derive(black,60%) 5%, derive(black,90%) 40%); -fx-background-insets: 0, 1;-fx-background-radius: 3px;");

        progressInfo.getChildren().addAll(progress, progressText);
        progressInfo.setSpacing(10);

        moduleInfo.getChildren().addAll(courseLabel, title);
        moduleInfo.setPadding(new Insets(20, 20, 20, 40));
        moduleInfo.setSpacing(10);

        stackPane.getChildren().addAll(moduleInfo, progress, progressText);
        StackPane.setAlignment(progress, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(progressText, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(Pos.CENTER);

        borderPane.setLeft(stackPane);

        return borderPane;
    }

    private static BorderPane createWebcast(ContentItem webcast, Person person) {

        BorderPane borderPane = new BorderPane();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(800);
        rectangle.setHeight(100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.LIGHTGRAY);
        rectangle.setStrokeWidth(1);

        stackPane.getChildren().add(rectangle);

        VBox moduleInfo = new VBox();
        VBox progressInfo = new VBox();

        Label webcastLabel = new Label("Webcast");
        webcastLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        webcastLabel.setTextFill(Color.web("#10162f"));
        Label title = new Label(webcast.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        title.setTextFill(Color.web("#10162f"));

        double progressLevel = (double) webcast.getProgress();
        ProgressBar progress = new ProgressBar();
        progress.setProgress(progressLevel / 100);
        progress.getTransforms().setAll(
                new Translate(80, 20),
                new Rotate(-90, 0, 0));
        Label progressText = new Label("Hallo");
        progressText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        progressText.textProperty().bind(Bindings.format("%.0f%%", progress.progressProperty().multiply(100)));
        progress.setStyle(
                "-fx-accent: #ffd302; -fx-background-color: #ffd302, linear-gradient(to bottom, derive(black,60%) 5%, derive(black,90%) 40%); -fx-background-insets: 0, 1;-fx-background-radius: 3px;");

        progressInfo.getChildren().addAll(progress, progressText);
        progressInfo.setSpacing(10);

        moduleInfo.getChildren().addAll(webcastLabel, title);
        moduleInfo.setPadding(new Insets(20, 20, 20, 40));
        moduleInfo.setSpacing(10);

        stackPane.getChildren().addAll(moduleInfo, progress, progressText);
        StackPane.setAlignment(progress, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(progressText, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(Pos.CENTER);

        borderPane.setLeft(stackPane);

        return borderPane;
    }

    private static BorderPane createCertificate(Certificate certificate, Person person) {

        BorderPane borderPane = new BorderPane();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(800);
        rectangle.setHeight(100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.LIGHTGRAY);
        rectangle.setStrokeWidth(1);

        stackPane.getChildren().add(rectangle);

        VBox moduleInfo = new VBox();

        Label webcastLabel = new Label("Certificate");
        webcastLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        webcastLabel.setTextFill(Color.web("#10162f"));
        Label title = new Label("Grade: " + certificate.getGrade());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        title.setTextFill(Color.web("#10162f"));

        moduleInfo.getChildren().addAll(webcastLabel, title);
        moduleInfo.setPadding(new Insets(20, 20, 20, 40));
        moduleInfo.setSpacing(10);

        stackPane.getChildren().addAll(moduleInfo);
        stackPane.setAlignment(Pos.CENTER);

        borderPane.setLeft(stackPane);

        return borderPane;
    }
}
