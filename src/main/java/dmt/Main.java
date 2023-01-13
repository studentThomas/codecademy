package dmt;

import java.util.ArrayList;

import dmt.Data.DatabaseHandler;
import dmt.UI.HomeUI;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        // Stage stage = new Stage();
        // Create a module
        // getPersons();
        // addPerson();

        // Person person = new Person("Barry@gmail.com", "Thomas", null, null, null,
        // null,
        // null);
        // System.out.println("\n" + "Webcasts");
        // person.getViewedWebcasts();
        // System.out.println("\n" + "Courses");
        // person.getEnrolledCourses();

        // System.out.println("\n" + "Modules");
        // person.getModules();

        // person.insertCertificate();
        // person.getProgress();
        // Course course = new Course(3, null, null, null, null, null);
        // course.checkProgressModule();
        // DatabaseHandler databaseHandler = new DatabaseHandler(null);
        // ArrayList<ContentItem> modules = databaseHandler.retrieveCouresModules(3);
        // for (ContentItem module : modules) {
        // System.out.println(course.checkProgressModule(module.getId()));

        // }

        // Course course = new Course(3, null, null, null, null, null);
        // DatabaseHandler databaseHandler = new DatabaseHandler(null);
        // databaseHandler.retrieveCouresModules(3);

        // course.checkProgressModule();

        // System.out.println(databaseHandler.retrieveCertificateStatistics("M"));
        // System.out.println(databaseHandler.retrieveTopViewedWebcasts());
        // System.out.println(databaseHandler.retrieveAmoutOfCertificatesPerCourse(3));
        // System.out.println("Modules");

        Application.launch(HomeUI.class, args);
        // Application.launch(PersonUI.class, args);

    }

}