package Guet.dao;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {

    public String queryUserById(@Param("uid")String uid);

    public StudentInfo getStudentInfo(@Param("uid")String uid);

    public void updateLoginInfo();


    public void changePassword(@Param("uid")String uid, @Param("password")String password);

    public TeacherInfo getTeacherInfo(@Param("uid")String uid);

    public String selectPassword(@Param("uid")String uid);

}
