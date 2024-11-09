
package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class recievePanel extends JPanel
{

    public JPanel rpanel;
    public JLabel helloLabel,joiningLabel,infoLabel,availabelLabel;
    public String userName;
    public JButton sendreqButton;

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
        infoLabel.setBounds(10,450,550,50);

        availabelLabel = new JLabel("Available rooms : ");
        availabelLabel.setBounds(10,250,550,50);
        availabelLabel.setFont(new Font("Calibri",Font.BOLD,25));

        sendreqButton = new JButton("Send Request");
        sendreqButton.setBounds(200,320,200,50);
        sendreqButton.setEnabled(false);

        rpanel.add(helloLabel);
        rpanel.add(joiningLabel);
        rpanel.add(infoLabel);
        rpanel.add(availabelLabel);
        rpanel.add(sendreqButton);
    }


}