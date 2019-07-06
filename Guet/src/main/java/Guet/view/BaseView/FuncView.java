package Guet.view.BaseView;

import Guet.pojo.TeacherInfo;
import Guet.util.AppConfig;
import Guet.util.UserManager;
import Guet.view.JTableView;
import Guet.view.LoginView;
import Guet.view.PersonalInfoVIew;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Calendar;

public class FuncView extends JPanel {

    private JPanel mainJPanel;
    private JPanel tableView;

    private JTableView jTableView;

    private ViewType viewType;

    public FuncView(String title, ViewType viewType){
        this.viewType = viewType;
        init(title);
    }

    private void init(String title){
        //学期初始化
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        StringBuilder sb = new StringBuilder();

        mainJPanel = new JPanel(new BorderLayout());
        mainJPanel.setBorder(BorderFactory.createEmptyBorder(0, AppConfig.navBarWidth/3,40,AppConfig.navBarWidth/3));

        //功能区标题
        JLabel funcTitle = new JLabel(title, SwingConstants.CENTER);
        funcTitle.setBorder(BorderFactory.createEmptyBorder(20,0,5,0));
        funcTitle.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,42));

        try {
            setTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.add(mainJPanel, BorderLayout.CENTER);
        this.add(funcTitle, BorderLayout.NORTH);
    }

    private void setTableView() throws IOException {
        jTableView = new JTableView();
        switch (viewType){
            case PERSONAL_INFO:
            case TEACHER_INFO:
                tableView = new PersonalInfoVIew().getPIView();
                break;
            default:
                tableView = jTableView.getViewJP(viewType);
        }

        mainJPanel.add(tableView, BorderLayout.CENTER);
    }


}
