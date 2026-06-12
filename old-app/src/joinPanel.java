

import java.util.*;
import java.io.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class joinPanel extends JPanel
{

    public JPanel jpanel;
    public JLabel helloLabel,joiningLabel,infoLabel,availabelLabel,pleaseLabel;
    public String hotspotName;
    public boolean progressCompleted;
    public JProgressBar pb;



    public void startProgress()
    {
        System.out.println("Started Progress");
        Thread t = new Thread(new Runnable(){
            public void run() 
            {
                int i = 0;
                for (i = 0; i <= 100; i++) 
                {
                    pb.setValue(i);
                    try 
                    {
                        Thread.sleep(50);
                    } 
                    catch (InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
        });
        t.start();

    }

    public joinPanel()
    {
        progressCompleted = false;
        jpanel = new JPanel();
        jpanel.setLayout(null);
        helloLabel = new JLabel("");
        helloLabel.setBounds(150,50,400,60);
        helloLabel.setFont(new Font("Calibri",Font.BOLD,40));

        joiningLabel = new JLabel("Searching room to start sharing...");
        joiningLabel.setBounds(15,120,550,50);
        joiningLabel.setFont(new Font("Calibri",Font.BOLD,28));

        pleaseLabel = new JLabel("<html>Please wait while we get you connected to other device...</html>");
        pleaseLabel.setBounds(80,200,550,80);
        pleaseLabel.setFont(new Font("Calibri",Font.BOLD,25));




        availabelLabel = new JLabel("Available rooms : ");
        availabelLabel.setBounds(50,400,550,50);
        availabelLabel.setFont(new Font("Calibri",Font.BOLD,30));


        infoLabel = new JLabel("<html><p>Ask the other user to click \"Create\" and wait for the connection to establish</p></html>");
        infoLabel.setBounds(10,460,550,50);

        jpanel.add(helloLabel);
        jpanel.add(joiningLabel);
        jpanel.add(infoLabel);
        jpanel.add(availabelLabel);
        jpanel.add(pleaseLabel);

        pb = new JProgressBar(SwingConstants.HORIZONTAL,0,100);

        pb.setStringPainted(true);
        pb.setForeground(Color.BLUE);

        pb.setBounds(20,350,500,40);
        jpanel.add(pb);
    }


}