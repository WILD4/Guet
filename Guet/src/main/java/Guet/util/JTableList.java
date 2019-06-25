package Guet.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class JTableList extends JTable {

    public JTableList(String[][] content, String[] title){
        super(content, title);
    }

    public JTableList(DefaultTableModel defaultTableModel){
        super(defaultTableModel);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
