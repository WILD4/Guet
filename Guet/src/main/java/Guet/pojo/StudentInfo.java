package Guet.pojo;

import java.sql.Date;

public class StudentInfo {

    String studentID;
    String studentName;
    String studentSex;
    Date studentBirthday;
    String password;

    public String getId() {
        return studentID;
    }

    public void setId(String id) {
        this.studentID = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] toStrArray(){
        return new String[]{studentID, studentName, studentSex, String.valueOf(studentBirthday)};
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentBirthday=" + studentBirthday +
                '}';
    }
}
