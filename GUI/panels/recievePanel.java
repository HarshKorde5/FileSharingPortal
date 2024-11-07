
package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class recievePanel extends JPanel
{

    public JPanel rpanel;
    public JLabel helloLabel,joiningLabel,infoLabel;
    public static String userName = "";
    public recievePanel()
    {
        rpanel = new JPanel();
        rpanel.setLayout(null);
        helloLabel = new JLabel("");
        helloLabel.setBounds(150,50,400,60);
        helloLabel.setFont(new Font("Calibri",Font.BOLD,40));

        joiningLabel = new JLabel("Searching for rooms to start sharing...");
        joiningLabel.setBounds(50,150,550,50);
        joiningLabel.setFont(new Font("Calibri",Font.BOLD,20));

        infoLabel = new JLabel("Ask the other user to click \"Create\" and\n \"Accept\" the connection request from \""+userName+"\"");
        infoLabel.setBounds(10,500,550,50);
        rpanel.add(helloLabel);
        rpanel.add(joiningLabel);
        rpanel.add(infoLabel);
    }


}