package Guet.util;

import javax.swing.*;

public class CenterViewManager {

    private static JFrame centerViewManager;

    public static JFrame getCenterView(){
        if(centerViewManager == null)
            return centerViewManager = new JFrame();
        else
            return centerViewManager;
    }

    public static void setCenterView(JFrame cvm){
        centerViewManager = cvm;
    }

}
