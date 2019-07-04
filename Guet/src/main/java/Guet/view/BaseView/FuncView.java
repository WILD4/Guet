package Guet.view.BaseView;

import Guet.util.AppConfig;
import Guet.util.TeacherManager;
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
    private String comboBoxes;

    private JComboBox jComboBox;
    private JTableView jTableView;

    private String[] boxList;
    private ViewType viewType;

    public FuncView(String title, ViewType viewType){
        this.viewType = viewType;
        init(title);
    }

    private void init(String title){
        //学期初始化
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        StringBuilder sb = new StringBuilder();
        comboBoxes = sb.append(nowYear).append('-').append(nowYear-1).append("下半学期").toString();

        mainJPanel = new JPanel(new BorderLayout());
        mainJPanel.setBorder(BorderFactory.createEmptyBorder(0, AppConfig.navBarWidth/3,80,AppConfig.navBarWidth/3));

        //功能区标题
        JLabel funcTitle = new JLabel(title, SwingConstants.CENTER);
        funcTitle.setBorder(BorderFactory.createEmptyBorder(20,0,15,0));
        funcTitle.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,50));

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
            case TEACHER_COURSE:
            case SELECTED_COURSE:
            case QUERY_GRADE:
            case COURSE_INFO:
                JPanel selectSemester = setJComboBox();
                tableView = jTableView.getViewJP(viewType, comboBoxes);
                mainJPanel.add(selectSemester, BorderLayout.NORTH);
                break;
            case PERSONAL_INFO:
                tableView = new PersonalInfoVIew(LoginView.USER_STATUS.STUDENT).getPIView();
                break;
            case TEACHER_INFO:
                tableView = new PersonalInfoVIew(LoginView.USER_STATUS.TEACHER).getPIView();
                break;
            case SELECT_COURSE:
                case DROP_COURSE:
                    tableView = jTableView.getViewJP(viewType, comboBoxes);
                break;
            case TEACHERS_STU_INFO:
                selectSemester = setJComboBox();
                tableView = jTableView.getViewJP(viewType, comboBoxes);
                mainJPanel.add(selectSemester, BorderLayout.NORTH);
                break;
        }

        mainJPanel.add(tableView, BorderLayout.CENTER);
    }

    private JPanel setJComboBox(){
        final JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jComboBox = new JComboBox();
        JLabel jLabel = null;
        switch (viewType){
            case TEACHERS_STU_INFO:
                jLabel = new JLabel("选择课号");
                System.out.println(TeacherManager.getTeacher().toString());
                boxList = TeacherManager.getTeacher().getCourseIDs();
                for(String s : boxList)
                    System.out.println(s);
                break;
            case QUERY_GRADE:
            case SELECTED_COURSE:
            case COURSE_INFO:
            case TEACHER_COURSE:
                jLabel = new JLabel("选择学期");
                Calendar c = Calendar.getInstance();
                int beginYear = 1997;
                int nowYear = c.get(Calendar.YEAR);
                boxList = new String[2 * (nowYear - beginYear)];
                StringBuilder sb = new StringBuilder();
                for(int i = 0, year = nowYear; i < boxList.length; i += 2, year--){
                    boxList[i] = sb.append(year).append('-').append(year-1).append("下半学期").toString();
                    sb.delete(0,sb.length());
                    boxList[i +1] = sb.append(year).append('-').append(year-1).append("上半学期").toString();
                    sb.delete(0,sb.length());
                }
                break;
        }

        comboBoxes = boxList[0];
        jComboBox.setModel(new DefaultComboBoxModel(boxList));
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    comboBoxes = (String) jComboBox.getSelectedItem();
                    jTableView.setContent(comboBoxes);
                }
            }
        });
        jPanel.add(jLabel);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        jPanel.add(jComboBox);
        jPanel.setVisible(true);
        return jPanel;
    }

}
