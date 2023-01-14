package dmt.UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import dmt.Course;
import dmt.Person;

public class HomeUI extends Application {

    public void start(Stage stage) throws Exception {
        // 2. Create the views ("subviews")
        PersonUI personUI = new PersonUI();
        CourseUI courseUI = new CourseUI();

        // 3. Create the higher level layout
        BorderPane layout = new BorderPane();

        // 3.1. Create the menu for the general layout
        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);

        // 3.2. Create the menu buttons
        Button enterButton = new Button("Persons");
        Button practiceButton = new Button("Courses");
        Button addButton = new Button("Add new data");

        // add styling to the menu buttons
        enterButton.setStyle("-fx-background-color: white; -fx-font-size: 12px; -fx-text-fill: black;");
        practiceButton.setStyle("-fx-background-color: white; -fx-font-size: 12px; -fx-text-fill: black;");
        addButton.setStyle("-fx-background-color: white; -fx-font-size: 12px; -fx-text-fill: black;");
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.BLUE);
        enterButton.setEffect(shadow);
        practiceButton.setEffect(shadow);
        addButton.setEffect(shadow);

        // 3.3. Add the buttons to the menu
        menu.getChildren().addAll(enterButton, practiceButton, addButton);
        layout.setTop(menu);

        // 4. Connect the subviews with the buttons. Clicking menu buttons changes the
        // subview.
        enterButton.setOnAction((event) -> layout.setCenter(personUI.getView()));
        practiceButton.setOnAction((event) -> layout.setCenter(courseUI.getView()));
        addButton.setOnAction((event) -> layout.setCenter(AddUI.getView()));

        // 5. First show the input view
        layout.setCenter(personUI.getView());

        // 6. Create the main view and add the high level layout
        Scene view = new Scene(layout, 1200, 750);
        layout.setStyle("-fx-background-color: red");

        // 7. Show the application

        stage.setTitle("CodeCademy");
        Image image = new Image("dmt/UI/codecademy.png");
        stage.getIcons().add(image);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch(HomeUI.class);
    }

}
