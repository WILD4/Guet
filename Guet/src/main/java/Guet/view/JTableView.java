package Guet.view;

import Guet.controller.CourseController;
import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.pojo.TeacherAndStuInfo;
import Guet.pojo.TeacherInfo;
import Guet.util.*;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class JTableView extends JPanel{

    private JLabel tableStatus;
    private String[][] content;
    private String[] title;
    private JTable tableList;
    private List<?> listContent;
    private int selectedRow;
    private DefaultTableModel tableModel;
    private CourseController courseController;
    private ViewType viewType;
    private JComboBox jComboBox;
    private JPanel jComboBoxJP;
    private String[] boxList;

    private static String userID;
    private static String comboBox;

    private void setListContent(){
        switch (viewType){
            case SELECTED_COURSE:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBox();
            case DROP_COURSE:
                listContent = courseController.getSelectedCourseInfo(userID, comboBox);
                title = new String[]{"课程序号", "课程代码", "课程名称","教师","选课类型","学分"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.SELECTED_COURSE);
                }
                break;
            case SELECT_COURSE:
                listContent = courseController.getCourseInfo(userID);
                title = new String[]{"课程代码", "课程名称","选课类型","学分","上课周次"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((CourseInfo)listContent.get(i)).toArray();
                }
                break;
            case NO_SELECT_COURSE:
                listContent = courseController.getNoSelectedCourse(userID, CourseManager.getCourseInfo().getCourseCode());
                title = new String[]{"课程序号","课程名称","教师","学分","上课周次","上课时间"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.NO_SELECT_COURSE);
                }
                break;
            case QUERY_GRADE:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBox();
                listContent = courseController.getStudentGrade(userID, comboBox);
                title = new String[]{"课程代码","课程序号","课程名称","成绩","学分","选课类型"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.QUERY_GRADE);
                }
                break;
            case QUERY_GRADE_POINT:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBox();
                listContent = courseController.getStudentGrade(userID, comboBox);
                title = new String[]{"序号","课程代码","课程名称","成绩","学分","计划学期","计划学分","选课类型"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.QUERY_GRADE_POINT);
                    content[i][0] = String.valueOf(i+1);
                    if(content[i][6].equals("1"))
                        content[i][6] = content[i][4];
                }
                setTableStatus();
                break;
            case TEACHERS_STU_INFO:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBox();
                listContent = courseController.getTeasStuInfo(UserManager.getUserInfo().getUID(), comboBox);
                title = new String[]{"学号","姓名","学分","成绩"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((TeacherAndStuInfo)listContent.get(i)).toArray();
                }
                if(listContent.size() > 0){
                    setTableStatus();
                }

            break;
            case COURSE_INFO:
            case TEACHER_COURSE:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBox();
                if(viewType == ViewType.COURSE_INFO)
                    listContent = courseController.getSelectedCourseInfo(userID, comboBox);
                else
                    listContent = courseController.getSelectedCourseInfo(UserManager.getUserInfo().getUID(), comboBox);
                title = new String[]{"","星期一","星期二","星期三","星期四","星期五","星期六","星期七"};
                if(listContent.size() == 0)
                    return;
                content = new String[5][title.length];
                for(int i = 0; i < content.length; i++){
                    content[i][0] = String.valueOf(2 * i + 1) + '\n' + String.valueOf(2 * i + 2);
                }
                for (Object o : listContent) {
                    String courseTime = ((SelectedCourse) o).getCourseTime();
                    for (int j = 0; j < courseTime.length(); j++) {
                        if (courseTime.charAt(j) == '/') {
                            int week = courseTime.charAt(j + 1) - '0';
                            String lessons = String.valueOf(courseTime.charAt(j + 2)) + courseTime.charAt(j + 4);
                            switch (lessons) {
                                case "14":
                                    content[convertLesson("12")][week] = ((SelectedCourse) o).getCourseList();
                                    content[convertLesson("34")][week] = ((SelectedCourse) o).getCourseList();
                                    break;
                                case "58":
                                    content[convertLesson("56")][week] = ((SelectedCourse) o).getCourseList();
                                    content[convertLesson("78")][week] = ((SelectedCourse) o).getCourseList();
                                    break;
                                case "36":
                                    content[convertLesson("34")][week] = ((SelectedCourse) o).getCourseList();
                                    content[convertLesson("56")][week] = ((SelectedCourse) o).getCourseList();
                                    break;
                                default:
                                    content[convertLesson(lessons)][week] = ((SelectedCourse) o).getCourseList();
                                    break;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void JPanelInit(){
        JTableInit();

        this.setPreferredSize(new Dimension(tableList.getWidth(), tableList.getHeight()));
        this.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(tableList);
        if(jComboBoxJP != null)
            this.add(jComboBoxJP, BorderLayout.NORTH);
        this.add(pane, BorderLayout.CENTER);
        tableStatus.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        if(tableStatus != null)
            this.add(tableStatus, BorderLayout.SOUTH);
    }

    private void JTableInit(){
        tableModel = new DefaultTableModel(content, title){
            @Override
            public boolean isCellEditable(int row, int columnIndex) {
                if(columnIndex == 3 && viewType == ViewType.TEACHERS_STU_INFO)
                    return true;
                return false;
            }
        };
        tableList = new JTable();
        tableList.setModel(tableModel);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(viewType == ViewType.COURSE_INFO || viewType == ViewType.TEACHER_COURSE){
            tableList.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());    // 内容换行
        }else{
            tableList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tableList.setRowHeight(AppConfig.height/30);
            sortTable();
            setTableColumnCenter(tableList);
            FitTableColumns(tableList);
            tableList.getTableHeader().setReorderingAllowed(false);
        }
    }

    private void setTableListListener(){
        tableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    selectedRow = tableList.getSelectedRow();
                    int result = -1;
                    switch (viewType){
                        case SELECT_COURSE:
//                            result = JOptionPane.showConfirmDialog(null, "是否选课", "确认", JOptionPane.YES_NO_OPTION);
//                            if(result == JOptionPane.YES_NO_OPTION)
//                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(tableList.getSelectedRow(),0))), userID);break;
                            CourseManager.getCourseInfo().setCourseCode((String) tableList.getValueAt(tableList.getSelectedRow(),0));
                            try {
                                new SelectCourseView();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case DROP_COURSE:
                            result = JOptionPane.showConfirmDialog(null, "是否退课", "确认", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.YES_NO_OPTION)
                            courseController.dropCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), userID);
                            break;
                        case NO_SELECT_COURSE:
                            result = JOptionPane.showConfirmDialog(null, "是否选课", "确认", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.YES_NO_OPTION)
                                System.out.println(String.valueOf(tableList.getValueAt(selectedRow,0)));
                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), userID);
                            break;

                    }
                    if(result == JOptionPane.YES_NO_OPTION)
                        tableModel.removeRow(selectedRow);
                }
            }
        });
    }

    public JPanel getViewJP(ViewType typeView) throws IOException {
        Calendar c = Calendar.getInstance();
        if(c.get(Calendar.MONTH) > 1 && c.get(Calendar.MONTH) < 8)
            comboBox = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.YEAR) - 1) + "下半学期";
        userID = UserManager.getUserInfo().getUID();
        courseController = new CourseController();
        viewType = typeView;
        tableStatus = new JLabel();
        tableStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        setListContent();
        JPanelInit();
        setTableListListener();

        return this;
    }
    //使JTable列宽适应文本内容
    private void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while(columns.hasMoreElements()){
            TableColumn column = (TableColumn)columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int)myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(int row = 0; row<rowCount; row++){
                int preferredWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferredWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width+myTable.getIntercellSpacing().width);
        }
    }
    //内容居中
    private void setTableColumnCenter(JTable table){
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, r);
    }

    //点击表头排序
    private void sortTable(){
        final TableRowSorter<?> sorter = new TableRowSorter<TableModel>(
                tableModel);
        tableList.setRowSorter(sorter);
    }

    private void setContent(){
        setListContent();
        if(listContent.size() == 0){
            tableModel.setRowCount(0);
            tableStatus.setText("");
        }
        else{
            if(viewType == ViewType.TEACHERS_STU_INFO){
                setTableStatus();
            }
            tableModel.setDataVector(content, title);
        }
        tableModel.fireTableDataChanged();
        tableModel.fireTableStructureChanged();
    }

    private int convertLesson(String lesson){
        switch (lesson) {
            case "12":
                return 0;
            case "34":
                return 1;
            case "56":
                return 2;
            case "78":
                return 3;
            case "90":
                return 4;
        }
        return -1;
    }

    private void setTableStatus(){
        StringBuilder sb = new StringBuilder();
        switch (viewType){
            case TEACHERS_STU_INFO:
                sb.append("<html>").append("课程名称：")
                        .append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseName())
                        .append("<&emsp&emsp").append("课程代码：")
                        .append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseCode())
                        .append("<&emsp&emsp")
                        .append("学生人数：")
                        .append(listContent.size())
                        .append("</html>");
            case QUERY_GRADE_POINT:
                float countCredit = 0f;
                for(int i = 0; i < content.length; i++)
                    countCredit += Float.parseFloat(content[i][6]);
                sb.append("<html>").append("总学分：")
                        .append(countCredit)
                        .append("<&emsp&emsp")
                        .append("课程数：")
                        .append(listContent.size())
                        .append("</html>");
        }
        tableStatus.setText(sb.toString());
    }

    private JPanel setJComboBox(){
        final JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jComboBox = new JComboBox();
        JLabel jLabel = null;
        switch (viewType){
            case TEACHERS_STU_INFO:
                jLabel = new JLabel("选择课号");
                boxList = ((TeacherInfo)UserManager.getUserInfo()).getCourseIDs();
                break;
            case QUERY_GRADE:
            case SELECTED_COURSE:
            case COURSE_INFO:
            case TEACHER_COURSE:
            case QUERY_GRADE_POINT:
                jLabel = new JLabel("选择学期");
                Calendar c = Calendar.getInstance();
                int beginYear = 1997,i = 0;
                int nowYear = c.get(Calendar.YEAR);
                StringBuilder sb = new StringBuilder();
                if(viewType == ViewType.QUERY_GRADE_POINT){
                    boxList = new String[2 * (nowYear - beginYear) + 1];
                    boxList[0] = "all";
                    i = 1;
                }else
                    boxList = new String[2 * (nowYear - beginYear)];
                for(int year = nowYear; i < boxList.length; i += 2, year--){
                    boxList[i] = sb.append(year).append('-').append(year-1).append("下半学期").toString();
                    sb.delete(0,sb.length());
                    if(i + 1 == boxList.length)
                        break;
                    boxList[i + 1] = sb.append(year).append('-').append(year-1).append("上半学期").toString();
                    sb.delete(0,sb.length());
                }

                break;
        }

        comboBox = boxList[0];
        jComboBox.setModel(new DefaultComboBoxModel(boxList));
        jComboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                comboBox = (String) jComboBox.getSelectedItem();
                setContent();
            }
        });
        jPanel.add(jLabel);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        jPanel.add(jComboBox);
        jPanel.setVisible(true);
        return jPanel;
    }
}
