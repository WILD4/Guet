package Guet.dao;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {

    public void removeStuInfo(@Param("uid")String uid);

    public void removeTeaInfo(@Param("uid")String uid);

    public List<StudentInfo> queryAllStu();

    public List<StudentInfo> queryAllTea();

    public void updateStuInfo(@Param("uid")String uid, @Param("studentInfo")StudentInfo studentInfo);

    public void updateOrInsertStuInfo(@Param("stuInfoList")List<StudentInfo> list);

    public void updateOrInsertTeaInfo(@Param("teaInfoList")List<TeacherInfo> list);

}
