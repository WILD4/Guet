package Guet.util;

import Guet.view.CenterView;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {

    CenterView.ViewType viewType;

    public MyDefaultTableModel(String[][] content, String[] title, CenterView.ViewType viewType){
        super(content, title);
        this.viewType = viewType;
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if(columnIndex == 3 && viewType == CenterView.ViewType.TEACHERS_STU_INFO || viewType == CenterView.ViewType.STUDENT_MANAGER)
            return true;
        return false;
    }

}
