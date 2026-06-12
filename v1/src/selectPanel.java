
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class selectPanel extends JPanel
{
    public JPanel spanel;
    public JButton logoutButton,createButton,joinButton;
    public String hotspotName;
    public JLabel welcomeLabel,orLabel;

    public selectPanel()
    {
        logoutButton = new JButton("Logout");
        welcomeLabel = new JLabel("");
        createButton = new JButton("Create");
        joinButton = new JButton("Join");
        orLabel = new JLabel("OR");
        orLabel.setFont(new Font("Calibri",Font.BOLD,30));

        spanel = new JPanel();
        spanel.setLayout(null);
        
        welcomeLabel.setBounds(70,80,500,40);
        welcomeLabel.setFont(new Font("Calibri",Font.BOLD,50));

        logoutButton.setBounds(460,5,85,30);

        createButton.setBounds(180,200,200,50);
        joinButton.setBounds(180,350,200,50);

        orLabel.setBounds(250,280,50,40);

        
        spanel.add(logoutButton);
        spanel.add(welcomeLabel);
        spanel.add(createButton);
        spanel.add(joinButton);
        spanel.add(orLabel);

    }

    public void gethotspotName(String name)
    {
        this.hotspotName = name;
        welcomeLabel.setText("Welcome "+hotspotName+"!");
    }

}