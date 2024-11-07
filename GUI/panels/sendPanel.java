
package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class sendPanel extends JPanel
{

    public JPanel spanel;
    public JLabel helloLabel,creatingLabel,infoLabel;
    public static String userName = "";
    
    public sendPanel()
    {
        spanel = new JPanel();
        spanel.setLayout(null);
        helloLabel = new JLabel("");

        helloLabel.setBounds(150,50,400,60);
        helloLabel.setFont(new Font("Calibri",Font.BOLD,40));

        creatingLabel = new JLabel("Creating room to start sharing...");

        creatingLabel.setBounds(50,150,550,50);
        
        creatingLabel.setFont(new Font("Calibri",Font.BOLD,20));
        infoLabel = new JLabel("Ask the other user to click \"Join\" and\n \"Send request\" for connection to\""+userName+"room\"");
        infoLabel.setBounds(10,500,550,50);

        spanel.add(helloLabel);
        spanel.add(creatingLabel);
        spanel.add(infoLabel);
    }


}