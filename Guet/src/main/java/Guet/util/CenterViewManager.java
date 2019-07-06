package Guet.util;

import Guet.view.CenterView;

import javax.swing.*;
import java.awt.*;

public class CenterViewManager {

    private static CenterView centerViewManager;

    private static JTableList jTableList;

    public static CenterView getCenterView(){

            return centerViewManager;

    }

    public static void setCenterView(CenterView cvm){
        centerViewManager = cvm;
    }

    public static void setFuncViw(JPanel jPanel){
        centerViewManager.removeFuncView();
        centerViewManager.setFuncViw(jPanel);
        centerViewManager.repaint();
        centerViewManager.validate();
    }

    public static void closeCenterView(){
        centerViewManager.dispose();
        ServerSqlSession.closeSqlSession();
    }

    public static JTableList getjTableList() {
        return jTableList;
    }

    public static void setjTableList(JTableList jTableList) {
        CenterViewManager.jTableList = jTableList;
    }
}
