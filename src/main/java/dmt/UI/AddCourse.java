package dmt.UI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dmt.Data.PersonData;
import dmt.Course;
import dmt.Person;
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

public class AddCourse {
    
    public static Parent getView() {
        ScrollPane scrollPane = new ScrollPane();
        ScrollPane scrollPane2 = new ScrollPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);
        
        VBox vBox = new VBox();
        VBox vBox2 = new VBox();

        Label IdAsk = new Label("Enter Id:");
        TextField IdField = new TextField();
        Label NameAsk = new Label("Enter your Name:");
        TextField NameField = new TextField();
        Label SubjectAsk = new Label("Enter the subject:");
        TextField SubjectField = new TextField();
        Label IntroductionAsk = new Label("Enter the introduction:");
        TextField IntroductionField = new TextField();
        Label LevelAsk = new Label("Enter the difficulty level(Beginner, Advanced, Expert):");
        TextField LevelField = new TextField();
        Button startButton = new Button("Insert Data");
        vBox.getChildren().addAll(IdAsk, IdField, NameAsk, NameField, SubjectAsk, SubjectField, IntroductionAsk, IntroductionField, LevelAsk, LevelField, startButton);

        //collect inserted data
        startButton.setOnAction((event) -> {
            String id = IdField.getText();
            String name = NameField.getText();
            String subject = SubjectField.getText();
            String introduction = IntroductionField.getText();
            String level = LevelField.getText();
            
            try {
                Connection connection = DatabaseConnectionManager.getInstance().getConnection();
                PreparedStatement query = connection.prepareStatement(
                        "INSERT INTO Course (Id, Name, Subject, Introduction, Level) VALUES (?, ?, ?, ?, ?)");
    
                // Explained: Sets the values for the insert query
                query.setString(1, id);
                query.setString(2, name);
                query.setString(3, subject);
                query.setString(4, introduction);
                query.setString(5, level);
                int rowsAffected = query.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //second layout
            BorderPane layout2 = new BorderPane();
            Button BackAfterAdd = new Button("go back");;
            Label confirmation = new Label("entered into database: Id = " + id + " Name = " + name + " Subject = " + subject + "level = " + level + "introduction = " + introduction);
            vBox2.getChildren().addAll(confirmation, BackAfterAdd);
            BackAfterAdd.setOnAction((event2) -> {
                layout.setCenter(AddCourse.getView());
            });
            scrollPane.setContent(vBox2);
            layout2.setCenter(scrollPane2);
        }); 

        
        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background: #383838; -fx-border-color: red;"); 

        return layout;
    }
}
