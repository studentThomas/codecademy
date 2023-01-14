package dmt;

import java.sql.Date;

public class Certificate {
    private int id;
    private String personEmai;
    private String courseId;
    private Date registrationDate;
    private int grade;
    private String teachter;

    public Certificate(int id, String personEmail, String courseId, Date registrationDate, int grade, String teacher) {
        this.id = id;
        this.personEmai = personEmail;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
        this.grade = grade;
        this.teachter = teacher;
    }

    public int getId() {
        return this.id;
    }

    public String getPersonEmai() {
        return this.personEmai;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public int getGrade() {
        return this.grade;
    }

    public String getTeachter() {
        return this.teachter;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", personEmai='" + personEmai + "'" +
                ", courseId='" + courseId + "'" +
                ", registrationDate='" + registrationDate + "'" +
                "}";
    }

}
