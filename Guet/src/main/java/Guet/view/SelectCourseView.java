package Guet.view;

import Guet.util.AppConfig;
import Guet.util.JTableList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class SelectCourseView extends JFrame {

    SelectCourseView() throws IOException {

        this.add(new JTableView().getViewJP(CenterView.ViewType.NO_SELECT_COURSE));
        this.setVisible(true);
        this.setSize(AppConfig.width, AppConfig.height);
        this.setLocationRelativeTo(null);
    }


}
