package Guet.dao;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {

    public String queryUserById(@Param("uid")String uid);

    public StudentInfo getStudentInfo(@Param("uid")String uid);

    public void updateLoginInfo(@Param("uid")String uid);

    public void changePassword(@Param("uid")String uid, @Param("password")String password);

    public TeacherInfo getTeacherInfo(@Param("uid")String uid);

    public String selectPassword(@Param("uid")String uid);

    public List<String> getTeaCourseID(@Param("uid")String uid);

}
