package Guet.controller;

import Guet.dao.CourseMapper;
import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.util.ServerSqlSession;

import java.io.IOException;
import java.util.List;

public class CourseController {

    CourseMapper courseMapper;

    public CourseController() throws IOException {
        courseMapper = ServerSqlSession.openSqlSession().getMapper(CourseMapper.class);
    }

    public List<SelectedCourse> getSelectedCourseInfo(String stuID){
        return courseMapper.queryCourseByStuID(stuID);
    }

    public List<SelectedCourse> getNoSelectedCourse(String stuID, String courseCode){
        return courseMapper.queryNoSelectedCourse(stuID, courseCode);
    }

    public void stuSelectCourse(int courseID, String stuID){
        courseMapper.insertCourseToStuCourse(courseID, stuID);
    }

    public void dropCourse(int courseID, String stuID){
        courseMapper.dropCourse(courseID, stuID);
    }

    public List<CourseInfo> getCourseInfo(String stuID){
        return courseMapper.queryAllCourse(stuID);
    }

    public List<SelectedCourse> getStudentGrade(String stuID){
        return courseMapper.queryStuGrade(stuID);
    }

}
