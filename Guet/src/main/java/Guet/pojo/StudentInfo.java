package Guet.pojo;

import Guet.util.UserManager;

import java.sql.Date;

public class StudentInfo extends UserInfo {

    public String getStudentId() {
        return getUID();
    }

    public void setStudentId(String id) {
        setUID(id);
    }

    public String getStudentName() {
        return getUserName();
    }

    public void setStudentName(String studentName) {
        setUserName(studentName);
    }

    public String getStudentSex() {
        return getUserSex();
    }

    public void setStudentSex(String studentSex) {
        setUserSex(studentSex);
    }

    public Date getStudentBirthday() {
        return getUserBirthday();
    }

    public void setStudentBirthday(Date studentBirthday) {
        setUserBirthday(studentBirthday);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public String getStatus() {
        return super.getStatus();
    }

    public void setStatus(String status) {
        super.setStatus(status);
    }

//    public String[] toStrArray(){
//        return new String[]{studentID, studentName, studentSex, String.valueOf(studentBirthday)};
//    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                super.toString()+
                '}';
    }
}
