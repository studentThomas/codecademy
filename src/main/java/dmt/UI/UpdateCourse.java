package dmt.UI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
import dmt.Data.CRUD;
import dmt.Data.DatabaseConnectionManager;
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

public class UpdateCourse {

    public Parent getView(String name, String subject, String introduction, String level) {

        CRUD crud = new CRUD();
        VBox vBox = new VBox();
        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);

        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(7);
        Label affected = new Label();

        Label NameAsk = new Label("Enter name:");
        TextField NameField = new TextField();
        NameField.setText(name);
        Label SubjectAsk = new Label("Enter subject:");
        TextField SubjectField = new TextField();
        SubjectField.setText(subject);

        Label IntroductionAsk = new Label("Enter introduction:");
        TextField IntroductionField = new TextField();
        IntroductionField.setText(introduction);
        Label LevelAsk = new Label("Enter Level");
        TextField LevelField = new TextField();
        LevelField.setText(level);

        Button update = new Button("Update");
        update.setStyle(
                "-fx-background-color: #eaf0f4; -fx-text-fill: #3a11e5; -fx-border-radius: 12px; -fx-pref-width: 120px; -fx-pref-height: 35px; -fx-font-size: 15px; -fx-font-weight: bold;");
        vBox.getChildren().addAll(NameAsk, NameField, SubjectAsk, SubjectField, IntroductionAsk, IntroductionField,
                LevelAsk, LevelField, update, affected);

        update.setOnAction((event) -> {
            String Name = NameField.getText();
            String Subject = SubjectField.getText();
            String Introduction = IntroductionField.getText();
            String Level = LevelField.getText();

            int rowsAffected = crud.updateCourse(Name, Subject, Introduction, Level, name);
            affected.setText("Rows affected " + String.valueOf(rowsAffected));
            update.setText("Updated");
        });

        vBox.setStyle("-fx-background: white; -fx-border-color: gray;");
        vBox.setMinWidth(1160);

        return vBox;
    }
}
