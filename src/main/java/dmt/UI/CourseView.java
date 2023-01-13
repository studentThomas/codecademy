package dmt.UI;

import java.util.ArrayList;

import dmt.Data.PersonData;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class CourseView {
    public Parent getView(Course course) {

        DatabaseHandler databaseHandler = new DatabaseHandler(null);
        ArrayList<ContentItem> modules = databaseHandler.retrieveCouresModules(course.getId());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 20, 20));

        layout.setStyle("-fx-background-color: #f5fcff; ");

        Label name = new Label(course.getName());
        Label description = new Label(course.getIntroduction());
        layout.getChildren().addAll(name, description);

        for (ContentItem module : modules) {
            layout.getChildren().add(createModule(module));

        }

        return layout;
    }

    private BorderPane createModule(ContentItem module) {

        BorderPane borderPane = new BorderPane();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(600);
        rectangle.setHeight(100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.LIGHTGRAY);
        rectangle.setStrokeWidth(1);

        stackPane.getChildren().add(rectangle);

        VBox moduleInfo = new VBox();
        VBox progressInfo = new VBox();

        Label title = new Label(module.getTitle());
        Label descreption = new Label(module.getDescription());

        ProgressBar progress = new ProgressBar();
        progress.setProgress(module.getProgressModule() / 100);
        progress.getTransforms().setAll(
                new Translate(80, 20),
                new Rotate(-90, 0, 0));
        Label progressText = new Label("Hallo");
        progressText.textProperty().bind(Bindings.format("%.0f%%", progress.progressProperty().multiply(100)));
        progress.setStyle("-fx-accent: #ffd302; -fx-border-color: transparent;");

        progressInfo.getChildren().addAll(progress, progressText);
        progressInfo.setSpacing(10);

        moduleInfo.getChildren().addAll(title, descreption);
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
