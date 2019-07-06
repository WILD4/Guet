package Guet.controller;

import Guet.dao.ManagerMapper;
import Guet.dao.StudentMapper;
import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import Guet.util.ServerSqlSession;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.List;

public class AdminController {

    ManagerMapper managerMapper;

    public AdminController(){
        try {
            managerMapper = ServerSqlSession.openSqlSession().getMapper(ManagerMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<StudentInfo> getAllStuInfo(){
        return managerMapper.queryAllStu();
    }

    public List<StudentInfo> getAllTeaInfo(){
        return managerMapper.queryAllTea();
    }

    public void updateStuInfo(String uid, StudentInfo studentInfo){
        managerMapper.updateStuInfo(uid, studentInfo);
    }

    public void updateOrInsertStuInfo(List<StudentInfo> studentInfo){
        managerMapper.updateOrInsertStuInfo(studentInfo);
    }

    public void updateOrInsertTeaInfo(List<TeacherInfo> teacherInfos){
        managerMapper.updateOrInsertTeaInfo(teacherInfos);
    }

    public void removeStuInfo(String stuID){
        managerMapper.removeStuInfo(stuID);
    }
}
