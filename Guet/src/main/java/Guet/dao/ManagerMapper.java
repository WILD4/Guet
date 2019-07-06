package Guet.dao;

import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {

    public void removeStuInfo(@Param("uid")String uid);

    public void removeTeaInfo(@Param("uid")String uid);

    public void removeCourseInfo(@Param("cid")String cid);

    public List<StudentInfo> queryAllStu();

    public List<StudentInfo> queryAllTea();

    public List<SelectedCourse> queryAllCourse();

    public void updateStuInfo(@Param("uid")String uid, @Param("studentInfo")StudentInfo studentInfo);

    public void updateOrInsertStuInfo(@Param("stuInfoList")List<StudentInfo> list);

    public void updateOrInsertTeaInfo(@Param("teaInfoList")List<TeacherInfo> list);

    public void updateOrInsertCourseInfo(@Param("couInfoList")List<CourseInfo> list);

}
