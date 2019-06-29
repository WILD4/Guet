package Guet.util;

import Guet.view.CenterView;

import javax.swing.*;
import java.awt.*;

public class CenterViewManager {

    private static CenterView centerViewManager;

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
    }

}
