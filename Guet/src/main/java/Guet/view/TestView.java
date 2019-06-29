package Guet.view;

import Guet.controller.LoginController;
import Guet.util.StudentManager;
import Guet.util.TableCellTextAreaRenderer;
import Guet.view.BaseView.FuncView;

import javax.swing.*;
import java.io.IOException;

public class TestView extends JFrame {

    JComboBox jComboBox;
    int beginYear = 1997;

    public TestView(){
        init();
    }

    void init(){
//        jComboBox = new JComboBox();
//        Calendar c = Calendar.getInstance();
//        int nowYear = c.get(Calendar.YEAR);
//        System.out.println(nowYear);
//        String[] semesterList = new String[2 * (nowYear - beginYear)];
//
//        for(int i = 0, year = nowYear; i < semesterList.length; i += 2, year--){
//            semesterList[i] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "下半学期";
//            semesterList[i + 1] = String.valueOf(year)+ "-" + String.valueOf(year - 1) + "上半学期";
//        }
//
//        jComboBox.setModel(new DefaultComboBoxModel(semesterList));
//
//        this.add(jComboBox);
//        this.setSize(200,80);
//        this.setVisible(true);
//
//        jComboBox.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if(e.getStateChange() == ItemEvent.SELECTED) {
//                    System.out.println(jComboBox.getSelectedItem().toString());
//                }
//            }
//        });
        this.add(new FuncView("个人信息", CenterView.ViewType.SELECTED_COURSE));
        this.setVisible(true);
        this.setSize(800,600);
    }

    public static void main(String arg[]) throws IOException {
        StudentManager.setStudent(new LoginController().getStudentInfo("1700420201"));
//        new TestView();
        String courseTime = "/37-8/57-8";
        char week = courseTime.charAt(1);
        String lessons = String.valueOf(courseTime.charAt(2)) + courseTime.charAt(4);
//        System.out.println(week);
//        System.out.println(lessons);
//        System.out.println(courseTime.charAt(2));
//        System.out.println(courseTime.charAt(4));


        new C();
    }




}
class C extends JFrame {
    public C() {
        JTable tbl = new JTable(new String[][]{{
                "JTable 里单元格内容的显示器是 TableCellRenderer。",
                "默认的显示器（DefaultTableCellRenderer） 继承 JLabel 所以不方便多行显示。",
                "要多行显示应该继承 JTextArea（参看下面的 TableCellTextAreaRenderer 类）。",
                "当然，别忘了调用 JTable.setDefaultRenderer() 登记你的显示器。"}},
                "A B C D".split(" "));
        tbl.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
        add(new JScrollPane(tbl));
        setSize(800, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
