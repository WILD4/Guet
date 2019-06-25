package Guet.view;

import Guet.dao.LoginMapper;
import Guet.util.StudentManager;
import Guet.util.AppConfig;
import Guet.util.ServerSqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {

    JFrame jFrame;
    JPanel loginView;
    JPanel topTitleJP;
    JPanel usernameJP;
    JPanel passwordJP;
    JPanel bottomJP;

    JLabel topTitle;
    JLabel usernameJL;
    JLabel passwordJL;
    JTextField usernameJT;
    JTextField passwordJT;

    JButton loginJB;
    JButton clearJB;

    LoginMapper loginMapper;
    String password;

    LoginView(){
        init();
        addActionListener();
    }

    private void init(){
        jFrame = new JFrame();
        loginView = new JPanel();

        topTitleJP = new JPanel();
        usernameJP = new JPanel();
        passwordJP = new JPanel();
        bottomJP = new JPanel();


        topTitle = new JLabel("学生选课");
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

        loginView.setLayout(new GridLayout(5,1));
        loginView.add(topTitleJP);
        loginView.add(usernameJP);
        loginView.add(passwordJP);
        loginView.add(bottomJP);
        loginView.setBorder(BorderFactory.createEmptyBorder(AppConfig.height/4,AppConfig.width/4,AppConfig.height/4,AppConfig.width/4));
//        loginView.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        jFrame.setLayout(new BorderLayout());
        jFrame.setResizable(false);
        jFrame.add(loginView, BorderLayout.CENTER);
        jFrame.setSize(AppConfig.width, AppConfig.height);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                            StudentManager.setStudent(loginMapper.getStudentInfo(usernameJT.getText()));
                            new CenterView();
                            jFrame.dispose();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "不存在该用户");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    ServerSqlSession.closeSqlSession();
                }
            }
        });

    }

    public static void main(String args[]){
        new LoginView();
    }

}
