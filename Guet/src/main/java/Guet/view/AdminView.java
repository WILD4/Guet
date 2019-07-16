package Guet.view;

import Guet.pojo.AdminInfo;
import Guet.util.CenterViewManager;
import Guet.view.CenterView.ViewType;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;

import javax.swing.*;

public class AdminView extends NavBarView {

    private ButtonStatus status;


    enum ButtonStatus{
        STUDENT_MANAGER,
        TEACHER_MANAGER,
        COURSE_MANAGER,
        NO_SELECTED_COURSE,
        SUM_CREDIT,
    }

    AdminView(){
        super(new String[]{
                "学生管理",
                "教师管理",
                "课程管理",
                "未被选修课程",
                "学生总学分",
        });
        addActionListener();
    }

    private void addActionListener(){
        jButtons[0].addActionListener(e->{
            int n = ButtonStatus.STUDENT_MANAGER.ordinal();
            if(status != ButtonStatus.STUDENT_MANAGER){
//                if(views[n] == null)
                views[n] = new FuncView("学生信息管理", ViewType.STUDENT_MANAGER);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.STUDENT_MANAGER;
            }
        });
        jButtons[1].addActionListener(e->{
            int n = ButtonStatus.TEACHER_MANAGER.ordinal();
            if(status != ButtonStatus.TEACHER_MANAGER){
//                if(views[n] == null)
                views[n] = new FuncView("教师信息管理", ViewType.TEACHER_MANAGER);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.TEACHER_MANAGER;
            }
        });

        jButtons[2].addActionListener(e -> {
            int n = ButtonStatus.COURSE_MANAGER.ordinal();
            if(status != ButtonStatus.COURSE_MANAGER){
//                if(views[n] == null)
                views[n] = new FuncView("课程信息管理", ViewType.COURSE_MANAGER);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.COURSE_MANAGER;
            }
        });

        jButtons[3].addActionListener(e->{
            int n = ButtonStatus.NO_SELECTED_COURSE.ordinal();
            if(status != ButtonStatus.NO_SELECTED_COURSE){
                views[n] = new FuncView("未被选修课程", ViewType.NO_STU_SELECTE_COURSE);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.NO_SELECTED_COURSE;
            }
        });
        jButtons[4].addActionListener(e -> {
            int n = ButtonStatus.SUM_CREDIT.ordinal();
            if(status != ButtonStatus.SUM_CREDIT){
                views[n] = new FuncView("学生总学分", ViewType.SUM_CREDIT);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.SUM_CREDIT;
            }
        });
    }
}
