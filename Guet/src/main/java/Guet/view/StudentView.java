package Guet.view;

import Guet.util.CenterViewManager;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StudentView extends NavBarView {

    JButton[] jButtons;

    StudentView(){
        super(new String[]{
                "个人信息",
                "已选课程",
                "   选   课   ",
                "   退   课   ",
                "查询成绩",
                "学分绩查询",
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

        title.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

    private void addActionListener(){
        jButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if(viewType != CenterView.ViewType.PERSONAL_INFO){
                    CenterViewManager.setFuncViw(new FuncView("个人信息", ViewType.PERSONAL_INFO));
//                    viewType = CenterView.ViewType.PERSONAL_INFO;
//                }
            }
        });
        //已选课程
        jButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("已选课程", CenterView.ViewType.SELECTED_COURSE));
            }
        });
        //选课
        jButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("选课", CenterView.ViewType.SELECT_COURSE));
            }
        });
        //退课
        jButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("退课", CenterView.ViewType.DROP_COURSE));
            }
        });

        jButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("查询成绩", CenterView.ViewType.QUERY_GRADE));
            }
        });

        jButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("课表信息", ViewType.COURSE_INFO));
            }
        });

    }

}
