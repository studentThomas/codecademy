package dmt;

import java.sql.Date;
import java.util.ArrayList;

public class Course {
    private String name;
    private String subject;
    private String introduction;
    private String level;
    private Date registrationDate;
    private Person person;
    private ArrayList<Module> modules;

    public Course(String name, String subject, String introduction, String level) {
        this.name = name;
        this.subject = subject;
        this.introduction = introduction;
        this.level = level;
        this.modules = new ArrayList<Module>();
    }

    // TODO: Add certicaat

    // TODO: get data from database en print that

    // TODO: print all modules in course they have the same ID

}
