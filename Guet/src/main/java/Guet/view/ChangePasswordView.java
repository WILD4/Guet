package Guet.view;

import Guet.controller.LoginController;
import Guet.util.StudentManager;
import Guet.util.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChangePasswordView extends JFrame {

    private JFrame jFrame;
    private JPanel jPanel;
    private JPanel btnJP;
    private JLabel oldPasswordJL;
    private JLabel newPasswordJL1;
    private JLabel newPasswordJL2;
    private JTextField oldPasswordJT;
    private JTextField newPasswordJT1;
    private JTextField newPasswordJT2;

    private JButton clearButton;
    private JButton confirmButton;

    private LoginController loginController;

    ChangePasswordView(){
        init();
        setActionListener();
    }

    private void init(){
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(340,230));
        btnJP = new JPanel();
        jPanel = new JPanel(new GridLayout(4,2,20,10));
        jPanel.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));
        btnJP.setBorder(BorderFactory.createEmptyBorder(0,this.getWidth()/4,25,this.getWidth()/4));
        oldPasswordJL = new JLabel("旧密码", SwingConstants.CENTER);
        newPasswordJL1 = new JLabel("新密码", SwingConstants.CENTER);
        newPasswordJL2 = new JLabel("确认密码", SwingConstants.CENTER);
        oldPasswordJT = new JTextField(10);
        newPasswordJT1 = new JPasswordField(10);
        newPasswordJT2 = new JPasswordField(10);
        clearButton = new JButton("清除");
        confirmButton = new JButton("确认");

        jPanel.add(oldPasswordJL);
        jPanel.add(oldPasswordJT);
        jPanel.add(newPasswordJL1);
        jPanel.add(newPasswordJT1);
        jPanel.add(newPasswordJL2);
        jPanel.add(newPasswordJT2);

        btnJP.add(confirmButton);
        btnJP.add(clearButton);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(btnJP, BorderLayout.SOUTH);
        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setTitle("修改密码");
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    private void setActionListener(){

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginController = new LoginController();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(!(oldPasswordJT.getText().equals("") || newPasswordJT1.getText().equals("")|| newPasswordJT2.getText().equals(""))){
                    if(!oldPasswordJT.getText().equals(loginController.getUserPassword(UserManager.getUserInfo().getUID())))
                        JOptionPane.showMessageDialog(jFrame, "密码不正确", "确认", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        if(newPasswordJT1.getText().equals(newPasswordJT2.getText())){
                            loginController.changePassword(UserManager.getUserInfo().getUID(), newPasswordJT1.getText());
                            JOptionPane.showMessageDialog(jFrame, "修改成功", "确认", JOptionPane.INFORMATION_MESSAGE);
                            jFrame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(jFrame, "两次密码不一致", "确认", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    clearJText();
                }else {
                    JOptionPane.showMessageDialog(jFrame, "请输入正确信息", "确认", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearJText();
            }
        });
    }

    private void clearJText(){
        oldPasswordJT.setText("");;
        newPasswordJT1.setText("");;
        newPasswordJT2.setText("");;
    }

}
