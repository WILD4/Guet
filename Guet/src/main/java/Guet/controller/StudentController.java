package Guet.controller;

import Guet.dao.StudentMapper;
import Guet.pojo.StudentInfo;
import Guet.util.ServerSqlSession;
import Guet.util.StudentManager;

import java.io.IOException;
import java.util.List;

public class StudentController {

    StudentMapper studentMapper;

    public StudentController() throws IOException {
        studentMapper = ServerSqlSession.openSqlSession().getMapper(StudentMapper.class);
    }

    public List<StudentInfo> getStudentInfo(){
        return studentMapper.queryAllStu();
    }

}
