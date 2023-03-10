package dmt.UI;

import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.ContentItem;
import dmt.Course;
import dmt.Person;
import dmt.Data.CRUD;
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

public class CourseView {
    public Parent getView(Course course, boolean person, Person person2) {
        UpdateCourse updateCourse = new UpdateCourse();
        CRUD crud = new CRUD();
        ArrayList<ContentItem> contentItems = new ArrayList<>();
        DatabaseHandler databaseHandler = new DatabaseHandler(null);

        VBox layout = new VBox(20);

        if (person) {
            contentItems = databaseHandler.getModules(person2.getEmail(), course.getId());
        } else {
            contentItems = databaseHandler.retrieveCouresModules(course.getId());
        }

        int amount = databaseHandler.retrieveAmoutOfCertificatesPerCourse(course.getId());

        HBox hBox = new HBox();
        hBox.setSpacing(100);

        HBox buttons = new HBox();
        buttons.setSpacing(10);

        VBox courseInfo = new VBox();
        courseInfo.setSpacing(10);

        Button edit = new Button("Edit");
        edit.setStyle(
                "-fx-background-color: #4357b2; -fx-text-fill: white; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");

        Button delete = new Button("Delete");
        delete.setStyle(
                "-fx-background-color: #eaf0f4; -fx-text-fill: black; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");

        edit.setOnAction(event -> {
            layout.getChildren()
                    .setAll(updateCourse.getView(course.getName(), course.getSubject(), course.getIntroduction(),
                            course.getLevel()));
        });
        delete.setOnAction(event -> {
            crud.deleteCourse(course.getName());
            delete.setText("Deleted");

        });

        Label label2 = new Label(contentItems.size() + " Module(s)");
        Label label1 = new Label("Passed by " + amount + " Person(s)");
        Label label3 = new Label(course.getLevel());
        label1.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        label2.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        label3.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Line line1 = new Line();
        line1.setEndX(200);
        Line line2 = new Line();
        line2.setEndX(200);
        Line line3 = new Line();
        line3.setEndX(200);
        Line line4 = new Line();
        line4.setEndX(200);

        courseInfo.getChildren().addAll(label1, line1, label2, line2, label3, line3);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-border-color: #f5fcff;; -fx-background-color: #f5fcff;;");

        layout.setPadding(new Insets(20, 20, 20, 20));

        layout.setStyle("-fx-background-color: #f5fcff; ");

        Label name = new Label(course.getName());
        Label description = new Label(course.getIntroduction());
        description.setMaxWidth(500);
        description.setWrapText(true);
        Label modules = new Label("Modules");

        name.setFont(Font.font("Verdana", FontWeight.findByWeight(800), 30));
        name.setTextFill(Color.web("#10162f"));
        description.setFont(Font.font("Verdana", 15));
        description.setTextFill(Color.web("#10162f"));
        modules.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        modules.setTextFill(Color.web("#10162f"));

        modules.setPadding(new Insets(40, 20, 10, 0));

        vBox.setSpacing(10);

        for (ContentItem module : contentItems) {
            vBox.getChildren().add(createModule(module, person));

        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-border-color: #f5fcff;; -fx-background-color: #f5fcff;;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        hBox.getChildren().addAll(description, courseInfo);
        buttons.getChildren().addAll(edit, delete);
        layout.getChildren().addAll(name, buttons, hBox, modules, scrollPane);
        return layout;
    }

    private BorderPane createModule(ContentItem module, boolean person) {
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

        Label title = new Label(module.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#10162f"));
        Label description = new Label(module.getDescription());
        description.setFont(Font.font("Verdana", 15));
        description.setTextFill(Color.web("#10162f"));

        ProgressBar progress = new ProgressBar();
        if (person) {
            double progressd = (double) module.getProgress();
            progress.setProgress(progressd / 100);
        } else {
            progress.setProgress(module.getProgressModule() / 100);
        }

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

        moduleInfo.getChildren().addAll(title, description);
        moduleInfo.setPadding(new Insets(20, 20, 20, 40));
        moduleInfo.setSpacing(10);

        stackPane.getChildren().addAll(moduleInfo, progress, progressText);
        StackPane.setAlignment(progress, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(progressText, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(Pos.CENTER);

        borderPane.setLeft(stackPane);

        return borderPane;
    }

}
