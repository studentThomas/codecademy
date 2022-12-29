package dmt;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        // Stage stage = new Stage();
        // Create a module
        // getPersons();
        // addPerson();

        Person person = new Person("Barry@gmail.com", null, null, null, null, null, null);
        System.out.println("Webcasts");
        person.getViewedWebcasts();
        System.out.println("Courses");
        person.getEnrolledCourses();

        Application.launch(HomeUI.class, args);
        // Application.launch(PersonUI.class, args);

    }

    public static void getCourse() {
        // TODO: Get all courses in course. When clicked show info. When no one follows
        // that course display that

    }

    public static void addCourse() {

    }

    public static void ShowCourse() {

    }

}