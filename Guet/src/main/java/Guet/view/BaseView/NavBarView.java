package Guet.view.BaseView;

import Guet.util.AppConfig;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class NavBarView extends JPanel {

    private  JButton[] jButtons;

    public NavBarView(String[] jButtonsName){
        init(jButtonsName);
    }

    private void init(String[] jButtonsName){
        FlowLayout flowLayout = new FlowLayout(0,10,10);
        flowLayout.setAlignment(FlowLayout.CENTER);
        this.setLayout(flowLayout);
        this.setPreferredSize(new Dimension(AppConfig.navBarWidth, AppConfig.navBarHeight));
        this.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        jButtons = new JButton[jButtonsName.length];
        for(int i = 0; i < jButtonsName.length; i++) {
            jButtons[i] = new JButton(jButtonsName[i]);
        }
    }

    public JButton[] getJButtons(){
        return jButtons;
    }

    private void addActionListener(){}

}
