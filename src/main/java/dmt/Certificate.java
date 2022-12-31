package dmt;

import java.sql.Date;

public class Certificate {
    private int id;
    private String personEmai;
    private String courseId;
    private Date registrationDate;

    public Certificate(int id, String personEmail, String courseId, Date registrationDate) {
        this.id = id;
        this.personEmai = personEmail;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
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

    public Date registrationDate() {
        return this.registrationDate;
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
