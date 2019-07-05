package Guet.view;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import Guet.pojo.UserInfo;
import Guet.util.StudentManager;
import Guet.util.UserManager;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoVIew extends JPanel {

    private JTable jTable;
    private String[] title;
    private String[][] content;

    public PersonalInfoVIew() {
        init();
    }

    private void init(){

        UserInfo userInfo = UserManager.getUserInfo();
        if(userInfo.getStatus().equals("学生")){
            StudentInfo studentInfo = (StudentInfo)userInfo;
            title = new String[]{"",""};
            content = new String[][]{
                    {"学号：",studentInfo.getStudentId()},
                    {"姓名：",studentInfo.getStudentName()},
                    {"班级：",studentInfo.getStudentId().substring(0,8)},
                    {"年级：","20"+studentInfo.getStudentId().substring(0,2)},
                    {"性别：",studentInfo.getStudentSex()},
                    {"生日：",studentInfo.getStudentBirthday().toString()}
            };
        }
        if(userInfo.getStatus().equals("教师")){
            TeacherInfo teacherInfo = (TeacherInfo)userInfo;
            title = new String[]{"",""};
            content = new String[][]{
                    {"学号：",teacherInfo.getTeacherID()},
                    {"姓名：",teacherInfo.getTeacherName()},
                    {"性别：",teacherInfo.getTeacherSex()},
                    {"生日：",teacherInfo.getTeacherBirthday().toString()},
                    {"职称：",teacherInfo.getTeacherTitle()}
            };
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
