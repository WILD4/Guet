package Guet.dao;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {

    public String queryUserById(@Param("uid")String uid);

    public StudentInfo getStudentInfo(@Param("uid")String uid);

    public void updateLoginInfo();

    public TeacherInfo getTeacherInfo(@Param("uid")String uid);

}
