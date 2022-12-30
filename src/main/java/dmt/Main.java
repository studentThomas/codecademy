package dmt;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        // Stage stage = new Stage();
        // Create a module
        // getPersons();
        // addPerson();

        Person person = new Person("EchteMark@gmail.com", null, null, null, null, null,
                null);
        // System.out.println("\n" + "Webcasts");
        // person.getViewedWebcasts();
        System.out.println("\n" + "Courses");
        person.getEnrolledCourses();

        System.out.println("\n" + "Modules");
        person.getModules();

        person.getCertificate();

        // System.out.println("Modules");
        // Course course = new Course(1, null, null, null, null);
        // course.getModules();

        // Application.launch(HomeUI.class, args);
        // Application.launch(PersonUI.class, args);

    }

}