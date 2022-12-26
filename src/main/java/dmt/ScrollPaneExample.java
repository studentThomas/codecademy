package dmt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollPaneExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();

        VBox vBox = new VBox();
        for (int i = 0; i < 40; i++) {
            vBox.getChildren().add(new Button("Button " + i));
        }

        scrollPane.setContent(vBox);

        Scene scene = new Scene(scrollPane, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}
