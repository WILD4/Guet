package Guet.view;

import Guet.util.AppConfig;
import Guet.util.CenterViewManager;
import Guet.util.ServerSqlSession;
import Guet.util.StudentManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CenterView extends JFrame {

    private JFrame jFrame;
    private JPanel navBarJP;
    private JPanel funcViw;

    private JLabel funcTitle;
    private JLabel title;
    private JButton[] jButtons;
    private String[] jButtonsName;

    private JTableView jTableView;

    private ViewType viewType;

    CenterView(){
        init();
        addActionListener();
    }

    public enum ViewType{
        PERSONAL_INFO,
        SELECTED_COURSE,
        SELECT_COURSE,
        DROP_COURSE,
        NO_SELECT_COURSE,
        QUERY_GRADE,
    }

    private void init() {
        jFrame = new JFrame();
        CenterViewManager.setCenterView(jFrame);
        FlowLayout flowLayout = new FlowLayout(0,10,10);
        flowLayout.setAlignment(FlowLayout.CENTER);
        navBarJP = new JPanel(flowLayout);
        funcViw = new JPanel(new GridLayout(4,1));

        title = new JLabel("主菜单", JLabel.HORIZONTAL);
        funcTitle = new JLabel("",JLabel.HORIZONTAL);
        jButtonsName = new String[]{
                "个人信息",
                "已选课程",
                "   选   课   ",
                "   退   课   ",
                "查询成绩"
        };
        jButtons = new JButton[jButtonsName.length];
        for(int i = 0; i < jButtonsName.length; i++){
            jButtons[i] = new JButton(jButtonsName[i]);
        }


        navBarJP.setPreferredSize(new Dimension(AppConfig.navBarWidth, AppConfig.navBarHeight));
        navBarJP.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        title.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        funcTitle.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,50));

        navBarJP.add(title);
        for(JButton jButton : jButtons){
            navBarJP.add(jButton);
        }
        funcViw.add(funcTitle);
        jFrame.add(navBarJP, BorderLayout.WEST);
        jFrame.add(funcViw, BorderLayout.CENTER);
        jFrame.setSize(AppConfig.width, AppConfig.height);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void addActionListener(){
        //个人信息
        jButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            if(viewType != ViewType.PERSONAL_INFO){
                while (funcViw.getComponentCount() > 1)
                    funcViw.remove(1);
                funcTitle.setText("个人信息");
                funcViw.add(new PersonalInfoVIew().getPIView());
                jFrame.repaint();
                jFrame.validate();
                viewType = ViewType.PERSONAL_INFO;
            }
            }
        });
        //已选课程
        jButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJBListener("已选课程", ViewType.SELECTED_COURSE);
            }
        });
        //选课
        jButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJBListener("选课", ViewType.SELECT_COURSE);
            }
        });
        //退课
        jButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJBListener("退课", ViewType.DROP_COURSE);
            }
        });

        jButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJBListener("查询成绩", ViewType.QUERY_GRADE);
            }
        });

    }

    private void setJBListener(String title, ViewType vt){
        jTableView = new JTableView();
        if(viewType != vt){
            while(funcViw.getComponentCount() > 1)
                funcViw.remove(1);
            funcTitle.setText(title);
            try{
                funcViw.add(jTableView.getViewJP(vt));
            }catch (IOException ex){
                ex.printStackTrace();
            }
            jFrame.repaint();
            jFrame.validate();
            viewType = vt;
        }
    }

    public void refreshJFrame(){
        jFrame.repaint();
        jFrame.validate();
    }

}
