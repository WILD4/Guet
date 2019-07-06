package Guet.pojo;

public class CourseInfo{

    private String courseCode;
    private String courseName;
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
                null,
                courseCode,
                courseName,
                String.valueOf(courseCredit),
                courseType,
                courseDate,
        };
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                ", courseType='" + courseType + '\'' +
                ", courseDate='" + courseDate + '\'' +
                '}';
    }
}
