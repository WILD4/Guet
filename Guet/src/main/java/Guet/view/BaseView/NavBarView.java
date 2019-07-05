package Guet.view.BaseView;

import Guet.util.AppConfig;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class NavBarView extends JPanel {

    protected  JButton[] jButtons;
    protected  JPanel[] views;

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

        JLabel title = new JLabel("主菜单", SwingConstants.CENTER);
        this.add(title);

        for(JButton jButton : jButtons)
            this.add(jButton);

        views = new JPanel[jButtons.length];

        title.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

    private void addActionListener(){

    }

}
