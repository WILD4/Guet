package Guet.view;

import Guet.controller.AdminController;
import Guet.controller.CourseController;
import Guet.pojo.*;
import Guet.util.*;
import Guet.view.CenterView.ViewType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class JTableView extends JPanel{

    private JPanel jtvJP;
    private JLabel tableStatus;
    private JPanel tableStatusJP;
    private String[][] content;
    private String[] title;
    private JTable tableList;
    private List<?> listContent;
    private int selectedRow;
    private DefaultTableModel tableModel;
    private CourseController courseController;
    private ViewType viewType;
    private JComboBox<String> jComboBox;
    private JPanel jComboBoxJP;
    private String[] boxList;

    private static String userID;
    private static String comboBox;
    private static String[] comboBoxes;

    private Map<Integer ,List<Integer>> changeListRow;
    private List<Integer> changeListColumn;

    private AdminController adminController;

    private void setListContent(){
        switch (viewType){
            case SELECTED_COURSE:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBoxJP();
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
            case NO_STU_SELECTE_COURSE:
                if(viewType == ViewType.SELECT_COURSE)
                    listContent = courseController.getCourseInfo(userID);
                else
                    listContent = courseController.getNoStuSelectCourse();
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
                title = new String[]{"课程序号","课程名称","教师","学分","上课周次","上课时间","选课容量"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((SelectedCourse)listContent.get(i)).toStrArray(ViewType.NO_SELECT_COURSE);
                }
                break;
            case QUERY_GRADE:
                if(jComboBoxJP == null)
                    jComboBoxJP = setJComboBoxJP();
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
                    jComboBoxJP = setJComboBoxJP();
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
                    jComboBoxJP = setJComboBoxJP();
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
                    jComboBoxJP = setJComboBoxJP();
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
            case STUDENT_MANAGER:
                jComboBoxJP = new JPanel(new FlowLayout());
                jComboBoxJP.add(setJComboBoxJP());
                jtvJP.add(setTableStatusJP(), BorderLayout.SOUTH);
                listContent = new AdminController().getAllStuInfo();
                title = new String[]{"学号","姓名","性别","出生年月","籍贯"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((StudentInfo)listContent.get(i)).toArray();
                }
            case TEACHER_MANAGER:
                jComboBoxJP = new JPanel(new FlowLayout());
                jComboBoxJP.add(setJComboBoxJP());
                jtvJP.add(setTableStatusJP(), BorderLayout.SOUTH);
                listContent = new AdminController().getAllTeaInfo();
                title = new String[]{"工号","姓名","性别","出生年月","籍贯","职务"};
                if(listContent.size() == 0)
                    return;
                content = new String[listContent.size()][];
                for(int i = 0; i < listContent.size(); i++){
                    content[i] = ((TeacherInfo)listContent.get(i)).toArray();
                }
        }
    }

    private void JPanelInit(){
        JTableInit();
        jtvJP.setPreferredSize(new Dimension(tableList.getWidth(), tableList.getHeight()));
        JScrollPane pane = new JScrollPane(tableList);
        if(jComboBoxJP != null)
            jtvJP.add(jComboBoxJP, BorderLayout.NORTH);
        jtvJP.add(pane, BorderLayout.CENTER);
    }

    private void JTableInit(){
        tableModel = new DefaultTableModel(content, title);
        tableModel.addTableModelListener(e->{
            try{
                if(e.getType() == TableModelEvent.UPDATE){
//                    if(userInfoList != null){
//                        if(e.getColumn() != 0){
//                            String uid = String.valueOf(tableModel.getValueAt(e.getLastRow(), e.getColumn()));
//                            if(!userInfoList.containsKey(uid)){
//                                userInfoList.put(uid, new UserInfo());
//
//                            }
//                        }
//                    }
                    if(e.getLastRow() < 0 || e.getLastRow() > tableModel.getRowCount())
                        return;
                    if(!changeListRow.containsKey(e.getLastRow()))
                        changeListRow.put(e.getLastRow(),new ArrayList<>());
                    changeListRow.get(e.getLastRow()).add(e.getColumn());
                }
            }catch (ArrayIndexOutOfBoundsException e1){
                e1.printStackTrace();
            }
        });
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
                            String capacity = tableList.getValueAt(selectedRow,6).toString();
                            boolean isFull;
                            int indexOf = capacity.indexOf('/');
                            isFull = capacity.substring(0, indexOf).compareTo(capacity.substring(indexOf + 1, capacity.length())) >= 0;
                            if(isFull){
                                JOptionPane.showMessageDialog(null, "选课容量已满", "确认", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            result = JOptionPane.showConfirmDialog(null, "是否选课", "确认", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.YES_NO_OPTION)
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
        jtvJP = new JPanel(new BorderLayout());
        setListContent();
        JPanelInit();
        setTableListListener();
        return jtvJP;
    }
    //使JTable列宽适应文本内容
    private void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
        while(columns.hasMoreElements()){
            TableColumn column = columns.nextElement();
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
            if(tableStatus != null)
                tableStatus.setText("");
        }
        else{
            if(viewType == ViewType.TEACHERS_STU_INFO){
                setTableStatus();
            }
            tableModel.setDataVector(content, title);
        }
        tableModel.fireTableDataChanged();
        System.out.println(tableModel.getRowCount());
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

    private JPanel setTableStatusJP(){
        JPanel tableStatusJP = new JPanel(new BorderLayout(), true);
        JPanel buttonJP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tableStatusJP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        String[] jButtonNames = new String[]{
                "添加",
                "删除",
                "确认",
                "刷新",
        };
        JButton[] jButtons = new JButton[jButtonNames.length];
        for(int i = 0; i < jButtonNames.length; i++){
            jButtons[i] = new JButton(jButtonNames[i]);
            buttonJP.add(jButtons[i]);
        }
        JTextField searchJTF = new JTextField();
        searchJTF.setHorizontalAlignment(SwingConstants.CENTER);
        tableStatusJP.add(buttonJP, BorderLayout.CENTER);
        tableStatusJP.add(searchJTF,BorderLayout.NORTH);
        changeListRow = new HashMap<>();
        changeListColumn = new ArrayList<>();
        adminController = new AdminController();

        searchJTF.getDocument().addDocumentListener(new DocumentListener() {
            private void changeFilter(DocumentEvent event) {
                Document document = event.getDocument();
                try {
                    String text=document.getText(0, document.getLength());
                    setSearchResultTable(text);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeFilter(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeFilter(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeFilter(e);
            }
        });

        jButtons[0].addActionListener(e->{
            tableModel.addRow(new Object[]{});
        });
        jButtons[1].addActionListener(e->{
            adminController.removeStuInfo((String) tableModel.getValueAt(tableList.getSelectedRow(), 0));
            tableModel.removeRow(tableList.getSelectedRow());
        });
        jButtons[2].addActionListener(e->{
            if(changeListRow.size() == 0)
                return;
            Iterator iter = changeListRow.entrySet().iterator();
            List<StudentInfo> list = new ArrayList<>();
            while (iter.hasNext()){
                int key = Integer.parseInt(String.valueOf(((Map.Entry)iter.next()).getKey()));
                if(key < -1)
                    return;
                StudentInfo studentInfo = new StudentInfo();
                for(int i = 0; i < changeListRow.get(key).size(); i++){
                    String v = null;
                    try{
                        v = (String) tableModel.getValueAt(Integer.parseInt(String.valueOf(key)), changeListRow.get(key).get(i));
                    }catch (ArrayIndexOutOfBoundsException e1){
                        e1.printStackTrace();
                    }
                    switch (changeListRow.get(key).get(i)){
                        case 0:studentInfo.setUID(v);break;
                        case 1:studentInfo.setUserName(v);break;
                        case 2:studentInfo.setUserSex(v);break;
                        case 3:try{
                            studentInfo.setUserBirthday(Date.valueOf(v));
                        }catch (IllegalArgumentException e1){
                            e1.printStackTrace();
                            studentInfo.setUserBirthday(null);
                        };break;
                        case 4:studentInfo.setBirthPlace(v);break;
                    }
                }
                if(studentInfo.getUID() == null)
                    studentInfo.setUID((String) tableModel.getValueAt(key, 0));
                list.add(studentInfo);
            }
            adminController.updateOrInsertStuInfo(list);
        });
        jButtons[3].addActionListener(e->{
            setContent();
        });

        return tableStatusJP;
    }

    private void setSearchResultTable(String text){
        if(text == null){
            tableModel.setDataVector(content, title);
            tableModel.fireTableStructureChanged();
        }
        List<String[]> resultList = new ArrayList<>();
        for(int i = 0; i < content.length; i++){
            if(content[i][0].contains(text) || content[i][1].contains(text))
                resultList.add(content[i]);
        }
        String[][] resultData = new String[resultList.size()][];
        for(int i = 0; i < resultData.length; i++){
            resultData[i] = resultList.get(i);
        }
        tableModel.setDataVector(resultData, title);
        tableModel.fireTableStructureChanged();
    }

    private void setTableStatus(){
        if(tableStatus == null){
            tableStatus = new JLabel();
            jtvJP.add(tableStatus, BorderLayout.SOUTH);
            tableStatus.setHorizontalAlignment(SwingConstants.RIGHT);
            tableStatus.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        }
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
                break;
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
                break;
                default:
        }
        tableStatus.setText(sb.toString());
    }

    private JPanel setJComboBox(String title, String[] boxList){
        final JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jComboBox = new JComboBox<String>();
        JLabel jLabel = new JLabel(title);

        if(boxList != null){
            comboBox = boxList[0];
            jComboBox.setModel(new DefaultComboBoxModel<>(boxList));
        }else
            jComboBox.setModel(new DefaultComboBoxModel<>());

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

    private JPanel setJComboBoxJP(){
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String title;
        switch (viewType){
            case TEACHERS_STU_INFO:
                title = "选择课号";
                boxList = ((TeacherInfo)UserManager.getUserInfo()).getCourseIDs();
                jPanel.add(setJComboBox(title, boxList));
                break;
            case QUERY_GRADE:
            case SELECTED_COURSE:
            case COURSE_INFO:
            case TEACHER_COURSE:
            case QUERY_GRADE_POINT:
                title = "选择学期";
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
                jPanel.add(setJComboBox(title, boxList));
                break;
            case STUDENT_MANAGER:
//                title = "学院";
//                jPanel.add(setJComboBox(title,null));
//                title = "专业";
//                jPanel.add(setJComboBox(title,null));
//                title = "班级";
//                jPanel.add(setJComboBox(title,null));

        }
        return jPanel;
    }

}
