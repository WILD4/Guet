package Guet.view;

import Guet.util.CenterViewManager;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherView extends NavBarView {

    JButton[] jButtons;

    TeacherView(){
        super(new String[]{
                "个人信息",
                "个人课表信息",
                " 学生成绩查询",
                " 学生成绩录入",
        });
        init();
        addActionListener();
    }

    private void init(){

        JLabel title = new JLabel("主菜单", SwingConstants.CENTER);
        this.add(title);
        jButtons = getJButtons();
        for(JButton jButton : getJButtons())
            this.add(jButton);

        title.setBorder(new EtchedBorder(EtchedBorder.RAISED));

    }

    private void addActionListener(){

        jButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("个人信息", ViewType.TEACHER_INFO));
            }
        });

        jButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterViewManager.setFuncViw(new FuncView("课表信息", ViewType.TEACHER_COURSE));
            }
        });

    }

}