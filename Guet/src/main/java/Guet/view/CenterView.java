package Guet.view;

import Guet.util.AppConfig;
import Guet.util.CenterViewManager;

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
    private JPanel navBarJP;
    private JPanel funcViw;
    private JPanel mainJPanel;
    private JPanel selectSemester;

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
        mainJPanel = new JPanel(new BorderLayout());
        selectSemester = selectSemester();
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
            while(mainJPanel.getComponentCount() > 0)
                mainJPanel.remove(0);
            funcTitle.setText(title);
            try{
                mainJPanel.add(jTableView.getViewJP(vt), BorderLayout.CENTER);
                mainJPanel.add(selectSemester, BorderLayout.NORTH);
                selectSemester.setBorder(BorderFactory.createEmptyBorder(0,0,5,AppConfig.width/2));
                funcViw.add(mainJPanel);
            }catch (IOException ex){
                ex.printStackTrace();
            }
            jFrame.repaint();
            jFrame.validate();
            viewType = vt;
        }
    }

    public JPanel selectSemester(){
        JPanel jPanel = new JPanel(new FlowLayout());
        int beginYear = 1997;
        final JComboBox jComboBox = new JComboBox();
        Calendar c = Calendar.getInstance();
        int nowYear = c.get(Calendar.YEAR);
        String[] semesterList = new String[2 * (nowYear - beginYear)];

        for(int i = 0, year = nowYear; i < semesterList.length; i += 2, year--){
            semesterList[i] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "下半学期";
            semesterList[i + 1] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "上半学期";
        }

        jComboBox.setModel(new DefaultComboBoxModel(semesterList));
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(jComboBox.getSelectedItem().toString());
                }
            }
        });
        JLabel jLabel = new JLabel("选择学期");
        jPanel.add(jLabel, BorderLayout.WEST);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        jPanel.add(jComboBox, BorderLayout.CENTER);
        return jPanel;
    }


}
