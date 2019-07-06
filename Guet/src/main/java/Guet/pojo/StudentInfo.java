package Guet.pojo;

import Guet.util.UserManager;

import java.sql.Date;

public class StudentInfo extends UserInfo {

    public StudentInfo() {
    }

    public StudentInfo(String UID, String userName, String userSex, Date userBirthday, String password, String birthPlace) {
        super(UID, userName, userSex, userBirthday, password, null, birthPlace);
    }

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

    public String[] toArray(){
        return new String[]{getUID(), getUserName(), getUserSex(), String.valueOf(getUserBirthday()), getBirthPlace()};
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                super.toString()+
                '}';
    }
}
