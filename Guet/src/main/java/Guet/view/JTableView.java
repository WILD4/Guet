package Guet.view;

import Guet.controller.CourseController;
import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.pojo.TeacherAndStuInfo;
import Guet.util.*;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class JTableView extends JPanel{

    private JLabel tableStatus;
    private String[][] content;
    private String[] title;
    private JTable tableList;
    private List<?> listContent;
    private int selectedRow;
    DefaultTableModel tableModel;
    CourseController courseController;
    ViewType viewType;

    private void setListContent(String comboBoxes){
        switch (viewType){
            case DROP_COURSE:
            case SELECTED_COURSE:
                listContent = courseController.getSelectedCourseInfo(StudentManager.getStudent().getStudentId(), comboBoxes);
                if(listContent.size() == 0)
                    return;
                title = new String[]{"课程序号", "课程代码", "课程名称","教师","选课类型","学分"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.SELECTED_COURSE);
                }
                break;
            case SELECT_COURSE:
//                listContent = courseController.getNoSelectedCourse(extra);
//                title = new String[]{"课程序号", "课程代码", "课程名称","教师","选课类型","学分"};
                listContent = courseController.getCourseInfo(StudentManager.getStudent().getStudentId());
                if(listContent.size() == 0)
                    return;
                title = new String[]{"课程代码", "课程名称","学分","学时","上课周次","选课类型"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((CourseInfo)listContent.get(i)).toArray();
                }
                break;
            case NO_SELECT_COURSE:
                listContent = courseController.getNoSelectedCourse(StudentManager.getStudent().getStudentId(), CourseManager.getCourseInfo().getCourseCode());
                if(listContent.size() == 0)
                    return;
                title = new String[]{"课程序号","课程名称","教师","学分","上课周次","上课时间"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.NO_SELECT_COURSE);
                }
                break;
            case QUERY_GRADE:
                listContent = courseController.getStudentGrade(StudentManager.getStudent().getStudentId(), comboBoxes);
                if(listContent.size() == 0)
                    return;
                title = new String[]{"课程代码","课程序号","课程名称","成绩","学分","选课类型"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.QUERY_GRADE);
                }
                break;
            case TEACHERS_STU_INFO:
                listContent = courseController.getTeasStuInfo(UserManager.getUserInfo().getUID(), comboBoxes);
                if(listContent.size() == 0)
                    return;
                title = new String[]{"学号","姓名","学分","成绩"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((TeacherAndStuInfo)listContent.get(i)).toArray();
                }

                tableStatus.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
                StringBuilder sb = new StringBuilder();
                sb.append("<html>");
                sb.append("课程名称：");
                sb.append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseName());
                sb.append("<&emsp&emsp");
                sb.append("课程代码：");
                sb.append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseCode());
                sb.append("<&emsp&emsp");
                sb.append("学生人数：");
                sb.append(listContent.size());
                sb.append("</html>");
                tableStatus.setText(sb.toString());
            break;
            case COURSE_INFO:
            case TEACHER_COURSE:
                if(viewType == ViewType.COURSE_INFO)
                    listContent = courseController.getSelectedCourseInfo(StudentManager.getStudent().getStudentId(), comboBoxes);
                else
                    listContent = courseController.getSelectedCourseInfo(UserManager.getUserInfo().getUID(), comboBoxes);
                if(listContent.size() == 0)
                    return;
                title = new String[]{"","星期一","星期二","星期三","星期四","星期五","星期六","星期七"};
                content = new String[5][title.length];
                for(int i = 0; i < content.length; i++){
                    content[i][0] = String.valueOf(2 * i + 1) + '\n' + String.valueOf(2 * i + 2);
                }
                for(int i = 0; i < listContent.size(); i++){
                    String courseTime = ((SelectedCourse)listContent.get(i)).getCourseTime();
                    for(int j = 0; j < courseTime.length(); j++){
                        if(courseTime.charAt(j) == '/'){
                            int week = courseTime.charAt(j + 1) - '0';
                            String lessons = String.valueOf(courseTime.charAt(j + 2)) + courseTime.charAt(j + 4);
                            if(lessons.equals("14")){
                                content[convertLesson("12")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                                content[convertLesson("34")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                            }else if(lessons.equals("58")){
                                content[convertLesson("56")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                                content[convertLesson("78")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                            }else if(lessons.equals("36")){
                                content[convertLesson("34")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                                content[convertLesson("56")][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                            }else
                                content[convertLesson(lessons)][week] = ((SelectedCourse)listContent.get(i)).getCourseList();
                        }
                    }
                }break;
        }
    }

    private void JPanelInit(){
        JTableInit();

        this.setPreferredSize(new Dimension(tableList.getWidth(), tableList.getHeight()));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane pane = new JScrollPane(tableList);
        this.add(pane, BorderLayout.CENTER);
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
        tableList = new JTable(tableModel);
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
//                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(tableList.getSelectedRow(),0))), StudentManager.getStudent().getStudentId());break;
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
                            courseController.dropCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), StudentManager.getStudent().getStudentId());
                            break;
                        case NO_SELECT_COURSE:
                            result = JOptionPane.showConfirmDialog(null, "是否选课", "确认", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.YES_NO_OPTION)
                                System.out.println(String.valueOf(tableList.getValueAt(selectedRow,0)));
                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), StudentManager.getStudent().getStudentId());
                            break;

                    }
                    if(result == JOptionPane.YES_NO_OPTION)
                        tableModel.removeRow(selectedRow);
                    //确认之后关闭连接
//                    ServerSqlSession.closeSqlSession();
                }
            }
        });
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE){
                    float grade = Float.parseFloat(String.valueOf(tableModel.getValueAt(e.getLastRow(), e.getColumn())));
//                    courseController.updateStuGrade();
                }
            }
        });
    }

    public JPanel getViewJP(ViewType typeView, String comboBoxes) throws IOException {
        courseController = new CourseController();
        viewType = typeView;
        tableStatus = new JLabel();
        tableStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        setListContent(comboBoxes);
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
    public void setTableColumnCenter(JTable table){
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

    public void setContent(String semester){
        setListContent(semester);
        if(listContent.size() == 0){
            tableModel = new DefaultTableModel();
            tableStatus.setText("");
        }
        else{
            if(viewType == ViewType.TEACHERS_STU_INFO){
                StringBuilder sb = new StringBuilder();
                sb.append("<html>").append("课程名称：")
                        .append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseName())
                        .append("<&emsp&emsp").append("课程代码：")
                        .append(((TeacherAndStuInfo)listContent.get(0)).getCourseInfo().getCourseCode())
                        .append("<&emsp&emsp")
                        .append("学生人数：")
                        .append(listContent.size())
                        .append("</html>");
                tableStatus.setText(sb.toString());
            }
            tableModel = new DefaultTableModel(content, title){
                @Override
                public boolean isCellEditable(int row, int columnIndex) {
                    if(columnIndex == 3 && viewType == ViewType.TEACHERS_STU_INFO)
                        return true;
                    return false;
                }
            };
            tableModel.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    System.out.println(tableModel.getValueAt(e.getLastRow(), e.getColumn()));
                }
            });
        }
        tableList.setModel(tableModel);
        tableList.updateUI();
    }



    private int convertLesson(String lesson){
        if(lesson.equals("12"))
            return 0;
        else if(lesson.equals("34"))
            return 1;
        else if(lesson.equals("56"))
            return 2;
        else if(lesson.equals("78"))
            return 3;
        else if(lesson.equals("90"))
            return 4;
        return -1;
    }

}
