package Guet.dao;

import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.pojo.TeacherAndStuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    //通过学号查找学生已选课程
    List<SelectedCourse> queryCourseByStuID(@Param("stuID")String stuID, @Param("semester")String semester);
    //通过工号查找老师授课内容
    List<SelectedCourse> queryCourseByTeaID(@Param("teaID")String teaID, @Param("semester")String semester);

    //通过学号查找学生未选的课程
    List<SelectedCourse> queryNoSelectedCourses(@Param("stuID")String stuID, @Param("courseCode")String courseCode);

    //没有被学生选修的课程
    List<CourseInfo> queryNoStuSelectCourse(@Param("termYear")String termYear);

    //学生选课
    void insertCourseToStuCourse(@Param("courseID")int courseID, @Param("stuID")String stuID);

    //学生退课
    void dropCourse(@Param("courseID")int courseID, @Param("stuID")String stuID);
    //查询学生所有可选课程
    List<CourseInfo> queryAllCourseByStuID(String stuID);
    //查询成绩
    List<SelectedCourse> queryStuGrade(@Param("stuID")String stuID, @Param("semester")String semester);
    //查询所有成绩
    List<SelectedCourse> queryStuAllGrade(@Param("stuID")String stuID);
    //查询老师的学生信息
    List<TeacherAndStuInfo> queryTeasStuInfo(@Param("teaID")String teaID, @Param("courseID")String courseID);

    void updateStuGrade(@Param("courseID")String courseID, @Param("stuID")String stuID, @Param("grade")String grade);

    //获取所有基础课程
    List<CourseInfo> queryAllCourse();
}
