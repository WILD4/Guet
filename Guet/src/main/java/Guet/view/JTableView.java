package Guet.view;

import Guet.controller.CourseController;
import Guet.pojo.CourseInfo;
import Guet.pojo.SelectedCourse;
import Guet.util.*;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class JTableView extends JPanel{

    private String[][] content;
    private String[] title;
    private JTableList tableList;
    private JPanel jPanel;
    private int courseID;
    List<?> listContent;
    int selectedRow;
    DefaultTableModel tableModel;
    CourseController courseController;
    ViewType viewType;

    private void setListContent(){
        switch (viewType){
            case DROP_COURSE:
            case SELECTED_COURSE:
                listContent = courseController.getSelectedCourseInfo(StudentManager.getStudent().getId());
                title = new String[]{"课程序号", "课程代码", "课程名称","教师","选课类型","学分"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.SELECTED_COURSE);
                }
                break;
            case SELECT_COURSE:
//                listContent = courseController.getNoSelectedCourse(extra);
//                title = new String[]{"课程序号", "课程代码", "课程名称","教师","选课类型","学分"};
                listContent = courseController.getCourseInfo(StudentManager.getStudent().getId());
                title = new String[]{"课程代码", "课程名称","学分","学时","上课周次","选课类型"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((CourseInfo)listContent.get(i)).toArray();
                }
                break;
            case NO_SELECT_COURSE:
                listContent = courseController.getNoSelectedCourse(StudentManager.getStudent().getId(), CourseManager.getCourseInfo().getCourseCode());
                title = new String[]{"课程序号","课程名称","教师","学分","上课周次","上课时间"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.NO_SELECT_COURSE);
                }
                break;
            case QUERY_GRADE:
                listContent = courseController.getStudentGrade(StudentManager.getStudent().getId());
                title = new String[]{"课程代码","课程序号","课程名称","成绩","学分","选课类型"};
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.QUERY_GRADE);
                }
                break;
        }
    }

    private void JPanelInit(){
        JTableInit();
        this.setPreferredSize(new Dimension(tableList.getWidth(), tableList.getHeight()));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane pane = new JScrollPane(tableList);
//        this.add(tableList.getTableHeader(), BorderLayout.NORTH);
        this.add(pane, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(0, AppConfig.navBarWidth/3,0,AppConfig.navBarWidth/3));
    }

    private void JTableInit(){
        tableModel = new DefaultTableModel(content, title);
        tableList = new JTableList(tableModel);
        sortTable();
        tableList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //内容居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableList.setDefaultRenderer(Object.class, defaultTableCellRenderer);

        FitTableColumns(tableList);

        tableList.getTableHeader().setReorderingAllowed(false);
        tableList.setRowHeight(AppConfig.height/30);
//        tableList.getTableHeader().setBackground(Color.LIGHT_GRAY);
//        tableList.setEnabled(true);
//        tableList.setBackground(Color.LIGHT_GRAY);

    }

    public void setTableListListener(){
        tableList.setBackground(Color.WHITE);
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
//                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(tableList.getSelectedRow(),0))), StudentManager.getStudent().getId());break;
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
                            courseController.dropCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), StudentManager.getStudent().getId());
                            break;
                        case NO_SELECT_COURSE:
                            result = JOptionPane.showConfirmDialog(null, "是否选课", "确认", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.YES_NO_OPTION)
                                courseController.stuSelectCourse(Integer.parseInt(String.valueOf(tableList.getValueAt(selectedRow,0))), StudentManager.getStudent().getId());
                            break;

                    }
                    if(result == JOptionPane.YES_NO_OPTION)
                        tableModel.removeRow(selectedRow);
                    //确认之后关闭连接
//                    ServerSqlSession.closeSqlSession();
                }
            }
        });
    }

    public JPanel getViewJP(ViewType typeView) throws IOException {
        courseController = new CourseController();
        viewType = typeView;
        setListContent();
        JPanelInit();
        setTableListListener();

        return this;
    }
    //使JTable列宽适应文本内容
    public void FitTableColumns(JTable myTable){
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
                int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width+myTable.getIntercellSpacing().width);
        }
    }

    //点击表头排序
    public void sortTable(){
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                tableModel);
        tableList.setRowSorter(sorter);
    }

}
