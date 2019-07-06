package Guet.view;

import Guet.util.CenterViewManager;
import Guet.view.BaseView.FuncView;
import Guet.view.BaseView.NavBarView;
import Guet.view.CenterView.ViewType;

class TeacherView extends NavBarView {

    private ButtonStatus status;

    enum ButtonStatus{
        PERSONAL_INFO,
        SELECTED_COURSE,
        QUERY_STU_INFO,
        UPDATE_STU_GRADE,
    }

    TeacherView(){
        super(new String[]{
                "个人信息",
                "个人课表信息",
                " 学生信息查询",
                " 学生成绩录入",
        });
        addActionListener();
    }

    private void addActionListener(){

        jButtons[0].addActionListener(e -> {
            int n = ButtonStatus.PERSONAL_INFO.ordinal();
            if(status != ButtonStatus.PERSONAL_INFO){
                if(views[n] == null)
                    views[n] = new FuncView("个人信息", ViewType.PERSONAL_INFO);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.PERSONAL_INFO;
            }
        });

        jButtons[1].addActionListener(e -> {
            int n = ButtonStatus.SELECTED_COURSE.ordinal();
            if(status != ButtonStatus.SELECTED_COURSE){
//                if(views[n] == null)
                    views[n] = new FuncView("个人课表信息", ViewType.TEACHER_COURSE);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.SELECTED_COURSE;
            }
        });

        jButtons[2].addActionListener(e-> {
            int n = ButtonStatus.QUERY_STU_INFO.ordinal();
            if(status != ButtonStatus.QUERY_STU_INFO){
//                if(views[n] == null)
                    views[n] = new FuncView("查询学生信息", ViewType.TEACHERS_STU_INFO);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.QUERY_STU_INFO;
            }
        });

        jButtons[3].addActionListener(e->{
            int n = ButtonStatus.UPDATE_STU_GRADE.ordinal();
            if(status != ButtonStatus.UPDATE_STU_GRADE){
                if(views[n] == null)
                    views[n] = new FuncView("查询学生成绩", ViewType.UPDATE_STU_GRADE);
                CenterViewManager.setFuncViw(views[n]);
                status = ButtonStatus.UPDATE_STU_GRADE;
            }
        });

    }

}
