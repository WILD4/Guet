package Guet.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AlignmentCenterTabelCellRenderer extends DefaultTableCellRenderer {
    public AlignmentCenterTabelCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER); //设置为水平方向居中
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // 计算当下行的最佳高度
        int maxPreferredHeight = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            setText("  " + table.getValueAt(row, i));
            setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
            maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
        }

        if (table.getRowHeight(row) != maxPreferredHeight)
            table.setRowHeight(row, maxPreferredHeight);

        setFont(new Font("微软雅黑", Font.PLAIN, 15));
        setText(value == null ? "" : value.toString());
        if(row%2 == 0){
            setBackground(Color.decode("#E0FFFF")); // 设置奇数行底色
        }else if(row%2 == 1){
            setBackground(Color.decode("#FFFAFA")); // 设置奇数行底色
        }
        return this;
    }
}
