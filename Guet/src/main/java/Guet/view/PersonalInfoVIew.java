package Guet.view;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import Guet.pojo.UserInfo;
import Guet.util.StudentManager;
import Guet.util.TeacherManager;
import Guet.util.UserManager;
import Guet.view.LoginView.USER_STATUS;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoVIew extends JPanel {

    JTable jTable;
    String[] title;
    String[][] content;

    public PersonalInfoVIew(USER_STATUS userStatus) {
        init(userStatus);
    }

    private void init(USER_STATUS userStatus){

        switch (userStatus){
            case STUDENT:
                StudentInfo student = StudentManager.getStudent();
                title = new String[]{"",""};
                content = new String[][]{
                        {"学号：",student.getStudentId()},
                        {"姓名：",student.getStudentName()},
                        {"班级：",student.getStudentId().substring(0,8)},
                        {"年级：","20"+student.getStudentId().substring(0,2)},
                        {"性别：",student.getStudentSex()},
                        {"生日：",student.getStudentBirthday().toString()}
                };
                break;
            case TEACHER:
                UserInfo userManager = UserManager.getUserInfo();
                title = new String[]{"",""};
                content = new String[][]{
                        {"学号：",teacherInfo.getTeacherID()},
                        {"姓名：",teacherInfo.getTeacherName()},
                        {"性别：",teacherInfo.getTeacherSex()},
                        {"生日：",teacherInfo.getTeacherBirthday().toString()},
                        {"职称：",teacherInfo.getTeacherTitle()}
                };
                break;
        }

        jTable = new JTable(content, title);
        jTable.setEnabled(false);
        jTable.setRowHeight(28);
        jTable.setCellSelectionEnabled(false);
        jTable.setShowHorizontalLines(false);
        jTable.setShowVerticalLines(false);
        jTable.setBackground(this.getBackground());
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));
        this.add(jTable, BorderLayout.WEST);

    }




    public JPanel getPIView(){
        return this;
    }

}
