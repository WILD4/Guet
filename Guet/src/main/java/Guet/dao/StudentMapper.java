package Guet.dao;

import Guet.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StudentMapper {

    public StudentInfo queryStuById(@Param("id") String id);

    public List<StudentInfo> queryAllStu();

}
