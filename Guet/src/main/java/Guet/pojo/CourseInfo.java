package Guet.pojo;

public class CourseInfo{

    private String courseCode;
    private String courseName;
    private int courseClass;
    private float courseCredit;
    private String courseType;
    private String courseDate;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(int courseClass) {
        this.courseClass = courseClass;
    }

    public float getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(float courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String[] toArray(){
        return new String[]{
                courseCode,
                courseName,
                String.valueOf(courseClass),
                String.valueOf(courseCredit),
                courseDate,
                courseType,
        };
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseClass=" + courseClass +
                ", courseCredit=" + courseCredit +
                ", courseType='" + courseType + '\'' +
                ", courseDate='" + courseDate + '\'' +
                '}';
    }
}
