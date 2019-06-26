package Guet.pojo;

import Guet.view.CenterView.ViewType;

import java.sql.Date;

public class SelectedCourse {

    private int courseID;
    private String studentID;
    private String teacherID;
    private String courseTime;    //上课时间
    private String classroom;
    private CourseInfo courseInfo;
    private TeacherInfo teacherInfo;
    private float grade;
    private Date semester;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TeacherInfo teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String[] toStrArray(ViewType typeView){
        String[] content = null;
        switch (typeView){
            case NO_SELECT_COURSE:
                StringBuilder time = new StringBuilder();
                for(int i = 0; i < courseTime.length(); i++){
                    if(courseTime.charAt(i) == '/')
                        time.append(convertToDate(i));
                }
                content = new String[]{
                    String.valueOf(courseID),
                    courseInfo.getCourseName(),
                    teacherInfo.getTeacherName(),
//                    courseInfo.getCourseType(),
                    String.valueOf(courseInfo.getCourseCredit()),
                    courseInfo.getCourseDate(),
                    time.toString(),
            };
            break;
            case SELECTED_COURSE:
                content = new String[]{
                    String.valueOf(courseID),
                    courseInfo.getCourseCode(),
                    courseInfo.getCourseName(),
                    teacherInfo.getTeacherName(),
                    courseInfo.getCourseType(),
                    String.valueOf(courseInfo.getCourseCredit())
            };
            break;
            case QUERY_GRADE:
                content = new String[]{
                        courseInfo.getCourseCode(),
                        String.valueOf(courseID),
                        courseInfo.getCourseName(),
                        String.valueOf(grade),
                        String.valueOf(courseInfo.getCourseCredit()),
                        courseInfo.getCourseType(),
                };
        }
        return content;
    }

    private String convertToDate(int tag){
        return ("周"+courseTime.charAt(tag+1)+"第"+courseTime.substring(tag + 2,tag + 5)+"节 ");
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "courseID=" + courseID +
                ", studentID='" + studentID + '\'' +
                ", teacherID='" + teacherID + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", classroom='" + classroom + '\'' +
                ", courseInfo=" + courseInfo +
                ", teacherInfo=" + teacherInfo +
                ", grade=" + grade +
                ", semester=" + semester +
                '}';
    }
}
