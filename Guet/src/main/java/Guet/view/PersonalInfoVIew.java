package Guet.view;

import Guet.pojo.StudentInfo;
import Guet.util.StudentManager;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoVIew extends JPanel {

    JTable jTable;
    String[] title;
    String[][] content;

    StudentInfo student;

    public PersonalInfoVIew() {
        init();
    }

    private void init(){
        student = StudentManager.getStudent();
        title = new String[]{"",""};
        content = new String[][]{
                {"学号：",student.getId()},
                {"姓名：",student.getStudentName()},
                {"班级：",student.getId().substring(0,8)},
                {"年级：","20"+student.getId().substring(0,2)},
                {"性别：",student.getStudentSex()},
                {"生日：",student.getStudentBirthday().toString()}
        };
        jTable = new JTable(content, title);
        jTable.setEnabled(false);
        jTable.setRowHeight(28);
        jTable.setCellSelectionEnabled(false);
//        jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable.setShowHorizontalLines(false);
        jTable.setShowVerticalLines(false);
        jTable.setBackground(this.getBackground());
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));
        this.add(jTable, BorderLayout.WEST);

//        jTable.getModel().isCellEditable(1,6);
    }




    public JPanel getPIView(){
        return this;
    }

}
