package Guet.controller;

import Guet.dao.CourseMapper;
import Guet.dao.LoginMapper;
import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import Guet.util.ServerSqlSession;
import Guet.view.LoginView;

import java.io.IOException;

public class LoginController {

    LoginMapper loginMapper;

    public LoginController() throws IOException {
        loginMapper = ServerSqlSession.openSqlSession().getMapper(LoginMapper.class);
    }

    public void changePassword(String uid, String password){
        loginMapper.changePassword(uid, password);
    }

    public String getUserPassword(String uid){
        return loginMapper.selectPassword(uid);
    }

    public StudentInfo getStudentInfo(String uid){
        loginMapper.updateLoginInfo(uid);
        return loginMapper.getStudentInfo(uid);
    }

    public TeacherInfo getTeacherInfo(String uid){
        loginMapper.updateLoginInfo(uid);
        return loginMapper.getTeacherInfo(uid);
    }
}
