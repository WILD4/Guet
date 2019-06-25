package Guet.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import java.util.List;

public class TableListCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    private List<String[]> positions;

    public TableListCellRenderer(List<String[]> positions){
        this.positions = positions;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,
                                                   int row, int col) {

        for(int i=0; i<table.getRowCount(); i++){
            if (row == i){
                this.setBackground(Color.white);
            }
        }
        for(String[] rowAndCol : this.positions){
            int _row= Integer.valueOf(rowAndCol[0]);
            int _col= Integer.valueOf(rowAndCol[1]);

            if( _row == row && _col == col) {
                this.setBackground(Color.LIGHT_GRAY );
            }
        }
        this.setText(value.toString());
        return this;
    }
}

