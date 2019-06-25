package Guet.util;

import Guet.pojo.CourseInfo;

public class CourseManager {

    private static CourseInfo courseInfo;

    public CourseManager(){
    }

    public static CourseInfo getCourseInfo() {
        if(courseInfo == null)
            courseInfo = new CourseInfo();
        return courseInfo;
    }

    public static void setCourseInfo(CourseInfo courseInfo) {
        CourseManager.courseInfo = courseInfo;
    }
}
