package Guet.controller;

import Guet.dao.CourseMapper;
import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.pojo.TeacherAndStuInfo;
import Guet.util.ServerSqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseController {

    CourseMapper courseMapper;

    public CourseController() throws IOException {
        courseMapper = ServerSqlSession.openSqlSession().getMapper(CourseMapper.class);
    }

    public List<SelectedCourse> getSelectedCourseInfo(String UID, String semester){
        if(UID.length() == 10)
            return courseMapper.queryCourseByStuID(UID, semester);
        else
            return courseMapper.queryCourseByTeaID(UID, semester);
    }

    public List<SelectedCourse> getNoSelectedCourse(String stuID, String courseCode){
        return courseMapper.queryNoSelectedCourses(stuID, courseCode);
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

    public List<SelectedCourse> getStudentGrade(String stuID, String semester){
        if(semester.equals("all"))
            return courseMapper.queryStuAllGrade(stuID);
        return courseMapper.queryStuGrade(stuID, semester);
    }

    public List<TeacherAndStuInfo> getTeasStuInfo(String teaID, String courseID){
        return courseMapper.queryTeasStuInfo(teaID, courseID);
    }

    public void updateStuGrade(String courseID, String stuID, String grade){
        courseMapper.updateStuGrade(courseID, stuID, grade);
    }

}
