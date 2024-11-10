
package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class createPanel extends JPanel
{

    public JPanel cpanel;
    public JLabel helloLabel,creatingLabel,infoLabel,pleaseLabel,idLabel;
    public String hotspotName;
    public JProgressBar pb;
    public boolean progressCompleted;

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

                if(i > 100) progressCompleted = true;
            }
        });
        t.start();


    }
    
    public createPanel()
    {
        progressCompleted = false;
        cpanel = new JPanel();
        cpanel.setLayout(null);
        helloLabel = new JLabel("");

        helloLabel.setBounds(150,50,400,60);
        helloLabel.setFont(new Font("Calibri",Font.BOLD,40));

        creatingLabel = new JLabel("Creating room to start sharing...");
        creatingLabel.setBounds(10,120,550,50);
        creatingLabel.setFont(new Font("Calibri",Font.BOLD,30));


        pleaseLabel = new JLabel("<html>Please wait while we get you connected to other device...</html>");
        pleaseLabel.setBounds(80,200,550,80);
        pleaseLabel.setFont(new Font("Calibri",Font.BOLD,25));

        idLabel = new JLabel("Room ID : "+hotspotName);
        idLabel.setBounds(155,400,550,50);
        idLabel.setFont(new Font("Calibri",Font.BOLD,30));
      
        infoLabel = new JLabel("<html><p>Ask the other user to click \"Join\" and wait for connection to\""+hotspotName+"\"</p></html>");
        infoLabel.setBounds(10,460,550,50);


        cpanel.add(helloLabel);
        cpanel.add(creatingLabel);
        cpanel.add(infoLabel);
        cpanel.add(pleaseLabel);
        cpanel.add(idLabel);


        pb = new JProgressBar(SwingConstants.HORIZONTAL,0,100);

        pb.setStringPainted(true);
        pb.setValue(0);
        pb.setForeground(Color.BLUE);

        pb.setBounds(20,350,500,40);
        cpanel.add(pb);


    }


    public void gethotspotName(String name)
    {
        this.hotspotName = name;
        System.out.println(hotspotName);
        // welcomeLabel.setText("Welcome "+userName+"!");
    }

}