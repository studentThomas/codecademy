package dmt.UI;

import dmt.Person;
import dmt.Data.PersonData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonView {

    public Parent getView() {
        ScrollPane scrollPane = new ScrollPane();

        VBox vBox = new VBox();

        scrollPane.setContent(vBox);
        return scrollPane;
    }
}
