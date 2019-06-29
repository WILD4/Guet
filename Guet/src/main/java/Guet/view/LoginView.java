package Guet.view;

import Guet.dao.LoginMapper;
import Guet.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {

    private JFrame jFrame;

    private JLabel usernameJL;
    private JLabel passwordJL;
    private JTextField usernameJT;
    private JTextField passwordJT;

    private JButton loginJB;
    private JButton clearJB;

    private LoginMapper loginMapper;
    private String password;

    LoginView(){
        init();
        addActionListener();
    }

    public enum USER_STATUS{
        TEACHER,
        STUDENT,
        ADMIN,
    }

    private void init(){
        jFrame = new JFrame();
        JPanel loginView = new JPanel();

        JPanel topTitleJP = new JPanel();
        JPanel usernameJP = new JPanel();
        JPanel passwordJP = new JPanel();
        JPanel bottomJP = new JPanel();


        JLabel topTitle = new JLabel("教务管理系统");
        topTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,50));
        usernameJL = new JLabel("用户名");
        passwordJL = new JLabel("口    令");
        usernameJT = new JTextField(10);
        passwordJT = new JTextField(10);
        usernameJT.setText("1700420201");
        passwordJT.setText("123456");

        loginJB = new JButton("登录");
        clearJB = new JButton("清楚");

        topTitleJP.add(topTitle);
        usernameJP.add(usernameJL);
        usernameJP.add(usernameJT);
        passwordJP.add(passwordJL);
        passwordJP.add(passwordJT);
        bottomJP.add(loginJB);
        bottomJP.add(clearJB);

        loginView.setLayout(new GridLayout(4,1));
        loginView.add(usernameJP);
        loginView.add(passwordJP);
        loginView.add(bottomJP);
        loginView.setBorder(BorderFactory.createEmptyBorder(AppConfig.height/8,AppConfig.width/4,AppConfig.height/4,AppConfig.width/4));
        topTitle.setBorder(BorderFactory.createEmptyBorder(AppConfig.height/6,0,0,0));
//        loginView.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        jFrame.setLayout(new BorderLayout());
        jFrame.setResizable(false);
        jFrame.add(topTitleJP, BorderLayout.NORTH);
        jFrame.add(loginView, BorderLayout.CENTER);
        jFrame.setSize(AppConfig.width, AppConfig.height);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }

    private void addActionListener(){

        loginJB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    loginMapper = ServerSqlSession.openSqlSession().getMapper(LoginMapper.class);
                    password = loginMapper.queryUserById(usernameJT.getText());
                    if(password != null){
                        if(password.equals(passwordJT.getText())){
                            loginMapper.updateLoginInfo();
                            if(usernameJT.getText().length() == 10){
                                StudentManager.setStudent(loginMapper.getStudentInfo(usernameJT.getText()));
                                CenterViewManager.setCenterView(new CenterView(USER_STATUS.STUDENT));
                            }else if(usernameJT.getText().length() == 6){
                                TeacherManager.setTeacher(loginMapper.getTeacherInfo(usernameJT.getText()));
                                CenterViewManager.setCenterView(new CenterView(USER_STATUS.TEACHER));
                            }
                            jFrame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(null, "不存在该用户");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    ServerSqlSession.closeSqlSession();
                }
            }
        });

        clearJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameJT.setText("");
                passwordJT.setText("");
            }
        });

    }

    public static void main(String args[]){
        new LoginView();
    }

}
