package Guet.view;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

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
    }

    public static void main(String arg[]){
            new TestView();
    }

}
