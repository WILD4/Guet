package Guet.view.BaseView;

import Guet.util.AppConfig;
import Guet.view.CenterView;
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
    private String semester;

    private JComboBox jComboBox;
    private JTableView jTableView;

    private String[] semesterList;

    public FuncView(String title, ViewType viewType){
        init(title, viewType);
    }

    private void init(String title, ViewType viewType){

        initSemesterList();

        mainJPanel = new JPanel(new BorderLayout());
        mainJPanel.setBorder(BorderFactory.createEmptyBorder(0, AppConfig.navBarWidth/3,80,AppConfig.navBarWidth/3));

        JLabel funcTitle = new JLabel(title, SwingConstants.CENTER);
        funcTitle.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        funcTitle.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,50));

        try {
            setTableView(viewType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.add(mainJPanel, BorderLayout.CENTER);
        this.add(funcTitle, BorderLayout.NORTH);
    }

    private void setTableView(ViewType vt) throws IOException {
        jTableView = new JTableView();
        switch (vt){
            case SELECTED_COURSE:
                case QUERY_GRADE:
                JPanel selectSemester = setJComboBox();
                tableView = jTableView.getViewJP(vt, semester);
                mainJPanel.add(selectSemester, BorderLayout.NORTH);
                break;
            case PERSONAL_INFO:
                tableView = new PersonalInfoVIew(LoginView.USER_STATUS.STUDENT).getPIView();
                break;
            case TEACHER_INFO:
                tableView = new PersonalInfoVIew(LoginView.USER_STATUS.TEACHER).getPIView();
                break;
            case COURSE_INFO:
            case SELECT_COURSE:
                case DROP_COURSE:
                tableView = jTableView.getViewJP(vt, semester);
                break;
        }

        mainJPanel.add(tableView, BorderLayout.CENTER);

    }

    private JPanel setJComboBox(){
        final JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jComboBox = new JComboBox();

        jComboBox.setModel(new DefaultComboBoxModel(semesterList));
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    semester = (String) jComboBox.getSelectedItem();
                    jTableView.setContent(semester);
                }
            }
        });
        JLabel jLabel = new JLabel("选择学期");
        jPanel.add(jLabel);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        jPanel.add(jComboBox);
        jPanel.setVisible(true);
        return jPanel;
    }

    private void initSemesterList(){
        Calendar c = Calendar.getInstance();
        int beginYear = 1997;
        int nowYear = c.get(Calendar.YEAR);
        semesterList = new String[2 * (nowYear - beginYear)];

        for(int i = 0, year = nowYear; i < semesterList.length; i += 2, year--){
            semesterList[i] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "下半学期";
            semesterList[i + 1] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "上半学期";
        }
        semester = semesterList[0];
    }


}
