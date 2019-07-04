package Guet.util;

import javafx.scene.input.ScrollEvent;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {

    public TableCellTextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        // 计算当下行的最佳高度
//        int maxPreferredHeight = 0;
//        for (int i = 0; i < table.getColumnCount(); i++) {


//        }
//
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int maxHeight = 78;

        if (table.getRowHeight(row) != maxHeight) // 少了这行则处理器瞎忙
        {
            table.setRowHeight(row, maxHeight);
        }

        table.setRowHeight(row, maxHeight);

        table.getTableHeader().setResizingColumn(table.getColumnModel().getColumn(column));
        if(column == 0)
            table.getColumnModel().getColumn(column).setWidth(20 + table.getIntercellSpacing().width);
//        setFont(new Font("微软雅黑", Font.PLAIN, 12));
//        setText(value == null ? "" : value.toString());
//        if(row%2 == 0){
//            setBackground(Color.LIGHT_GRAY); // 设置奇数行底色
//        }else if(row%2 == 1){
//            setBackground(Color.WHITE); // 设置奇数行底色
//        }

        setText(value == null ? "" : value.toString());
        return this;
    }
}


