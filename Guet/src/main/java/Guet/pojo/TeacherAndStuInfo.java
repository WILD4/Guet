package Guet.pojo;

import java.sql.Date;

public class TeacherAndStuInfo {

    private String studentID;
    private String studentName;
    private int courseID;
    private CourseInfo courseInfo;
    private String grade;
    private Date semester;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getSemester() {
        return semester;
    }

    public void setSemester(Date semester) {
        this.semester = semester;
    }

    public String[] toArray(){
        return new String[]{
                studentID,
                studentName,
                String.valueOf(courseInfo.getCourseCredit()),
                grade,
        };
    }
}
