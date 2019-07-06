package Guet.view;

import Guet.controller.LoginController;
import Guet.dao.LoginMapper;
import Guet.pojo.AdminInfo;
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

    private LoginController loginController;
    private String password;

    LoginView(){
        init();
        addActionListener();
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
        usernameJT.setText("59424856");   //423859
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
                    loginController = new LoginController();
                    password = loginController.getUserPassword(usernameJT.getText());
                    if(password != null){
                        if(password.equals(passwordJT.getText())){
                            if(usernameJT.getText().length() == 6)
                                UserManager.setUserInfo(loginController.getTeacherInfo(usernameJT.getText()));
                            else if(usernameJT.getText().length() == 10)
                                UserManager.setUserInfo(loginController.getStudentInfo(usernameJT.getText()));
                            else
                                UserManager.setUserInfo(new AdminInfo(usernameJT.getText(), "管理员"));
                            CenterViewManager.setCenterView(new CenterView());
                            jFrame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(null, "不存在该用户");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
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
