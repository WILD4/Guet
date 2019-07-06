package Guet.view;

import Guet.pojo.UserInfo;
import Guet.util.*;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Calendar;

public class CenterView extends JFrame {

    private JFrame jFrame;
    private JPanel funcViw;
    private JPanel userStatusJP;
    private JLabel userType;
    private JLabel userID;
    private JButton logout;
    private JButton modPassword;

    private NavBarView navBarView;

    CenterView(){
        userViewInit();
        init();
        addActionListener();
    }

    public enum ViewType{
        COURSE_INFO,
        PERSONAL_INFO,
        SELECTED_COURSE,
        SELECT_COURSE,
        DROP_COURSE,
        NO_SELECT_COURSE,
        QUERY_GRADE,
        QUERY_GRADE_POINT,
        TEACHER_INFO,
        TEACHER_COURSE,
        TEACHERS_STU_INFO,
        STUDENT_MANAGER,
        TEACHER_MANAGER,
        COURSE_MANAGER,
        NO_STU_SELECTE_COURSE,
        UPDATE_STU_GRADE
    }

    private void init() {
        this.add(navBarView, BorderLayout.WEST);
        this.add(userStatusJP, BorderLayout.SOUTH);

        this.setSize(AppConfig.width, AppConfig.height);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    private void userViewInit(){

        userID = new JLabel("当前用户：" + UserManager.getUserInfo().getUID());
        userType = new JLabel("用户类型：" + UserManager.getUserInfo().getStatus());

        logout = new JButton("注销");
        modPassword = new JButton("修改密码");
        userStatusJP = new JPanel(new FlowLayout(FlowLayout.LEFT));    //布局靠左
        userStatusJP.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        userStatusJP.add(userID);
        userStatusJP.add(userType);
        userStatusJP.add(modPassword);
        userStatusJP.add(logout);

        if(UserManager.getUserInfo().getStatus().equals("学生"))
            navBarView = new StudentView();
        else if(UserManager.getUserInfo().getStatus().equals("教师"))
            navBarView = new TeacherView();
        else if(UserManager.getUserInfo().getStatus().equals("管理员"))
            navBarView = new AdminView();
    }

    private void addActionListener(){
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否注销", "确认", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_NO_OPTION) {
                    UserManager.setUserInfo(null);
                    new LoginView();
                    CenterViewManager.closeCenterView();
                }
            }
        });
        modPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordView();

            }
        });
    }

    public void removeFuncView(){
        if(funcViw != null)
            this.remove(funcViw);
    }

    public void setFuncViw(JPanel jPanel){
        funcViw = jPanel;
        this.add(funcViw, BorderLayout.CENTER);
    }

    public int getFuncViewHeight(){
        return funcViw.getHeight();
    }
}
