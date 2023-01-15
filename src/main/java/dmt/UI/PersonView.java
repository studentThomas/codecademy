package dmt.UI;

import java.beans.EventHandler;
import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Certificate;
import dmt.ContentItem;
import dmt.Course;
import dmt.Person;
import dmt.Data.CRUD;
import dmt.Data.DatabaseHandler;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    HBox layout = new HBox();

    public Parent getView(Person person) {
        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        CRUD crud = new CRUD();
        PersonUI personUI = new PersonUI();
        ArrayList<Course> courses = person.getEnrolledCourses();
        ArrayList<ContentItem> webcasts = person.getViewedWebcasts();
        ArrayList<Certificate> certificates = person.getCertificates();
        CourseView courseView = new CourseView();
        UpdatePerson updatePerson = new UpdatePerson();

        // HBox layout = new HBox();
        layout.setPadding(new Insets(20, 20, 20, 20));

        layout.setSpacing(100);

        HBox buttons = new HBox();
        buttons.setSpacing(10);

        Button edit = new Button("Edit");
        edit.setStyle(
                "-fx-background-color: #4357b2; -fx-text-fill: white; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");

        Button delete = new Button("Delete");
        delete.setStyle(
                "-fx-background-color: #eaf0f4; -fx-text-fill: #4357b2; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");

        buttons.getChildren().addAll(edit, delete);
        edit.setOnAction(event -> {
            layout.getChildren()
                    .setAll(updatePerson.getView(person.getEmail(), person.getName(), person.getDateOfBirth(),
                            person.getGender(), person.getAddress(), person.getCountry(), person.getCity()));
        });
        delete.setOnAction(event -> {
            crud.deletePerson(person.getEmail());
            delete.setText("Deleted");

        });
        VBox personInfo = new VBox();

        personInfo.setSpacing(10);
        Label user = new Label(person.getName() + " (" + person.getGender() + ")");
        user.setStyle("-fx-text-fill: #303545;");
        Label email = new Label(person.getEmail());
        email.setStyle("-fx-text-fill: #303545;");
        email.setWrapText(true);
        email.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        Label country = new Label(person.getCountry());
        country.setStyle("-fx-text-fill: #303545;");

        personInfo.getChildren().addAll(email, buttons, user, country);

        VBox personData = new VBox();
        layout.setMinWidth(1200);
        personData.setPadding(new Insets(20, 40, 20, 0));
        personData.setSpacing(20);
        Label courseLabel = new Label("Course(s)" + " (" + courses.size() + ") ");
        courseLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        courseLabel.setStyle("-fx-text-fill: #303545;");
        personData.getChildren().add(courseLabel);
        if (courses.size() > 0) {
            for (Course course : courses) {
                personData.getChildren().add(createCourse(course, person));

            }
        }
        Label webcastLabel = new Label("Webcast(s)" + " (" + webcasts.size() + ") ");
        webcastLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        webcastLabel.setStyle("-fx-text-fill: #303545;");
        personData.getChildren().add(webcastLabel);
        if (webcasts.size() > 0) {
            for (ContentItem webcast : webcasts) {
                personData.getChildren().add(createWebcast(webcast, person));
            }
        }

        Label certificateLabel = new Label("Certificate(s)" + " (" + certificates.size() + ") ");
        certificateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        certificateLabel.setStyle("-fx-text-fill: #303545;");
        personData.getChildren().add(certificateLabel);
        if (certificates.size() > 0) {
            for (Certificate certificate : certificates) {
                personData.getChildren().add(createCertificate(certificate, person));
            }
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(personData);
        scrollPane.setStyle("-fx-border-color: #f5fcff; -fx-background-color: #f5fcff;");
        layout.setStyle("-fx-border-color: #f5fcff; -fx-background-color: #f5fcff;");
        personData.setStyle("-fx-border-color: #f5fcff; -fx-background-color: #f5fcff;");

        buttons.setStyle("-fx-border-color: #f5fcff; -fx-background-color: #f5fcff;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        layout.getChildren().addAll(personInfo, scrollPane);

        return layout;
    }

    private BorderPane createCourse(Course course, Person person) {
        CourseView courseView = new CourseView();
        BorderPane borderPane = new BorderPane();
        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(800);
        rectangle.setHeight(100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.LIGHTGRAY);
        rectangle.setStrokeWidth(1);

        Button button = new Button("Course ->");
        button.setStyle(
                "-fx-background-color: #4357b2; -fx-text-fill: white; -fx-border-radius: 12px; -fx-pref-width: 80px; -fx-pref-height: 25px; -fx-font-size: 12px; -fx-font-weight: bold;");
        button.setOnAction(event -> {
            layout.getChildren().setAll(courseView.getView(course, true, person));

        });

        button.setPadding(new Insets(0));

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
        progressText.setTextFill(Color.web("#10162f"));
        progressText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        progressText.textProperty().bind(Bindings.format("%.0f%%", progress.progressProperty().multiply(100)));
        progressText.setPadding(new Insets(0, 20, 0, 0));
        progress.setStyle(
                "-fx-accent: #4357b2; -fx-background-color: #4357b2, linear-gradient(to bottom, derive(black,60%) 5%, derive(black,90%) 40%); -fx-background-insets: 0, 1;-fx-background-radius: 3px;");

        progressInfo.getChildren().addAll(progress, progressText);
        progressInfo.setSpacing(10);
        progressInfo.setPadding(new Insets(0, 20, 0, 0));

        moduleInfo.getChildren().addAll(button, title);
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
        progressText.setTextFill(Color.web("#10162f"));
        progressText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        progressText.textProperty().bind(Bindings.format("%.0f%%", progress.progressProperty().multiply(100)));
        progress.setStyle(
                "-fx-accent: #4357b2; -fx-background-color: #4357b2, linear-gradient(to bottom, derive(black,60%) 5%, derive(black,90%) 40%); -fx-background-insets: 0, 1;-fx-background-radius: 3px;");

        progressInfo.getChildren().addAll(progress, progressText);
        progressInfo.setSpacing(10);
        progressText.setPadding(new Insets(0, 20, 0, 0));

        moduleInfo.getChildren().addAll(webcastLabel, title);
        moduleInfo.setPadding(new Insets(20, 30, 20, 30));
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
