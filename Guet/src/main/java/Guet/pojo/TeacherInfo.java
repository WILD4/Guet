package Guet.pojo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class TeacherInfo extends UserInfo {

//    private String teacherID;
//    private String teacherName;
//    private String teacherSex;
//    private Date teacherBirthday;
    private String teacherTitle;
    private List<String> courseIDs;

    public String getTeacherID() {
        return getUID();
    }

    public void setTeacherID(String teacherID) {
        this.setUID(teacherID);
    }

    public String getTeacherName() {
        return getUserName();
    }

    public void setTeacherName(String teacherName) {
        this.setUserName(teacherName);
    }

    public String getTeacherSex() {
        return this.getUserSex();
    }

    public void setTeacherSex(String teacherSex) {
        setUserSex(teacherSex);
    }

    public Date getTeacherBirthday() {
        return getUserBirthday();
    }

    public void setTeacherBirthday(Date teacherBirthday) {
        setUserBirthday(teacherBirthday);
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String[] getCourseIDs() {
        String[] arr = new String[courseIDs.size()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = courseIDs.get(i);
        return arr;
    }

    public void setCourseIDs(List<String> courseIDs) {
        this.courseIDs = courseIDs;
    }

    public String[] toArray(){
        return new String[]{"",getUID(), getUserName(), getUserSex(), String.valueOf(getUserBirthday()), getBirthPlace(), teacherTitle};
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                super.toString() + '\'' +
                "teacherTitle='" + teacherTitle + '\'' +
                ", courseIDs=" + courseIDs +
                '}';
    }
}
