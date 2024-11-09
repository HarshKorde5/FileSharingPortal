
package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class sendPanel extends JPanel
{

    public JPanel spanel;
    public JLabel helloLabel,creatingLabel,infoLabel,reqLabel;
    public String userName;
    public JButton acceptButton,denyButton;

    public sendPanel()
    {
        spanel = new JPanel();
        spanel.setLayout(null);
        helloLabel = new JLabel("");

        helloLabel.setBounds(150,50,400,60);
        helloLabel.setFont(new Font("Calibri",Font.BOLD,40));

        creatingLabel = new JLabel("Creating room to start sharing...");

        creatingLabel.setBounds(80,150,550,50);
        
        creatingLabel.setFont(new Font("Calibri",Font.BOLD,20));
        infoLabel = new JLabel("Ask the other user to click \"Join\" and\n \"Send request\" for connection to\""+userName+"room\"");
        infoLabel.setBounds(10,450,550,50);

        reqLabel = new JLabel("Request to join room from : ");
        reqLabel.setBounds(10,250,550,50);
        reqLabel.setFont(new Font("Calibri",Font.BOLD,25));

        acceptButton = new JButton("Accept");
        denyButton = new JButton("Deny");

        denyButton.setBounds(150,320,100,50);
        acceptButton.setBounds(300,320,100,50);

        spanel.add(helloLabel);
        spanel.add(creatingLabel);
        spanel.add(infoLabel);
        spanel.add(reqLabel);
        spanel.add(acceptButton);
        spanel.add(denyButton);
    }


    public void getuserName(String name)
    {
        userName = name;
        System.out.println(userName);
        // welcomeLabel.setText("Welcome "+userName+"!");
    }

}