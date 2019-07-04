package Guet.view;

import Guet.util.CenterViewManager;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentView extends NavBarView {

    private JButton[] jButtons;
    private ButtonStatus status;
    private JPanel[] views;

    enum ButtonStatus{
        PERSONAL_INFO,
        SELECTED_COURSE,
        SELECT_COURSE,
        DROP_COURSE,
        QUERY_GRADE,
        QUERY_GRADE_POINT,
        COURSE_INFO,
    }

    StudentView(){
        super(new String[]{
                "个人信息",
                "已选课程",
                "   选   课   ",
                "   退   课   ",
                "查询成绩",
                "总学分查询",
                "课表信息",
        });
        init();
        addActionListener();
    }

    private void init(){

        JLabel title = new JLabel("主菜单", SwingConstants.CENTER);
        this.add(title);

        jButtons = getJButtons();

        for(JButton jButton : jButtons){
            this.add(jButton);
        }

        views = new JPanel[jButtons.length];

        title.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

//    private void setActionListener(JButton jButton, ButtonStatus buttonStatus, ViewType viewType, String title){
//        int n = buttonStatus.ordinal();
//        jButton.addActionListener(e->{
//            if(status != buttonStatus){
//                if(views[n] == null)
//                    views[n] = new FuncView(title, viewType);
//                CenterViewManager.setFuncViw(views[n]);
//                status = buttonStatus;
//            }
//        });
//    }

    private void addActionListener(){
        jButtons[ButtonStatus.PERSONAL_INFO.ordinal()].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.PERSONAL_INFO.ordinal();
                if(status != ButtonStatus.PERSONAL_INFO){
                    if(views[n] == null)
                        views[n] = new FuncView("个人信息", ViewType.PERSONAL_INFO);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.PERSONAL_INFO;
                }
            }
        });
        //已选课程
        jButtons[ButtonStatus.SELECTED_COURSE.ordinal()].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.SELECTED_COURSE.ordinal();
                if(status != ButtonStatus.SELECTED_COURSE){
                    if(views[n] == null)
                        views[n] = new FuncView("已选课程", ViewType.SELECTED_COURSE);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.SELECTED_COURSE;
                }
            }
        });
        //选课
        jButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.SELECT_COURSE.ordinal();
                if(status != ButtonStatus.SELECT_COURSE){
                    if(views[n] == null)
                        views[n] = new FuncView("选课", ViewType.SELECT_COURSE);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.SELECT_COURSE;
                }
            }
        });
        //退课
        jButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.DROP_COURSE.ordinal();
                if(status != ButtonStatus.DROP_COURSE){
                    if(views[n] == null)
                        views[n] = new FuncView("退课", ViewType.DROP_COURSE);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.DROP_COURSE;
                }
            }
        });

        jButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.QUERY_GRADE.ordinal();
                if(status != ButtonStatus.QUERY_GRADE){
                    if(views[n] == null)
                        views[n] = new FuncView("查询成绩", ViewType.QUERY_GRADE);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.QUERY_GRADE;
                }
            }
        });

        jButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(status != ButtonStatus.QUERY_GRADE_POINT){
                    CenterViewManager.setFuncViw(new FuncView("总学分查询", CenterView.ViewType.QUERY_GRADE));
                    status = ButtonStatus.QUERY_GRADE_POINT;
                }
            }
        });
        jButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = ButtonStatus.COURSE_INFO.ordinal();
                if(status != ButtonStatus.COURSE_INFO){
                    if(views[n] == null)
                        views[n] = new FuncView("课表信息", ViewType.COURSE_INFO);
                    CenterViewManager.setFuncViw(views[n]);
                    status = ButtonStatus.COURSE_INFO;
                }
            }
        });

    }

}
