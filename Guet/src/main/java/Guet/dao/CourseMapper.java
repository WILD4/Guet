package Guet.dao;

import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    //通过学号查找学生已选课程
    List<SelectedCourse> queryCourseByStuID(@Param("stuID")String stuID, @Param("semester")String semester);

    //通过学号查找学生未选的课程
    List<SelectedCourse> queryNoSelectedCourse(@Param("stuID")String stuID, @Param("courseCode")String courseCode);

    //学生选课
    void insertCourseToStuCourse(@Param("courseID")int courseID, @Param("stuID")String stuID);

    void dropCourse(@Param("courseID")int courseID, @Param("stuID")String stuID);

    List<CourseInfo> queryAllCourse(String stuID);

    List<SelectedCourse> queryStuGrade(@Param("stuID")String stuID, @Param("semester")String semester);

}
